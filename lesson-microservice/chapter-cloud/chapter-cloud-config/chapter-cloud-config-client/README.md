# Spring cloud config client


## Endpoint
  * 初始化
    * `JmxEndpointDiscoverer#createEndpointBeans` ->  `AbstractWebMvcEndpointHandlerMapping#initHandlerMethods` -> `registerMappingForOperation`
  * `AbstractWebMvcEndpointHandlerMapping` -> `ServletWebOperationAdapter#handle` -> `DiscoveredWebOperation#invoker`

  * health
   * `HealthEndpointProperties`


