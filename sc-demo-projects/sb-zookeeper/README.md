## Zookeper应用

通知服务、配置管理中心、集群微服务注册、分布式锁。

## What's Zookeeper?

http://zookeeper.apache.org/


ZooKeeper是一个分布式的，开放源码的分布式应用程序协调服务，是Google的Chubby一个开源的实现，是Hadoop和Hbase的重要组件。它是一个为分布式应用提供一致性服务的软件，提供的功能包括：配置维护、域名服务、分布式同步、组服务等。

ZooKeeper的目标就是封装好复杂易出错的关键服务，将简单易用的接口和性能高效、功能稳定的系统提供给用户。

ZooKeeper包含一个简单的原语集，提供Java和C的接口。

ZooKeeper代码版本中，提供了分布式独享锁、选举、队列的接口，代码在$zookeeper_home\src\recipes。其中分布锁和队列有Java和C两个版本，选举只有Java版本。

## Zookeeper原理

ZooKeeper是以Fast Paxos算法为基础的，Paxos 算法存在活锁的问题，即当有多个proposer交错提交时，有可能互相排斥导致没有一个proposer能提交成功，而Fast Paxos作了一些优化，通过选举产生一个leader (领导者)，只有leader才能提交proposer，具体算法可见Fast Paxos。因此，要想弄懂ZooKeeper首先得对Fast Paxos有所了解。 [2]
 
**ZooKeeper的基本运转流程：**

1、选举Leader。

2、同步数据。

3、选举Leader过程中算法有很多，但要达到的选举标准是一致的。

4、Leader要具有最高的执行ID，类似root权限。

5、集群中大多数的机器得到响应并接受选出的Leader。

## Zookeeper特点
在Zookeeper中，znode是一个跟Unix文件系统路径相似的节点，可以往这个节点存储或获取数据。如果在创建znode时Flag设置为EPHEMERAL，那么当创建这个znode的节点和Zookeeper失去连接后，这个znode将不再存在在Zookeeper里，Zookeeper使用Watcher察觉事件信息。当客户端接收到事件信息，比如连接超时、节点数据改变、子节点改变，可以调用相应的行为来处理数据。Zookeeper的Wiki页面展示了如何使用Zookeeper来处理事件通知，队列，优先队列，锁，共享锁，可撤销的共享锁，两阶段提交。

那么Zookeeper能做什么事情呢，简单的例子：假设我们有20个搜索引擎的服务器(每个负责总索引中的一部分的搜索任务)和一个总服务器(负责向这20个搜索引擎的服务器发出搜索请求并合并结果集)，一个备用的总服务器(负责当总服务器宕机时替换总服务器)，一个web的cgi(向总服务器发出搜索请求)。搜索引擎的服务器中的15个服务器提供搜索服务，5个服务器正在生成索引。这20个搜索引擎的服务器经常要让正在提供搜索服务的服务器停止提供服务开始生成索引，或生成索引的服务器已经把索引生成完成可以提供搜索服务了。使用Zookeeper可以保证总服务器自动感知有多少提供搜索引擎的服务器并向这些服务器发出搜索请求，当总服务器宕机时自动启用备用的总服务器。


## Zookeeper操作

### 进入命令行操作zk

linux:

`./zkCli.sh -server 127.0.0.1:2181`

windows

`zkCli.cmd -server localhost:2181`

### 常用命令

`ls /         # 查看目录`

`set /zk new  # 修改目录`

`get /zk      # 获取目录`

`delete /zk   # 删除目录`

`create /zk   # 创建目录`

`ZooKeeper -server host:port cmd args

        stat path [watch]
        
        set path data [version]
        
        ls path [watch]
        
        delquota [-n|-b] path
        
        ls2 path [watch]
        
        setAcl path acl
        
        setquota -n|-b val path
        
        history
        
        redo cmdno
        
        printwatches on|off
        
        delete path [version]
        
        sync path
        
        listquota path
        
        rmr path
        
        get path [watch]
        
        create [-s] [-e] path data acl  #-s为顺序充点，-e临时节点
        
        addauth scheme auth
        
        quit
        
        getAcl path
        
        close
        
        connect host:port`