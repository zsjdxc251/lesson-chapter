
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