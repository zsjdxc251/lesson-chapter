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
* 启动 `npm run start`



## kibana

### 基本操作

* 后台启动 `./kibana -l ../logs/kibana.log &`