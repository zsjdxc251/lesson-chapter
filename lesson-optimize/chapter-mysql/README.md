# Mysql 优化

**优化点 可以从业务这块来优化，然后就是并发，并发的话数据库连接数，数据库锁，在需求可以的情况下尽量使用隔离级别小的。然后就是数据库层面的缓存，配置缓存大小。然后就是`sql`优化主要点还是在索引 `join_buffer_size`，`sort_buffer_size`减少拷贝空间换时间**



## 初识Mysql体系结构

### 存储引擎

## 理解Mysql底层B+tree索引机制





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


















































