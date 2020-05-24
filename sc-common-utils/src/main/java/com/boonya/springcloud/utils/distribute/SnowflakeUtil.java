package com.boonya.springcloud.utils.distribute;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: SnowflakeUtil
 * @Description: TODO(功能说明 ： 雪花算法 - - 全局唯一19位ID生成算法【JS语言不支持15位以上的长整型，该工具使用与否要有取舍】)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/24 22:33
 * @noete [twitter 用于生成id的算法]
 * 对于mysql而言，InnoDB为聚集主键类型的引擎，数据会按照主键进行排序，由于UUID的无序性，InnoDB会产生巨大的IO压力。
 * InnoDB主键索引和数据存储位置相关（簇类索引），uuid 主键可能会引起数据位置频繁变动，严重影响性能，而雪花算法的
 * 高位使用的是时间，因此保证了生成的ID的大小是递增的，因此推荐使用雪花算法。
 * <p>
 * mysql的首要推荐当然还是使用ID自增，但是这种做法不适合使用在分布式上，同时也有人觉得只用递增会暴露业务信息（比如通过ID判断产品的销量）
 * ————————————————
 * 版权声明：本文为CSDN博主「toBeMN」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_31748587/article/details/84107424
 */
@Slf4j
public class SnowflakeUtil {
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long twepoch = 1288834974657L;                              //  Thu, 04 Nov 2010 01:42:54 GMT 标记时间 用来计算偏移量，距离当前时间不同，得到的数据的位数也不同
    private long workerIdBits = 5L;                                     //  物理节点ID长度
    private long datacenterIdBits = 5L;                                 //  数据中心ID长度
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);             //  最大支持机器节点数0~31，一共32个
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);     //  最大支持数据中心节点数0~31，一共32个
    private long sequenceBits = 12L;                                    //  序列号12位， 4095，同毫秒内生成不同id的最大个数
    private long workerIdShift = sequenceBits;                          //  机器节点左移12位
    private long datacenterIdShift = sequenceBits + workerIdBits;       //  数据中心节点左移17位
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits; //  时间毫秒数左移22位
    private long sequenceMask = -1L ^ (-1L << sequenceBits);                          // 用于和当前时间戳做比较，以获取最新时间
    private long lastTimestamp = -1L;

    //成员类，SnowFlakeUtil的实例对象的保存域
    private static class IdGenHolder {
        private static final SnowflakeUtil instance = new SnowflakeUtil();
    }

    //外部调用获取SnowFlakeUtil的实例对象，确保不可变
    public static SnowflakeUtil get() {
        return IdGenHolder.instance;
    }

    //初始化构造，无参构造有参函数，默认节点都是0
    public SnowflakeUtil() {
        this(0L, 0L);
    }

    //设置机器节点和数据中心节点数，都是 0-31
    public SnowflakeUtil(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 线程安全的id生成方法
     *
     * @return
     */
    public synchronized long nextId() {
        //获取当前毫秒数
        long timestamp = timeGen();
        //如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            sequence = (sequence + 1) & sequenceMask;
            //判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            if (sequence == 0) {
                //自旋等待到下一毫秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            //如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加，每个毫秒时间内，都是从0开始计数，最大4095
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        // 最后按照规则拼出ID 64位
        // 000000000000000000000000000000000000000000  00000            00000       000000000000
        //1位固定整数   time                                       datacenterId   workerId    sequence
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
            | (workerId << workerIdShift) | sequence;
    }

    /**
     * 比较当前时间和过去时间，防止时钟回退（机器问题），保证给的都是最新时间/最大时间
     *
     * @param lastTimestamp
     * @return
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取当前的时间戳（毫秒）
     *
     * @return
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 获取全局唯一编码
     */
    public static String getId() {
        Long id = SnowflakeUtil.get().nextId();
        return id.toString();
    }

    public static void main(String[] args) {
        log.info(SnowflakeUtil.getId());
    }

}
