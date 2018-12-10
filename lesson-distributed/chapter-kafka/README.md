# kafka

## 目录
  * kafka 产生的背景
  * kafka 的架构
  * kafka 安装部署及集群部署
  * kafka 的基本操作
  * kafka 的应用
  * Topic&Partition
  * 消息分发策略
  * 消息消费原理
  * 消息存储策略
  * Partition 的副本机制原理
  * 副本数据的同步原理

## 集群配置
* 启动命令 `./kafka-server-start.sh -daemon ../config/server.propertie`
* 在server.properties中配置
  * broker.id= 配置节点id
  * listeners=PLAINTEXT://ip:port 配置该节点访问监听如果该不配置远程调用不到
* 查看配置集群情况
  * 在zookeeper 查看
  * ls /brokers/ids 如果几个集群配置的id都存在代表集群配置成功

## spring-kafka
  * [文档](https://docs.spring.io/spring-kafka/reference/htmlsingle/)



### Topic&Partition的存储
  * 创建名为test2的topic ,其中有3个partition , broker 3 个
    * `./kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic test2`
    * 会创建 3 个分区 分布在 broker1、broker2、broker3中 `logs/test2-1`、`logs/test2-2` 、`logs/test2-3` 中 如果broker 只有一个 那么三个分区都会在 broker1中生成


  * 分区分配策略
    * Range(默认)
      * 将partitions 的个数除以消费者线程的总数来决定每个消费者相册消费几个分区。如果除不尽，那么前面的几个消费者线程将多消费几个分区。
    * RoundRobin(轮询)
      * 轮询分区策略是把所有的partition和所有的consumer线程列出来，然后按照hashcode 进行排序。最后通过轮询算法分配partition 给消费线程。

## 消息存储原理
  ### 消息文件存储机制

* 索引文件 `.index`  、时间索引 `.timeindex` 、存储消息文件 `.log` 、`leader-epoch-checkpoint`

  ### logSegment 日志分段

* logSegment 也就是把 partition 巨大的文件分段存储
* 配置 log文件大小进行分段 `server.properties` 文件中配置  log.segment.bytes=107370








