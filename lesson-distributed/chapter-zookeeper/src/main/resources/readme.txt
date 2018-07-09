


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