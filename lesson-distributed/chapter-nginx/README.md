# Nginx

[TOC]



## Quickstart

### 安装

* `./configure --prefix=/usr/local/nginx`

* 依赖

  ```shell
  yum install pcre-devel / zlib-devel
  ```

* `make && make install `

* 添加模块 

  ```shell
  ./configure --prefix=/usr/local/nginx --with-http_stub_status_module  // prefix必须要和之前一样
  make // 必须先执行make
  cp objs/nginx nginx/sbin/nginx  // 替换原来的nginx
  ```

* 简单配置

  ```nginx
  upstream tomcat{
    server localhost:8080;
  }
  
  server {
     listen       80;
     server_name  192.168.11.132;
  
     location / {
        proxy_pass http://tomcat;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_next_upstream error timeout http_500;
  
        proxy_connect_timeout 60s;
        proxy_send_timeout 60s;
        proxy_read_timeout 60s;
       
      
     }
     location ~ .*\.(js|css|png|svg|ico|jpg)$ {
        accesskey    on;
        accesskey_hashmethod md5;
        accesskey_arg   "key";
        accesskey_signature  "mypass$remote_addr";
        
        #valid_referers none blocked 192.168.112.133;
        #if ($invalid_referer) {
        #    return 404;
        # }
        #root              static-resource;
        #expires 1h;  
  
    } 
     
  
  }
  ```

  


### 基本命令

* 启动 `./nginx `
* 关闭`./nginx -s stop`
* 重启 `./nginx -s reload`



## 课程目录

1. **Nginx在分布式架构中所处的位置和作用**
2. 正向代理和反向代理
3. 常见的web服务器
4. Nginx的安装部署
5. Nginx配置文件分析
6. location匹配规则
7. 实际使用建议
8. Rewrite规则
9. Nginx反向代理
10. Nginx的负载均衡实践
11. Nginx动静分离实践
12. Nginx进程模型
13. Nginx的高可用方案



## Nginx的高可用方案

### Keepalived

>轻量级的高可用解决方案
>
>LVS四层负载均衡软件（linux virtual server）
>
>监控lvs集群系统中的各个服务节点的状态
>
>VRRP协议（学你路由冗余协议）
>
>linux2.4以后，是内置在linux内核中的
>
>lvs(四层) ->HAproxy 七层应用层
>
>lvs(四层)->Nginx七层

#### 安装

1. keepalived-2.0.7.tar
2. ./configure --prefix =    --system

## 衍生问题

### TPS和QPS

### Nginx 防盗链原理

* 根据请求头`referer` 过滤

### 服务代理概念

* **正向代理** 代理服务器就是代理客户端去请求客户端需要请求的域名地址
  * 是针对客户端的
  * 是由客户端主动请求代理服务器，代理请求域名地址，被代理到的服务器是不知道客户端请求的存在，只知道代理服务器的存在
* **反向代理** 代理服务器通过客户端的请求自己转发到其他域名和地址
  * 是针对服务端的
  * 是由服务主动请求转发到指定域名地址，客户端是不知道被转发到的服务器的存在，只知道代理服务器的存在，客户端是不知道被代理了





