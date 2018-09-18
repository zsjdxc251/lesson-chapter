

ACL
  CREATE、READ、WRITE、DELETE、ADMIN 也就是 增、删、改、查、管理权限，这5种权限简写为crwda(即：每个单词的首字符缩写)

  注：这5种权限中，delete是指对子节点的删除权限，其它4种权限指对自身节点的操作权限

  world：默认方式，相当于全世界都能访问
     world, anyone
  auth：代表已经认证通过的用户(cli中可以通过addauth digest user:pwd 来添加当前上下文中的授权用户)
     auth , ""  (不使用任何id，代表任何已认证的用户)
  digest：即用户名:密码这种方式认证，这也是业务系统中最常用的
     digest , DigestAuthenticationProvider.generateDigest("用户名:密码")
  ip：使用Ip地址认证 它对应的id为客户机的IP地址，设置的时候可以设置一个ip段，比如ip:192.168.1.0/16, 表示匹配前16个bit的IP段
     ip , 192.168.1.0/16

  super: 在这种scheme情况下，对应的id拥有超级权限，可以做任何事情(cdrwa）

Stat
  cZxid：这是导致创建znode更改的事务ID。
  mZxid：这是最后修改znode更改的事务ID。
  pZxid：这是用于添加或删除子节点的znode更改的事务ID。
  ctime：表示从1970-01-01T00:00:00Z开始以毫秒为单位的znode创建时间。
  mtime：表示从1970-01-01T00:00:00Z开始以毫秒为单位的znode最近修改时间。
  dataVersion：表示对该znode的数据所做的更改次数。
  cversion：这表示对此znode的子节点进行的更改次数。
  aclVersion：表示对此znode的ACL进行更改的次数。
  ephemeralOwner：如果znode是ephemeral类型节点，则这是znode所有者的 session ID。 如果znode不是ephemeral节点，则该字段设置为零。
  dataLength：这是znode数据字段的长度。
  numChildren：这表示znode的子节点的数量。

  cZxid = 0x0
  ctime = Thu Jan 01 08:00:00 CST 1970
  mZxid = 0x0
  mtime = Thu Jan 01 08:00:00 CST 1970
  pZxid = 0x0
  cversion = 0
  dataVersion = 0
  aclVersion = 0
  ephemeralOwner = 0x0
  dataLength = 0
  numChildren = 0



临时节点不能添加子节点

PERSISTENT - 持久化目录节点：客户端与zookeeper断开连接后，该节点依旧存在
PERSISTENT_SEQUENTIAL - 持久化顺序编号目录节点：客户端与zookeeper断开连接后，该节点依旧存在，只是Zookeeper给该节点名称进行顺序编号
EPHEMERAL - 临时目录节点：客户端与zookeeper断开连接后，该节点被删除
EPHEMERAL_SEQUENTIAL - 临时顺序编号目录节点：客户端与zookeeper断开连接后，该节点被删除，只是Zookeeper给该节点名称进行顺序编号

Watch

应用场景
  注册中心
  配置中心
  负载均衡
  分布式锁
  Leader选举




集群配置
 配置3台
 server.1=192.168.23.128:2888:3888
 server.2=192.168.23.129:2888:3888
 server.3=192.168.23.130:2888:3888
 每台添加~/dataDir/myid 文件

 zxid,epoch,myid


ZAB 协议
ZooKeeper Atomic Broadcast



