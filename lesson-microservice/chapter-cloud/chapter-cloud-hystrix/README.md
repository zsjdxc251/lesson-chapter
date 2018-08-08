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

  * `eureka.instance` 关联配置类 `EurekaInstanceConfigBean`

  * 控制台页面 EurekaDashboardProperties


## RestTemplate
  * 适配工厂：ClientHttpRequestFactory

  * Spring 实现
    * SimpleClientHttpRequestFactory
  * HttpClient
    * HttpComponentsClientHttpRequestFactory
  * OkHttp
    * OkHttp3ClientHttpRequestFactory
    * OkHttpClientHttpRequestFactory

  * 示例
    ```java

    RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    ```
    **切换HTTP 通讯实现，提升性能**

  * HTTP 请求拦截器：ClientHttpRequestInterceptor
  
    **加深RestTemplate 拦截过程的**
