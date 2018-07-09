


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




集群配置
 配置3台
 server.1=192.168.23.128:2888:3888
 server.2=192.168.23.129:2888:3888
 server.3=192.168.23.130:2888:3888
 每台添加~/dataDir/myid 文件