# MonogoDB

## Quickstart

* 简单启动  

  ```shell
  ./mongod --dbpath=/usr/local/mongodb/data/ --bind_ip=0.0.0.0 --fork --logpath=/usr/local/mongodb/logs/mongod.log
  
  ./mongod -f ../conf/mongod.conf
  ```

* 终端连接

  ```shell
  ./mongo localhost
  ```

* 帮助命令

  ```sh
  ./mongod -h
  ```

  
