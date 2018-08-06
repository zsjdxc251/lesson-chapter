# Spring WebFlux Reactive

## 配置
  * `EurekaClientConfigBean`

  * 获取注册信息时间间隔
    * `eureka.client.registryFetchIntervalSeconds`

  * 实例信息复制时间间隔
    * `eureka.client.instanceInfoReplicationIntervalSeconds`

  * 配置信息
    * `--spring.profiles.active=peer2`
  
  * 客户端配置负载均衡高可用
    * `eureka.client.serviceUrl.defaultZone=http://localhost:${eureka.server.port}/eureka/,http://localhost:${eureka.server.port}/eureka/`


