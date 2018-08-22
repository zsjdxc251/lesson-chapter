# Spring cloud Bus

## 相关默认配置

* `BusEnvironmentPostProcessor` -> `BusProperties`

  * ```java
    public class BusEnvironmentPostProcessor implements EnvironmentPostProcessor {
        ...
        	private String getDefaultServiceId(ConfigurableEnvironment environment) {
    		return "${vcap.application.name:${spring.application.name:application}}:${vcap.application.instance_index:${spring.application.index:${local.server.port:${server.port:0}}}}:${vcap.application.instance_id:${random.value}}";
    	}
        ...
    }
    ```

  *默认配置`BusProperties`* 

## 启动配置

* `BusAutoConfiguration`

## SmartApplicationListener

* 可以监听多个事件，条件选择接受监听的事件类型