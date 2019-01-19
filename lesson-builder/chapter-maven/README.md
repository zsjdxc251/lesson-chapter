# Maven

## 基本命令

* `maven install -U -Dmaven.test.skip=true -P test`



## 获取pid方式

### sh

* 从文件中获取`pid` 

  ```sh
  #!/bin/bash
  
  PIDFILE="$PWD/process.pid"
  PID=$(cat $PIDFILE)
  
  #打印pid
  echo $PID
  kill -QUIT $PID
  ```

* 查找指定应用`pid`输出到指定文件

  ```sh
  ps -ef|grep s |grep -v grep |awk '{print $2}' > process.pid
  ```

* 运行应用获取`pid` 输出到指定文件

  ```sh
  nohup java -jar example-spring-boot-properties-1.0-SNAPSHOT.jar & echo $! > pid.pid
  ```

  且输出到控制台

  ```sh
  nohup java -jar example-spring-boot-properties-1.0-SNAPSHOT.jar & echo $! |tee pid.pid
  ```


### Spring Boot

`org.springframework.boot.system.ApplicationPid`