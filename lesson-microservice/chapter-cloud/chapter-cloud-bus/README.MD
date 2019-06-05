# Spring cloud Bus

## 相关默认配置

* `BusEnvironmentPostProcessor` -> `BusProperties`

  * ```java
    public class BusEnvironmentPostProcessor implements EnvironmentPostProcessor {
        ...
        	private String getDefaultServiceId(ConfigurableEnvironment environment) {
    		return locale;
    	}
        ...
    }
    ```

  *默认配置`BusProperties`* 

## 启动配置

* `BusAutoConfiguration`

## SmartApplicationListener

* 可以监听多个事件，条件选择接受监听的事件类型