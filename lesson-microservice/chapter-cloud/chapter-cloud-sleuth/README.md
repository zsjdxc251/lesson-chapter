# Spring cloud sleuth

## 配置zipkin Http上报

### sleuth-zipkin 相关配置

* 属性配置

```properties
spring:
  application:
    name: spring-cloud-sleuth-zipkin
server:
  port: 7071
```

* 依赖配置

  ```xml
  <!-- Zipkin 服务器依赖 -->
  <dependency>
      <groupId>io.zipkin.java</groupId>
      <artifactId>zipkin-server</artifactId>
      <version>2.11.3</version>
      <exclusions>
          <exclusion>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-log4j2</artifactId>
          </exclusion>
      </exclusions>
  </dependency>
  
  <!-- Zipkin 服务器UI控制器 -->
  <dependency>
      <groupId>io.zipkin.java</groupId>
      <artifactId>zipkin-autoconfigure-ui</artifactId>
      <version>2.11.3</version>
      <scope>runtime</scope>
  </dependency>
  ```

* 启用注解

  ```java
  zipkin2.server.internal.EnableZipkinServer
  ```


### 调用链应用配置

* 配置上报地址

  ```properties
  spring:
     zipkin:
      baseUrl: http://localhost:7071/
  ```

* 配置依赖包

  ```xml
  <!-- 配置跟踪 器-->
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-zipkin</artifactId>
  </dependency>
  ```

## 配置消息总线 zipkin 上报

* 启动`zipkin.jar` 命令

  ```shell
  java -DKAFKA_BOOTSTRAP_SERVERS=ip:port,ip:port -jar zipkin.jar
  ```
