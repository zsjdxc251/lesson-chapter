
http://192.168.23.128:8161/admin/

消息可靠性机制
  createSession(boolean arg0, int arg1)

  arg1:
      int AUTO_ACKNOWLEDGE = 1;
      int CLIENT_ACKNOWLEDGE = 2;
      int DUPS_OK_ACKNOWLEDGE = 3;
      int SESSION_TRANSACTED = 0;

  arg0 true 事务性确认
       生产者 使用session.commit()提交数据
       消费者 使用session.commit()确认消息
       消费者 使用session.rollback() 回滚=不commit
  arg0 true arg1 失效 被jms服务器设置为SESSION_TRANSACTED


  arg1 false 非事务确认

  arg0 false
       AUTO_ACKNOWLEDGE 自动确认
       CLIENT_ACKNOWLEDGE 客户端确认  javax.jms.Message的acknowledge
          客户端在确认消息时，如果在一个session里面消费多条数据，在消费某一条消息的时候调用了Message.acknowledge()那么之前消费的也同样被确认消费
       DUPS_OK_ACKNOWLEDGE 延迟确认

  arg0 false 非事务session不能使用 session.commit(),session.rollback()

  持久化
     MessageProducer.NON_PERSISTENT 非持久化
     MessageProducer.PERSISTENT 持久化 （默认）

  优先级
    0-10  （默认4）
  消息过期
    setTimeToLive (默认永久)



重发机制



同步发送和异步发送针对broker而言
    默认情况：
      1.非持久化是异步发送
      2.持久化非事务是同步发送
      3.开始事务都是异步发送
    配置异步发送
    1.ActiveMQConnectionFactory.setUseAsyncSend(true)
    2.ActiveMQConnection.setUseAsyncSend(true)
    3.brokerUrl?jms.userAsyncSend=true


源码剖析

ResponseCorrelator(MutexTransport(WireFormatNegotiator(InactivityMonitor(TcpTransport))))



生产者
    producerWindowSize
       只针对异步发送
        单位为字节
       ProducerWindowSize是一个生产者在等到确认消息之前，可以发送给代理broker的最大字节数据量，这个确认消息用来告诉生产者，代理broker已经收到先前发送的消息了。
       ProducerWindowSize是指在收到broker确认应答之前，生产者能够传送消息给broker的最大信息量
       配置大小
       1.ActiveMQConnectionFactory.setProducerWindowSize()
       2.brokerUrl?jms.producerWindowSize=105588
       3.ActiveMQConnection.setProducerWindowSize()
       4.destinationUrl?producer.windowSize=


    这个值越大消耗的内存越大


消费者
    prefetchSize
       默认 1000
       非持久化 topic 默认100
       用户一次性broker获取Message条数
       设置
          consumer.prefetchSize=


    optimizeAcknowledge
       开启批量确认
       prefetchSize*0.65 发起确认
       设置
       1.ActiveMQConnectionFactory.setOptimizeAcknowledge(true)
       2.?jms.optimizeAcknowledge=true

    optimizeAcknowledgeTimeOut
       超时时间
        设置
        1.?jms.optimizeAcknowledgeTimeOut=1000

Number Of Consumers  消费者 这个是消费者端的消费者数量

Number Of Pending Messages 等待消费的消息 这个是当前未出队列的数量。可以理解为总接收数-总出队列数
Messages Enqueued 进入队列的消息  进入队列的总数量,包括出队列的。 这个数量只增不减
Messages Dequeued 出了队列的消息  可以理解为是消费这消费掉的数量 