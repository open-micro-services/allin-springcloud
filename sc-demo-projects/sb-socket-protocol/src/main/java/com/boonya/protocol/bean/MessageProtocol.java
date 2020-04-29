package com.boonya.protocol.bean;

import com.boonya.protocol.utils.BinaryUtils;
import java.io.IOException;

/**
 * @ClassName: MessageProtocol
 * @Description: TODO(功能说明:消息协议实体对象，请查看：https://blog.csdn.net/lanyue1/article/details/85607396)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/30 0:13
 */
public class MessageProtocol {

    /**
     * 开始标志： 固定为0xFFFF，消息开始标识
     */
    short startSign;//2bytes

    /**
     * 消息类型
     *
     * 单字节整型数，类型编码含义如下：
     * 0:realTime
     * 1:reqLogin
     * 2:ackLogin
     * 3:reqSyncMsg
     * 4:ackSyncMsg
     * 8:reqHeartBeat
     * 9:ackHeartBeat
     * 10:closeConnAlarm
     */
    byte msgType;//1bytes

    /**
     * 时间戳
     *
     * 字节整型数，字节顺序为Big-Endian，表示消息产生时间,为距离1970-01-01 00:00:00时间偏移的秒数。
     */
    int timeStamp;//4bytes

    /**
     * 消息体长度
     *
     * 2字节整型数，字节顺序为Big-Endian，表示消息体字节长度，取值范围0~32767。
     */
    short bodyLen;//2bytes

    /**
     * 消息体
     */
    byte [] bodyData;

    /**
     * 将消息封装为二进制数据
     *
     * @param msgType
     * @param bodyData
     * @return
     */
    public MessageProtocol (byte msgType,String bodyData){
        byte[] allMessage = new byte[9 + bodyData.length()];
        this.msgType = msgType;
        this.startSign = (short)0xFFFF;
        this.timeStamp = (int)(System.currentTimeMillis() / 1000L);
        this.bodyLen = (short)bodyData.length();
        this.bodyData=bodyData.getBytes();
        // header
        System.arraycopy(BinaryUtils.shortToBytes2(this.startSign), 0, allMessage, 0, 2);
        System.arraycopy(BinaryUtils.byteToBytes2(this.msgType), 0, allMessage, 2, 1);
        System.arraycopy(BinaryUtils.intToBytes2(this.timeStamp), 0, allMessage, 3, 4);
        System.arraycopy(BinaryUtils.shortToBytes2(this.bodyLen), 0, allMessage, 7, 2);
        // body
        System.arraycopy(bodyData.getBytes(), 0, allMessage, 9, bodyData.length());
        System.out.println("startSign=" + startSign + ",msgType=" + msgType + ",timeStamp=" + timeStamp + ",bodyLen=" + bodyLen + ",bodyData=" + bodyData);
    }

    /**
     * 构造方法
     *
     * @param startSign
     * @param msgType
     * @param timeStamp
     * @param bodyLen
     * @param bodyData
     */
    public MessageProtocol(short startSign, byte msgType, int timeStamp, short bodyLen, byte[] bodyData) {
        this.startSign = startSign;
        this.msgType = msgType;
        this.timeStamp = timeStamp;
        this.bodyLen = bodyLen;
        this.bodyData = bodyData;
    }

    /**
     * 获取对象的二进制数据
     *
     * @return
     */
    public byte[]  getBinaryData(){
        String body = new String(bodyData);
        byte[] allMessage = new byte[9 + body.length()];
       /* short startSign = (short)0xFFFF;
        int timeStamp = (int)(System.currentTimeMillis() / 1000L);
        short bodyLen = (short)body.length();*/
        // header
        System.arraycopy(BinaryUtils.shortToBytes2(startSign), 0, allMessage, 0, 2);
        System.arraycopy(BinaryUtils.byteToBytes2(msgType), 0, allMessage, 2, 1);
        System.arraycopy(BinaryUtils.intToBytes2(timeStamp), 0, allMessage, 3, 4);
        System.arraycopy(BinaryUtils.shortToBytes2(bodyLen), 0, allMessage, 7, 2);
        // body
        System.arraycopy(body.getBytes(), 0, allMessage, 9, body.length());
        return allMessage;
    }

    /**
     * 二进制数据解析为对象
     *
     *
     * @param binaryData
     * @return
     */
    public MessageProtocol parseBinaryData(byte[] binaryData){
        short startSignParser = BinaryUtils.bytesToShort2(binaryData, 0);
        byte msgTypeParser = BinaryUtils.bytesToByte(binaryData, 2);
        int timeStampParser = BinaryUtils.bytesToInt2(binaryData, 3);
        short bodyLenParser = BinaryUtils.bytesToShort2(binaryData, 7);
        byte[] bodyDataParser = new byte[bodyLenParser];
        System.arraycopy(binaryData, 9, bodyDataParser, 0, bodyLenParser);
        String bodyDtaParserStr = new String(bodyDataParser);

        System.out.println("startSignParser=" + startSignParser + ",msgTypeParser=" + msgTypeParser + ",timeStampParser=" + timeStampParser + ",bodyLenParser=" + bodyLenParser + ",bodyDataParser=" + bodyDtaParserStr);
        return new MessageProtocol( startSign,  msgType,  timeStamp,  bodyLen,  bodyDataParser);
    }

    public static void main(String[] args) throws IOException {
        MessageProtocol protocol = new MessageProtocol((byte)1,"Tom is a good man.");

        byte [] binaryData = protocol.getBinaryData();

        protocol.parseBinaryData(binaryData);

    }


}
