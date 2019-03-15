http.cors.enabled: true #允许跨域
http.cors.allow-origin: "*"Elasticsearch

## Quickstart

1. 安装 `elasticsearch-6.5`

2. 启动 `./bin/elasticsearch`

3. 修改可跨域访问 `config/elasticsearch.yml`

   ```yaml
   http.cors.enabled: true #允许跨域
   http.cors.allow-origin: "*"
   ```

4. `./bin/elasticsearch -h` 查看帮助命令

* 启动错误时

  ```shell
  echo "* soft nofile 65536" >> /etc/security/limits.conf
  echo "* hard nofile 65536" >> /etc/security/limits.conf
  echo "* soft memlock unlimited" >> /etc/security/limits.conf
  echo "* hard memlock unlimited" >> /etc/security/limits.conf
  echo "vm.max_map_count = 262144" >> /etc/sysctl.conf
  sysctl -p
  ulimit -l unlimited
  ```
  
* 临时处理
  ```shell
  sysctl -w vm.max_map_count=262144
  ```
  
* 永久处理
  /etc/sysctl.conf文件最后添加一行
  ```shell
  vm.max_map_count=262144
  ```
  

### 集群配置

* `config/elasticsearch.yml`

  ```yaml
  cluster.name: es-first   ## 群名称
  node.name: es-slave-1     ## 节点名称
  node.master : false      ## 是否是主节点
  
  network.host: 0.0.0.0
  
  
  discovery.zen.ping.unicast.hosts: ["127.0.0.1"] #只有slave 节点才配置集群的IP 组，配置主节点IP 即可
  ```




## Elasticsearch UI 

* 在`github`上找到 `git@github.com:mobz/elasticsearch-head.git`
* 安装`nodejs`
* 安装 `grunt` -> `npm install -g grunt`
* 安装 `elasticsearch-head` 所需要模块 -> `rpm install`
* 启动 `npm run start &`



## kibana

### 基本操作

* 后台启动 `./kibana -l ../logs/kibana.log &`



## Elasticsearch 原理

### 分布式特性

* 节点启动 

  ```sh
  bin/elasticsearch -Ecluster.name=first_cluster -Epath.data=cluster_node1 -Enode.name=node1 -Ehttp.port=9201 -Enetwork.host=0.0.0.0 -Epath.logs=node1_logs -Ehttp.cors.enabled=true -Ehttp.cors.allow-origin="*" -d
  
  ```

* Cluster State

  * RestApi

    `GET _cluster/state`

* Coordingating Node

* 数据节点 Data Node

  * 默认情况下除了`master`节点以外都是`Coordingating NOde` 和 `Data Node`
  * 

### 副本和分片

* 分片

  如果需要添加吞吐量则添加节点

  如果需要添加容量则添加分片

* Cluster Health

  * RestApi
    * `_cluster/health`
  * `green` 健康状态，指所有主副分片都正常分配
  * `yellow` 指所有主分片都正常分配，但是副分片没有正常分配
  * `red` 有主分片未分配



### 故障转移

* 如果挂了某个节点为`yellow` 状态 `es` 会自动分配，然后变成 `gree` 状态
* 如果`master` 挂了会选举一个`master` 节点出来



### 脑裂问题

假设3台实例，`master` 挂了，后续两台中会选举出一个`master` 节点出来，但是后续`old master` 又起来了，是否需要继续成为`master` ，如果发现有2个节点存活就不会再去选举`master`

* `config` 配置

  `discovery.zen.minimum_master_nodes: `



### 分布式文件存储

* 文档到分片的映射算法

  shard = hash(routing) % number_of_primary_shards

  * hash 算法保证可以将数据均匀地分散在分片中
  * routing是一个关键参数，默认是文档id,也可以自行指定
  * number_of_primary_shards 是主分片数

  **该算法与主分片数相关，这也是分片数一旦确定后便不能更改**


* 文档写入流程

  * 客户端请求节点`hash` 计算在哪个分片上`index` ，在请求到指定`index` 节点上，然后把数据同步到副本中，副本响应给主分片，主分片在依次响应回去

* 文档读取流程

  * 客户端请求到某个节点，通过routing 计算该文档应该存储在某个节点上，查询`cluster state` 后获取 

    节点的主副分片列表，然后以轮询的机制获取一个`shard`发起请求

### shard 分片











## logstash

### quickstart

* 配置控制台输出 `../bin/logstash -f logstash.conf -r`

  ```json
  input {
      stdin {}
  }
  
  
  output {
  
      stdout {
  
  
      }
  }
  ```































































