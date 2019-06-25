# Mysql 优化

**优化点 可以从业务这块来优化，然后就是并发，并发的话数据库连接数，数据库锁，在需求可以的情况下尽量使用隔离级别小的。然后就是数据库层面的缓存，配置缓存大小。然后就是`sql`优化主要点还是在索引 `join_buffer_size`，`sort_buffer_size`减少拷贝空间换时间**



## 初识Mysql体系结构

### 存储引擎

## 理解Mysql底层B+tree索引机制

[索引演示](https://www.cs.usfca.edu/~galles/visualization/Algorithms.html)

### 索引是什么

**索引是为了加速对表中数据行的检索而创建的一种分散存储数据结构**

### 为什么要使用索引

* 索引能极大的减少存储引擎需要扫描的数据量
* 索引可以把随机IO变成顺序IO
* 索引可以帮助我们在进行分组、排序等操作时，避免使用临时表

## 为什么是B+Tree

### 二叉查找树Binary Search Tree

![1560954301757](assets/1560954301757.png)

### 平衡二叉查找树

![1560954747567](assets/1560954747567.png)

### 使用二叉树弊端

* 它太深了
  * 数据处的（高）深度决定着他的IO操作次数，IO操作耗时大
* 它太小了
  * 每一个磁盘块（节点/页）保存的数据量太小了
  * 没有很好的利用操作磁盘`IO`的数据交换特效，页没有利用好磁盘`IO`的预读能力（空间局部性原理），从而带来频繁的`IO`操作



### 多路平衡查找树 ，B-Tree

![1560955542307](assets/1560955542307.png)



### 加强版多路平衡查找树 B+树

![1560955599709](assets/1560955599709.png)

### B+Tree 与B-Tree的区别

* B+节点关键字搜索采用闭合区间
* B+非叶节点不保存数据相关信息，只保存关键字和子节点的引用
* B+关键字对应的数据保存在叶子节点中
* B+叶子节点是顺序排列的，并且相邻节点具有顺序引用的关系

### 为什么选B+Tree 

* B+树是B-树的变种（PLUS版）多路绝对平衡查找树，他拥有B-树的优势
* B+树扫库、表能力更强
* B+树的磁盘读写能力更强
* B+树的排序能力更强
* B+树的查询效率更加稳定
  * 由于都需要找到末尾页子节点才能找到数据

## Mysql B+Treee 索引体系形式

### 索引-Myisam

![1560955897527](assets/1560955897527.png)

![1560955928285](assets/1560955928285.png)

### 索引-Innodb

![1560956002470](assets/1560956002470.png)

![1561041169040](assets/1561041169040.png)

* 聚集索引
  * 数据库表行中数据的物理顺序与键值的逻辑（索引） 顺序相同



### Innodb VS Myisam

![1561041017550](assets/1561041017550.png)



## 索引知识补充

### 列的离散性

* `count(distinct col) : count(col)`
* 越大离散性越好
* 离散性越高选择性就越好

### 最左匹配原则

* 对索引中关键字进行计算，一定是从左往右依次进行，且不可跳过

### 覆盖索引

* 如果查询列可通过索引节点中的关键字直接返回，则该索引称之为覆盖索引。覆盖索引可减少数据库`IO`，将随机`IO`变为顺序`IO`，可提高查询性能

## Mysql 插拔式的存储引擎





![1560954051633](assets/1560954051633.png)



### 存储引擎介绍

* 插拔式的插件方式
* 存储引擎是制定在表之上的，即一个库中的每一个表都可以制定专用的存储引擎
* 不管表采用什么样的方式的存储引擎，都会在数据区，产生对应的`frm`文件（表结构定义描述文件）

### CSV存储引擎

* 数据存储以`CSV`文件

* 特点

  * 不能定义没有索引、列定义必须为`NOT NULL` 、不能设置自增列
  * 不适合大表或者数据的在线处理

  * `CSV` 数据存储用`,`隔开，可直接编辑`CSV`文件进行数据的编排

  * 数据安全性低

    **编辑之后，要生效使用`flush tables XXX` 命令**

* 应用场景

  * 数据的快速导入导出
  * 表直接转换成CSV

### Archive存储引擎

* 压缩协议进行数据存储
* 数据存储为`ARZ`文件格式
* 特点
  * 只支持`insert`和`select`两种操作
  * 只允许自增`ID`列建立索引
  * 行级锁
  * 不支持事物
  * 数据占用磁盘少
* 应用场景
  * 日志系统
  * 大量的设备数据采集

### Memory存储引擎

* 数据都是存储在内存中，IO效率要比其他引擎高很多，服务重启数据丢失，内存数据表默认只有16M
* 特点
  * 只支持`hash`索引，`B Tree`索引，默认`hash`（查找复杂度O(1)）
  * 字段长度都是固定长度varchar(32)=char(32)
  * 不支持大数据存储类型字段如：`blog`，`text`
  * 表级锁
* 应用场景
  * 等值查找热度较高的数据
  * 查询结果内存中的计算，大多数都是采用这种存储引擎作为临时表存储需计算的数据

### Myisam存储引擎

* `Mysql5.5`版本之前的默认存储引擎

* 较多的系统表也还是使用这个存储引擎

* 系统临时表也会用到`Myisam`存储引擎

* 特点

  * `select count(*) from table`无需进行数据的扫描
  * 数据`MYD`和索引`MYI`进行分开存储
  * 表级锁
  * 不支持事务

  

### Innodb存储引擎

* `Mysql5.5`及以后版本的默认存储引擎
* 特点
  * 事务`ACID`
  * 行级锁
  * `聚集索引(主键索引)方式进行数据存储`
  * 支持外键关系保证数据完整性

## Mysql体系结构及运行机理

### Mysql体系结构

* `Client Connectors` 接入方支持协议很多
* `Management Services & Utilities`
  * 系统管理和控制工具`mysqldump`，`mysql`复制集群、分区管理等
* `Connection Poll`
  * 连接池：管理缓冲用户连接、用户名、密码、权限校验、线程处理等需要缓存的需求
* `SQL Interface`
  * `sql`接口：接受用户的`SQL`命令，并且返回用户需要查询的结果
* `Parser`
  * 解析器，SQL命令传递到解析器的时候会被解析器验证和解析。解析器是由`Lex`和`YACC`实现的
* `Optimizer`
  * 查询优化器，`SQL`语句在查询之前会使用查询优化器对查询进行优化
* `Cache`和`Buffer`高速缓存区
  * 查询缓存，如果查询缓存有命中的查询结果，查询语句就可以直接去查询缓存中取数据
* `pluggable storage Engines`
  * 插件式存储引擎。存储引擎是`Mysql` 中具体与文件打交道的子系统
* `file system`
  * 文件系统，数据，日志（`redo`,`undo`），索引，错误日志、查询记录，慢查询等





## Mysql查询优化详解

### Mysql 查询优化-查询执行的路径

1. `mysql` 客户端/服务端通信
2. 查询缓存
3. 查询优化处理
4. 查询执行引擎
5. 返回客户端

### Mysql客户端/服务端通信

* `mysql`客户端与服务端的通信方式是`半双工`
  * 全双工：双向通信，发送同时也可以接收
  * 半双工：双向通信，同时只能接收或者发送，无法同时做操作
  * 单工：只能单一方向传送
* 半双工通信
  * 在任何一个时刻，要么是由服务端向客户端发送数据，要么是客户端向服务端发送数据，这两个动作不能同时发生。所以我们无法也无需将一个小小切成小块进行传输
* 特点和限制
  * 客户端一旦开始发送消息，另一端要接收完整个消息才能响应。
  * 客户端一旦开始接收数据没法停下来发送指令。

### mysql 客户端/服务端通信-查询状态

* `show status like 'table%'` 表级锁的争用状态变量
* `show status like 'innodb_row_lock%' ` 行级锁争用状态变量

* `show variables like'max_connections'` 查看当前最大连接数

  * `set global max_connections=1000` 设置最大的连接数

* `show processlist`

* `show full processlist`

* `show status like '%Uptime'` 

  >a. show tables或show tables from 'database_name'; -- 显示当前数据库中所有表的名称。
  >b. show databases; -- 显示mysql中所有数据库的名称。
  >c. show columns from table_name from 'database_name'; 或show columns from database_name.table_name; -- 显示表中列名称。
  >d. show grants for user_name; -- 显示一个用户的权限，显示结果类似于grant 命令。
  >e. show index from table_name; -- 显示表的索引。
  >f. show status; -- 显示一些系统特定资源的信息，例如，正在运行的线程数量。
  >g. show variables; -- 显示系统变量的名称和值。
  >h. show processlist; -- 显示系统中正在运行的所有进程，也就是当前正在执行的查询。大多数用户可以查看他们自己的进程，但是如果他们拥有process权限，就可以查看所有人的进程，包括密码。
  >i. show table status; -- 显示当前使用或者指定的database中的每个表的信息。信息包括表类型和表的最新更新时间。
  >j. show privileges; -- 显示服务器所支持的不同权限。
  >k. show create database database_name; -- 显示create database 语句是否能够创建指定的数据库。
  >l. show create table table_name; -- 显示create database 语句是否能够创建指定的数据库。
  >m. show engies; -- 显示安装以后可用的存储引擎和默认引擎。
  >p. show warnings; -- 显示最后一个执行的语句所产生的错误、警告和通知。
  >q. show errors; -- 只显示最后一个执行语句所产生的错误。

### 查询缓存

* `show variables like 'query_cache%'` 查看缓存开启情况

  * `quer_cache_type`  
    * `0` 关闭
    * `1` 开启
    * `2` 按需开启

* `show status like 'Qcache%'` 缓存情况 

  * `Qcache_hits` 缓存命中次数
  * `Qcache_inserts` 缓存插入条数

  **`select sql_no_cache * from users where id = 33`** 表示不走缓存

## 执行计划

`EXPLAIN`

### 执行计划字段说明

* `table`

  执行的是哪一张表 或者是别名

* `type` 

  访问类型，`sql` 查询好坏的一个重要指标，结果值从好到坏依次

  * `system` : 表只有一行记录
  * `const` : 表示通过索引一次就找到了，`const` 用于比较`primary key` 或者 `unique` 索引
  * `eq_ref` ：唯一索引扫描，对于每个索引，表中只有一条记录与之匹配。常见于主键或唯一索引扫描
  * `ref` : 非唯一性索引扫描，返回匹配某个单独值的所有行，本质是也是一种索引访问
  * `range`：只检索给定范围的行，使用一个索引来选择行
  * `index`：Full Index Scan，索引全表扫描，把索引从头到尾扫一遍
  * `ALL`：Full Table Scan，遍历全表以找到匹配的行 

* `possible_keys`

  查询过程中有可能用到的索引

* `key`

  实际使用的索引，如果为`null` 则没有使用索引

* `rows`

  根据表统计信息或者索引选用情况，大致估算出找到所需的记录所需要读取的行
  数

* `filtered`

  它指返回结果的行占需要读到的行(rows列的值)的百分比表示返回结果的行数占需读取行数的百分比，filtered的值越大越好

* `extra`

  重要的额外信息

  * `Using filesort` ：mysql对数据使用一个外部的文件内容进行了排序，而不是按照表内的索引进行排序读取
  * `Using temporary`：使用临时表保存中间结果，也就是说mysql在对查询结果排序时使用了临时表，常见于order by 或 group by
  * `Using index`：表示相应的select操作中使用了覆盖索引（Covering Index），避免了访问表的数据行，效率高
  * `Using where` ：使用了where过滤条件
  * `select tables optimized away`：基于索引优化MIN/MAX操作或者`MyISAM`存储引擎优化COUNT(*)操作，不必等到执行阶段在进行计算，查询执行计划生成的阶段即可完成优化







## 事务

**数据库操作的最小工作单元，是作为单个逻辑工作单元执行的一系列操作；
事务是一组不可再分割的操作集合（工作逻辑单元）**

* `Mysql` 如何开启事务

  * 手动开启事务

    ```sql
    begin/start transaction
    ```

  * 事务提交或者回滚

    ```sql
    commit / rollback
    ```

  * 事务是否自动开启

    ```sql
    set session autocommit = on/of
    ```

  * 查询事务是否自动开启`SELECT @@autocommit;`

  * 示例

    ```sql
    set session autocommit = OFF;   -- 第一步
    SELECT @@autocommit;   -- 第二步
    start transaction -- 第三步
    UPDATE t_article SET `status` = 3 WHERE id = 1  -- 第四部
    COMMIT  -- 第五步
    ```

    

### 事务ACID 特性

* 原子性（`atomicity`）

  最小的工作单位，整个工作单元要么一起提交，要么全部失败回滚

* 一致性 （`Consistency`）

  事务中操作的数据及状态改变是一致的，即写入资料的结果必须完全符合预设的规则，不会因为出现系统的意外等原因导致状态的不一致

  **原子性和一致性的区别在于，比如转账，A转账给B 100元。A扣除成功和B收入成功 ，或者是A扣除失败和B收入失败，这叫原子性。A扣除100元，B收入100元，这叫一致性**

* 隔离型（`Isolation`）

  一个事务所操作的数据在提交之前，对其他事务的可见性设定

* 持久化`Durability`

  事务所做的修改就会永远保存，不会因为意外导致数据的丢失



### 事务带来哪些并发问题

* 脏读（`read_uncommitted`）

  当一个事务可以读取到，其他事务没有提交的数据

* 不可重复读（`read_committed`）

  两个事务  a 事务读取一条数据，b 事务修改了这条数据且提交了，a再去读这条数据的时候 两次读出来的不一样

* 幻读/可重复读（`repeatable read`）

  假设两个事务 a 事务读取一条数据，b 事务对该条数据进行了删除且提交了，a再去读这条数据的时候 还能读出来

### 获取当前事务隔离级别

1. 查看当前会话隔离级别

   `select @@tx_isolation;`

2. 查看系统当前隔离级别

   `select @@global.tx_isolation;`

3. 设置当前会话隔离级别

   `set session transaction ISOLATION level repeatable read;`

4. 设置系统当前隔离级别

   `set global transaction ISOLATION level repeatable read;`

## 锁

### 共享锁(行锁) ：`shared locks`

又称读锁，只能读不能修改

>begin
>
>SELECT * FROM inquiry WHERE linkman = '小雪' LOCK in SHARE MODE;
>
>commit/rollback

后面条件匹配的所有数据都会上锁，只能查不能改，需要等待该事务完成

### 排他锁(行锁)：`Exclusive locks`

>BEGIN
>
>SELECT * FROM user_info WHERE username='钱十' for UPDATE;
>
>ROLLBACK
>
>-------------------------------------------------------
>
>SELECT * from user_info WHERE username = '钱九' for  UPDATE;
>
>
>SELECT * from user_info WHERE username = '钱十' lock in share mode;
>
>
>SELECT * from user_info WHERE username = '钱九'

在`username` 没有索引的时候，锁的是全部行，添加索引只锁匹配数据行

### 行锁到底锁了什么

* InnoDB的行锁是通过给索引上的索引项加锁来实现的

* 只有通过索引条件进行数据检索，`Innodb` 才会使用行级锁，否则`Innodb` 将使用表锁（锁住索引的所有记录），除了数字区间外

* 手动上表锁

  `lock tables 表名 read/write`

### 意向锁共享锁（表锁）： Intention Shared Locks

### 意向锁排它锁（表锁）：Intention Exclusive Locks



### 事物和锁Mysql存储的关系

**到`information_schema`库中查一下表**

* `innodb_trx` 当前运行的所有事务
* `innodb_locks` 当前出现的锁
* `innodb_lock_waits` 锁等待的对饮关系


















































