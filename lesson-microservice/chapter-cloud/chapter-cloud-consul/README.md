# Consul



## 启动相关配置

* `-config-dir` 

  * 就是指定加载置文件的目录，我们只需要填写配置文件的目录就可以帮助我们把该目录下所有的以.json结尾配置文件加载进去，它的加载顺序是根据26个字母的顺序加进行加载配置文件的。文件内容都是json格式的数据。默认后面文件定义配置会覆盖前面文件定义的配置

  * ```json
    {
    
        "ports": {
    
        "http": 8501 , ##consul web UI
    
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
  * `-client 0.0.0.0`

* `-join`

  * 加入到集群 
  * `consul -join 162.21.35.52`

* `-data-dir` 

  * 指定数据目录

* `-dc`

>acl_token：agent会使用这个token和consul server进行请求
>acl_ttl：控制TTL的cache，默认是30s
>addresses：一个嵌套对象，可以设置以下key：dns、http、rpc
>advertise_addr：等同于-advertise
>bootstrap：等同于-bootstrap
>bootstrap_expect：等同于-bootstrap-expect
>bind_addr：等同于-bind
>ca_file：提供CA文件路径，用来检查客户端或者服务端的链接
>cert_file：必须和key_file一起
>check_update_interval：
>client_addr：等同于-client
>datacenter：等同于-dc
>data_dir：等同于-data-dir
>disable_anonymous_signature：在进行更新检查时禁止匿名签名
>enable_debug：开启debug模式
>enable_syslog：等同于-syslog
>encrypt：等同于-encrypt
>key_file：提供私钥的路径
>leave_on_terminate：默认是false，如果为true，当agent收到一个TERM信号的时候，它会发送leave信息到集群中的其他节点上。
>log_level：等同于-log-level node_name:等同于-node
>ports：这是一个嵌套对象，可以设置以下key：dns(dns地址：8600)、http(http api地址：8500)、rpc(rpc:8400)、serf_lan(lan port:8301)、serf_wan(wan port:8302)、server(server rpc:8300)
>protocol：等同于-protocol
>rejoin_after_leave：等同于-rejoin
>retry_join：等同于-retry-join
>retry_interval：等同于-retry-interval
>server：等同于-server
>syslog_facility：当enable_syslog被提供后，该参数控制哪个级别的信息被发送，默认Local0
>ui_dir：等同于-ui-dir

* `-ui`
* 





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