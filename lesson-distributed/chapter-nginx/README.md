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





