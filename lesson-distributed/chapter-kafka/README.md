# kafka
## 集群配置
* 在server.properties中配置
  * broker.id= 配置节点id
  * listeners=PLAINTEXT://ip:port 配置该节点访问监听如果该不配置远程调用不到
* 查看配置集群情况
  * 在zookeeper 查看
  * ls /brokers/ids 如果几个集群配置的id都存在代表集群配置成功

## spring-kafka
  * [文档](https://docs.spring.io/spring-kafka/reference/htmlsingle/)

