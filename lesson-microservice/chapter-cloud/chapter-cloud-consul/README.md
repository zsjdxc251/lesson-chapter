# Consul



## 启动相关配置

* `-config-dir` 

  * 就是指定加载置文件的目录，我们只需要填写配置文件的目录就可以帮助我们把该目录下所有的以.json结尾配置文件加载进去，它的加载顺序是根据26个字母的顺序加进行加载配置文件的。文件内容都是json格式的数据。默认后面文件定义配置会覆盖前面文件定义的配置

  * ```json
    {
    
        "ports": {
    
        "http": 8501 , //consul web UI
    
        "dns": 8601,
    
        "rpc": 8401,
    
        "serf_lan": 8311,
        
        "serf_wan": 8312,
    
        "server": 8310
    
        }
    
    }
    ```

  * ```shell
    ./consul -config-dir /basic.json
    ```

* `-bind`

  * 该地址用来在集群内部的通讯，集群内的所有节点到地址都必须是可达的，默认是`0.0.0.0`

* `-node`

  * 节点在集群中的名称，在一个集群中必须是唯一的，默认是该节点的主机名

* `-server` 对应`-dev`

  * 定义`agent`运行在`server`模式

* `-bootstrap-expect`

  * 在一个datacenter中期望提供的server节点数目，当该值提供的时候，consul一直等到达到指定sever数目的时候才会引导整个集群，该标记不能和bootstrap共用

* `-rejoin`

  * 使consul忽略先前的离开，在再次启动后仍旧尝试加入集群中

* `-client`

  * consul服务侦听地址，这个地址提供HTTP、DNS、RPC等服务，默认是127.0.0.1所以不对外提供服务，如果你要对外提供服务改成`0.0.0.0`

* `-join`

  * 加入到集群 
  * `consul -join 162.21.35.52`

* `-data-dir` 

  * 指定数据目录

* `-dc`





## 启动后相关命令

* `members`

  * 可以看到Consul集群的成员
  * `consul members`
* `join`
  * 加入集群
  * `consul join 10.201.11.19`
* `reload`
  * 更新服务
  * `consul reload`

