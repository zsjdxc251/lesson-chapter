# Tomcat 性能优化

## 源码

## 优化



## 附加

### JMX远程连接配置

```properties
-Djava.rmi.server.hostname=192.168.1.1
-Dcom.sum.management.jmxremote=true
-Dcom.sun.management.jmxremote.port=8098
-Dcom.sun.management.jmxremote.ssl=false
-Dcom.sum.management.jmxremote.authenticate=true
-Dcom.sum.management.jmxremote.password.file=../conf/jmx.pwd
-Dcom.sum.management.jmxremote.access.file=../conf.jmx.access
-Djava.security.egd=file:/dev/./urandom
```

### JVM 参数

```properties
-XX:+UseG1GC
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps
-XX:+PrintGCDateStamps
-Xloggc:$XX_HOME/logs/gc.log
-Dcom.sum.management.jmxremote
```









