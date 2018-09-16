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




## 集群配置

### shardsver 配置

* 使用 `mongod` 执行

* 相关配置

  ```properties
  dbpath=/usr/workspace/install/mongodb/cluster/shardsvrs/data
  logpath=/usr/workspace/install/mongodb/cluster/shardsvrs/logs/mongodb.log
  logappend=true
  fork=true
  bind_ip=0.0.0.0
  port=27001
  replSet=shard002
  shardsvr=true
  ```

* 使用 `mongo` 命令进入 切换 `use admin`

  ```javascript
  cfg={_id:"shard002",members:[{_id:0,host:'192.168.204.129:27001'},{_id:1,host:'192.168.204.130:27001'},{_id:2,host:'192.168.204.133:27001'}]};
  rs.initiate(cfg);
  
  cfg={_id:"shard001",members:[{_id:0,host:'192.168.204.129:27002'},{_id:1,host:'192.168.204.130:27002'},{_id:2,host:'192.168.204.133:27002'}]};
  rs.initiate(cfg);
  ```

### configsvr 配置

* 使用 `mongod` 执行

* 相关配置

  ```properties
  dbpath=/usr/workspace/install/mongodb/cluster/configsvrs/data
  logpath=/usr/workspace/install/mongodb/cluster/configsvrs/logs/mongodb.log
  logappend=true
  fork=true
  bind_ip=0.0.0.0
  port=28001
  replSet=configrs
  configsvr=true
  ```

* 使用 `mongo` 命令进入 切换到 `use admin`

  ```javascript
  cfg={_id:"configrs",members:[{_id:0,host:'192.168.204.129:28001'},{_id:1,host:'192.168.204.130:28001'},{_id:2,host:'192.168.204.133:28001'}]};
  rs.initiate(cfg);
  ```

### route 配置

* 使用 `mongos` 执行

* 相关配置

  ```properties
  configdb=configrs/192.168.204.133:28001,192.168.204.129:28001,192.168.204.130:28001
  logpath=/usr/workspace/install/mongodb/cluster/routesvrs/logs/mongodb.log
  logappend=true
  fork=true
  bind_ip=0.0.0.0
  port=30000
  ```

* 使用 `mongo` 命令进入切换到 `use admin`

  ```javascript
  sh.addShard("shard002/192.168.204.133:27001");
  
  sh.addShard("shard001/192.168.204.133:27002")
  ```

  
