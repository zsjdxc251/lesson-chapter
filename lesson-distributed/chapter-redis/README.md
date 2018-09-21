# Redis

[TOC]



## Quickstart

### 安装

* 解压`tar`包 ,进入文件夹 `make ` 编译

  ```sh
  $ make PREFIX=/usr/local/redis install
  ```

  *编译依赖于`c++`  所以先执行 `yum install gcc ` & `yum install gcc-c++ ` | `yum install tcl `*

* 运行 `./bin/redis-server redis.conf `

* 参数运行 `./bin/redis-server --port 7777 --daemonize no`
  * `daemonize` 是否后台启动 `yes` | `no` 

* `./redis-cli -p 端口号 -h 域名`

  ```properties
  logfile 设置日志路径
  ```

  

## 课程目录

1. **分布式缓存技术的应用**
2. **Redis 的魅力**
3. **Redis 安装指引**
4. **Redis 的数据类型及常用命令**
5. **Redis 各个数据类型的底层数据结构简述**
6. **过期时间设置**
7. **消息队列的使用**
8. **过期时间设置及原理分析**
9. **发布订阅模式**
10. **Redis持久化及原理分析**
11. **Redis的内存回收策略**
12. **Redis单线程为什么性能很高**
13. **Lua脚本在Redis中的应用**
14. **Redis的集群**
15. **哨兵机制**
16. **Redis-Cluster**
17. **Redis的实践应用**
18. 

### 数据结构类型
**[Redis命令](http://redisdoc.com/)**

#### String (字符串)

#### List （列表）

* 存储结构
  * redis 3.2 之前 `linkedlist`  和 `ziplist` , 3.2 之后 `quicklist` 双向列表
  *  

#### hash （哈希表）

* 存储结构
  * hash有两种结构：`hash`/`ziplist`
  * 

#### Set （集合）

* 存储结构

  * `intset` , `hashtable`
* 使用场景
  * 

#### SortedSet （有序集合）

* 数据结构
  * `ziplist`或者 `skiplist` `hashtable`
  * 

* 使用场景
  * 网站排名

### 过期时间设置

* `expire key seconds`

* `setex(key ,seconds,value)` 设置值得同时设置过期时间

* `ttl key` 查看失效剩余时间

* 删除过期 `key` 策略

  * 消极方法 ，也叫懒惰删除 范围的时候判断是否过期

  * 积极方法，也叫主动删除 周期性从设置了过期时间`key` 中选择一部分的`key`进行删除

    * 通过 `hz` 设置每秒执行检查次数

    * 随机测试20个带有`timeout`信息的`key`

    * 如果超过`25%`被删除，则会重复执行整个删除流程

### 消息队列使用

### 持久化及原理分析

#### RDB

* `RDB` 也叫快照方式，当符合条件的时候 `fork`子进程，生成`dump.rdb`文件

* 配置规则 `save seconds changes` 保存对`redis` 进行多少次修改的`key` 

  ```properties
  save 900 1
  save 300 10
  save 60 10000
  ```

  *`900`秒之内对数据库进行了`1`次修改的`key`,只要满足以上三个条件都会执行一次保存*

* 执行保存命令

  1. `save`命令 会阻塞所有客户端请求

  2. `bgsave` 不会阻塞客户端请

  3. `flushall` 

  4. 执行复制操作

#### AOF

* `aof` 文件写入，只针对对数据变更的操作， `appendonly yes` 开启AOF

* 重写配置 

  ```properties
  ## 当目前的AOF文件超过上一次重写aof文件大小的百分之多少 默认 100%
  auto-aof-rewrite-percentage 100
  ## 重写大小  运行重写最小值
  auto-aof-rewrite-min-size 64mb
  
  ## 执行命令什么时候同步到aof文件中 ，默认是每一秒 everysec 每一秒 always 每次操作
  appendfsync everysec|always|no
  ```

* 重写会`fork`一个子进程

* 重写目的压缩`aof`文件，在某一个时间点把内存的指令重新生产`aof`文件

* 重写不一致 最后追加到`aof`文件之后

### 回收策略

* `LRU`  

* `redis.conf` 配置 `maxmemory-policy noeviction` 为默认配置

  * `allkeys-lru` 最少使用的数据去淘汰

  * `allkeys-random` 随机淘汰删除`key`

  * 设置了过期时间的`key`中选择删除

    * `volatile-random` 随机删除

    * `volatile-lru` 最少使用删除

    * `valatile-ttl` 即将过期的数据进行淘汰

### 单线程高性能

* 使用IO多路复用模型 （异步阻塞IO）

* 避免了多线程竞争

### lua脚本

* `eval` 使用 `eval script numkey key age `

  ```shell
  eval "return redis.call('set','demo','lua keys')" 0
  eval "return redis.call('set',KEYS[1],ARGV[1])" 1 user 名字
  ```

  *`1`代表多少个`key`*

* `redis-cli --eval myscript.lua key1 key2 , arg1 arg2 arg3 `

  * `lua脚本`

    ```lua
    local key ="retalimit:"..KEYS[1]
    local limit = tonumber(ARGV[1])
    local expireTime=tonumber(ARGV[2])
    local times = redis.call('incr',key)
    if times == 1 then
      redis.call('expire',key,expireTime)
    end
    if times > limit then
      return 0
    end
    return 1
    
    ```

  * 执行 

    ```shell
    /usr/local/redis/bin/redis-cli -p 7777 --eval retelimit.lua  127.0.0.1 , 10 10
    ```

* 在客户端如果脚本过大导致传输效率低

  * `script load script` 缓存脚本到服务端 输出 `SHA1` ，再通过 `evalsha SHA1 numkey key age`
  * `script Exists SHA1 ` 是否缓存指定的脚本
  *  `script KILL` 杀死正在运行的 `lua` 脚本
  * `script flush` 清除所有的`lua`脚本 





## Redis 主备与哨兵配置

### Redis主备（Master-Slaver）

* 关闭所有节点防火墙 并设置 `bind 0.0.0.0`

* 主`Master`

  ```propertie
   min-slaves-to-write 3
   min-slaves-max-lag 1
  ```

* 备`Slaver`设置连接主地址

  ```properties
  slaveof 192.168.204.135 7777
  ```

* 进入主备查看状态 `info replication`



### 哨兵配置

* 配置监控主 `sentinel monitor mymaster 192.168.204.135 7777 1`