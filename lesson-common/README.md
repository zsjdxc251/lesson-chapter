# Common

## Java 规范提案(Java Specification Requests)

### JSR-250
 * `@PostConstruct` 和 `@PreDestroy` `@Resource`

### JSR-303
 * Bean Validation 实体校验
  * [参考](https://www.ibm.com/developerworks/cn/java/j-lo-jsr303/index.html)

### JSR-305
 * 注解做编译约束

### JSR-310

* support for the new date/time data types (JSR 310) for `@Past` and `@Future` 


### JSR-330
  * 依赖注入

  * `@Inject`、`@Qualifier`、`@Named`、`@Scope`

### JSR-349

* Bean validation 1.1

### JSR-380

* Bean Validation  2.0
  * `@Email`, `@NotEmpty`, `@NotBlank`, `@Positive`, `@PositiveOrZero`, `@Negative`, `@NegativeOrZero`, `@PastOrPresent` and `@FutureOrPresent` 



## JavaSE

### 数据结构

#### Java 集合

#### Guava & Apache Common
  * Guava
    * `com.google.common.annotations`：普通注解类型。
    * `com.google.common.base`：基本工具类库和接口。
    * `com.google.common.cache`：缓存工具包，非常简单易用且功能强大的JVM内缓存。
    * `com.google.common.collect`：带泛型的集合接口扩展和实现，以及工具类，这里你会发现很多好玩的集合。
    * `com.google.common.eventbus`：发布订阅风格的事件总线。
    * `com.google.common.hash`： 哈希工具包。
    * `com.google.common.io`：I/O工具包。
    * `com.google.common.math`：原始算术类型和超大数的运算工具包。
    * `com.google.common.net`：网络工具包。
    * `com.google.common.primitives`：八种原始类型和无符号类型的静态工具包。
    * `com.google.common.reflect`：反射工具包。
      * `Reflection`
    * `com.google.common.util.concurrent`：多线程工具包。

  * Guava 扩展

### IO/网络

#### 文件系统
#### URL 资源
#### Socket (BIO)
#### NIO
#### NIO2
#### AIO
  * Future + NIO