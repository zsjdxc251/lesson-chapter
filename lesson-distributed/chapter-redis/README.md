# Redis

## Quickstart

### 安装

* 解压`tar`包 ,进入文件夹 `make ` 编译

  ```sh
  $ make PREFIX=/usr/local/redis install
  ```

  *编译依赖于`c++`  所以先执行 `yum install gcc ` & `yum install gcc-c++ ` | `yum install tcl `*

* 运行 `./bin/redis-server redis.conf `

## 课程目录

1. **分布式缓存技术的应用**
2. **Redis 的魅力**
3. **Redis 安装指引**
4. **Redis 的数据类型及常用命令**
5. **Redis 各个数据类型的底层数据结构简述**
6. **过期时间设置**
7. **消息队列的使用**

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

    







