# RabbitMq

## Quickstart

* 依赖

  ```sh
  wget www.rabbitmq.com/releases/erlang/erlang-18.3-1.el7.centos.x86_64.rpm
  wget http://repo.iotti.biz/CentOS/7/x86_64/socat-1.7.3.2-5.el7.lux.x86_64.rpm
  wget www.rabbitmq.com/releases/rabbitmq-server/v3.6.5/rabbitmq-server-3.6.5-1.noarch.rpm
  ```

* 后台启动 `rabbitmq-server start &`

* 停止 `rabbitmqctl app_stop`

* 配置信息

  ```shell
  vim /usr/lib/rabbitmq/lib/rabbitmq_server-xx/ebin/rabbit.app
  ```

  *打开`rabbitmq`用户配置，`loopback_users` 中的 `<<"guest">>` 放开*

* 启动管理插件

  ```sh
  ./rabbitmq-plugins enable rabbitmq_management
  ```

* 默认端口

  * `tcp`连接默认端口 `5672`

  * `web`端访问默认端口 `15672`



## 课程目录

1. Web 管理界面的使用
2. 可靠性投递分析
3. 高可用架构部署方案
4. 实践经验总结



## RabbitMQ特性

1. 可靠性
2. 灵活的路由
3. 消息集群
4. 高可用
5. 多种协议
6. 多语言客户端
7. 管理界面
8. 插件机制

## 交换机

### Direct Exchange 直连交换机 

* **routingKey需要准确，且不能为空**



### Topic Exchange 主题交换机

* **routingKey可以模糊匹配，`*`一个单词，`#`多个单词 ，且不能为空**



### Fanout Exchange 广播交换机 

* **routingKey 可以为空，routingKey需要准确，routingKey可以模糊匹配**
* 接收者有多少个，每个监听者都可以收到该消息





## 可靠性投递分析

### 服务端确定-- Transaction模式

```java
            AMQP.Tx.SelectOk selectOk = channel.txSelect();

            System.out.println(selectOk.protocolClassId());
            for (int i = 0; i < 10; i++) {
                final int index = i;
                channel.basicPublish(StringUtils.EMPTY, queueName,
                        null, ("等过期消息" + index).getBytes());
            }

            TimeUnit.SECONDS.sleep(5);

            AMQP.Tx.CommitOk commitOk = channel.txCommit();

            System.out.println(commitOk.protocolMethodName());
```

### 服务端确定-- Confirm 模式

```java
// 开启发送方确认模式
channel.confirmSelect();
            for (int i = 0; i < 10; i++) {
                final int index = i;
                channel.basicPublish(StringUtils.EMPTY, queueName,
                        null, ("等过期消息" + index).getBytes());
            }
            if (channel.waitForConfirms()){
                System.out.println("发送成功");
            }
```

### 服务端批量确定-- 等待发送完成

```java
// 开启发送方确认模式          
channel.confirmSelect();
            for (int i = 0; i < 10; i++) {
                final int index = i;
                new Thread(()->{
                    try {
                        channel.basicPublish(StringUtils.EMPTY, queueName,
                                null, ("等过期消息" + index).getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
//直到所有信息都发布，只要有一个未被Broker确认就会IOException
           channel.waitForConfirmsOrDie();
```

### 服务端异步监听--确定消息发送

```java
           channel.confirmSelect();
            for (int i = 0; i < 10; i++) {
                final int index = i;
                channel.basicPublish(StringUtils.EMPTY, queueName,
                        null, ("等过期消息" + index).getBytes());
            }
            channel.addConfirmListener(new ConfirmListener() {
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("Broker未确认消息，标识：" + deliveryTag);
                }
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    // 如果true表示批量执行了deliveryTag这个值以前（小于deliveryTag的）的所有消息，如果为false的话表示单条确认
                    System.out.println(String.format("Broker已确认消息，标识：%d，多个消息：%b", deliveryTag, multiple));
                }
            });
```



### 消息存储

1. 队列持久化

   ```java
   channel.queueDeclare(QUEUE_NAME, true, false, false, arguments);
   ```

   

2. 交换机持久化

   ```java
   channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT,true);
   ```

   

3. 消息持久化

   ```java
   AMQP.BasicProperties props = new AMQP.BasicProperties().builder().deliveryMode(2).build();
               for (int i = 0; i < 10; i++) {
                   final int index = i;
                   channel.basicPublish(StringUtils.EMPTY, queueName,
                           props, ("等过期消息" + index).getBytes());
               }
   ```


### 消费者确定

* 手工应答

  ```java
  channel.basicAck(envelope.getDeliveryTag(),false);
  ```

  

* 单条拒绝

  ```java
  channel.basicReject(envelope.getDeliveryTag(),false);
  ```

  

* 批量拒绝

  ```java
  channel.basicNack(envelope.getDeliveryTag(),false,true);
  ```



### 其他

1. 消息者回调
2. 补偿机制
3. 消息幂等性
4. 消息顺序性

## 进阶问题

1. 怎么自动删除没消费者消费的消息

   * 发送消息的时候自定过期时间 设置10秒钟过期，**删除消息**

     ```java
     AMQP.BasicProperties properties =
                         new AMQP.BasicProperties().builder().expiration("10000").build();
     channel.basicPublish(StringUtils.EMPTY, queueName,
                             properties, ("消息" + i).getBytes());
     ```

   * 指定整个队列，收到生产者发送的消息没有消费过期时间，**删除消息**

     ```java
     Map<String,Object> arguments = Maps.newHashMap();
                 arguments.put("x-message-ttl",10000);
                 channel.queueDeclare(queueName, true, false, false, arguments);
     ```

     **该队列一定是要没有创建新队列指定该值**

   * 指定队列过期时间，也就是会自定**删除该队列**

     ```java
     Map<String,Object> arguments = Maps.newHashMap();
                 arguments.put("x-expires",10000);
                 channel.queueDeclare(queueName, true, false, false, arguments);
     ```

2. 死信队列

   * 配置死信队列接收

     ```java
     Map<String, Object> arguments = new HashMap<>();
     arguments.put("x-dead-letter-exchange", "logs.error");
     arguments.put("x-dead-letter-routing-key", "*.warn");
     channel.queueDeclare(queueName, true, false, false, arguments);
     ```

     **消息发送到queueName队列如果过期或者其他，就会转发给指定死信队列**

   * 产生死信队列的几种原因

     1. 消息过期

     2. 手动`ACK` 拒绝 `channel.basicReject(envelope.getDeliveryTag(),false);`

     3. 队列达到最大长度,最大存储多少消息，如果有消费者，会直接被消费

        ```java
         Map<String, Object> arguments = new HashMap<>();
         arguments.put("x-max-length",1);
         channel.queueDeclare(QUEUE_NAME, false, false, false, arguments);
        ```

3. 优先队列

   * 优先级配置

     ```java
                 Map<String, Object> arguments = new HashMap<>();
     
                 arguments.put("x-max-priority", 10);
                channel.queueDeclare(QUEUE_NAME, false, false, false, arguments);
     ```

4. 延迟队列

   * 使用延迟队列插件
   * 使用死信队列 `TTL`+ `DLX` 实现延迟队列

5. MQ流量控制

   * 在消费端设置同一时刻多少消息进来，在消息没有确定ACK 之前下一个消息不会进入

     ```java
                 channel.basicQos(1);
       
                 channel.basicConsume(QUEUE_NAME, false,consumer);
     ```

6. RPC





## 面试题

1. 消息队列的作用与使用场景

2. 创建队列和交换机的方法

3. 多个消费者监听一个生产者时，消息如何分发？

4. 无法被路由的消息，去了哪儿？

5. 消息在什么时候会被Dead Letter?

6. RabbitMQ如何实现延迟队列？

7. 如何保证消息的可靠性投递？

8. 如何在服务端和消费端做限流？

9. 如何保证消息的顺序性？

10. RabbitMQ的集群节点类型？

    磁盘节点与内存节点 



## 附加内容

### 链接

[百分百可靠性投递-慕课](https://segmentfault.com/a/1190000016326662) 

[百分百可靠性投递-CSDN](https://blog.csdn.net/zuixiaoyao_001/article/details/82599115)



