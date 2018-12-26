

# Docker





## Quickstart

### 卸载

* 使用命令查找 需要卸载应用 `docker version`  找到

  > Package version: docker-1.13.1-75.git8633870.el7.centos.x86_64

* 使用`yum`移除 `yum -y remove docker-1.13.1-75.git8633870.el7.centos.x86_64`

* 彻底清除 `rm -rf /var/lib/docker/`

  >```shell
  >yum remove docker \
  >                  docker-client \
  >                  docker-client-latest \
  >                  docker-common \
  >                  docker-latest \
  >                  docker-latest-logrotate \
  >                  docker-logrotate \
  >                  docker-selinux \
  >                  docker-engine-selinux \
  >                  docker-engine
  >```
  >
  >find / -name docker



### 安装

1. >
   >```sh
   >yum install -y yum-utils \
   >  device-mapper-persistent-data \
   >  lvm2
   >```

2. >
   >```shell
   >yum-config-manager \
   >    --add-repo \
   >    https://download.docker.com/linux/centos/docker-ce.repo
   >```

3. >
   >```shell
   >yum install docker-ce
   >```

4. >```shell
   >systemctl start docker
   >```

5. > 如果不行则重启  
   > docker version

* 获取地址 `www.docker.com`, `www.docker-cn.com` ，`hub.docker.com`

### Docker 的组成

* 容器
* 镜像
* 仓库

### 基本命令

* 查看本地所有镜像 `docker image`

  * `-a` 列出所有的镜像

  * `-q` 只显示镜像ID

  * `--digests` 只显示摘要信息

  * `--no-trunc`
* 查看详细信息 `docker info`
* 帮助命令 `docker --help`
* 查找远程镜像 `docker search tomcat/centos/nginx` 

  * 查找STARS大于30的TOMCAT`docker search -s 30 tomcat`
* 拉取远程镜像到本地 `docker pull tomcat/centos/nginx`
* 删除镜像 `docker rmi `+ 镜像 ID

  * 删除单个 `docker rmi -f` 镜像名
  * 删除多个 `docker rmi`镜像名1:TAG1 镜像名2
  * 删除全部 `docker rmi -f $(docker images -q)`
* 新建容器并启动 `docker run [options] IMAGE [command] [ARG] `+ 镜像名

  * OPTIONS 
    * `--name`  为容器指定别名
    * `-d` 后台运行容器
    * `-i` 交换方式运行
    * `-t` 启动一个伪终端
    * `-p` 指定端口映射
    * `-P` 随机端口映射
  * 示例 
    * `docker run -it ef802ca71927` 启动容器
    * `docker run -it -p8888:8080 ef802ca71927` 指定端口号
    * `docker run -i -t -d --name tomcat1 ef802ca71927` 后台启动 且指定别名
    * `docker run -d -p 9999:8080 --name tomcat-run2 tomcat`
* 列出所有运行的容器 `docker ps [options]`

  * OPTIONS
    * `-a` 列出所有政治运行的容器+历史运行过的
    * `-l` 显示最近创建的容器
    * `-n` 显示最近 N个创建容器
    * `-q` 只显示容器ID
  * 示例
    * `docker ps -n 1` 显示1个容器
* 退出容器

  *  `exit` 退出并停止容器

  * `ctrl + p+ q` 退出不停止容器
* 启动容器 `docker start `容器ID 或者 容器名称
* 重启容器 `docker restart` 容器ID 或者 容器名称
* 停止容器 `docker stop` 容器ID 或者 容器名称
* 删除容器 `docker rm` 容器ID 或者 容器名称
* 强制停止容器 `docker kill` 容器ID 或者 容器名称
* 删除多个容器 

  * `docker rm -f $(docker ps -a -q)`
  * `docker ps -a -q|xargs docker rm`
* 后台启动 `docker run -d` 镜像ID或者镜像名称
* 查看日志 `docker logs -f -t --tail `容器ID
* 查看容器内进程 `docker top` 容器ID
* 查看容器详细信息 `docker inspect` 容器ID
* 进入正在运行的容器 并且前台方式运行

  * `docker exec -it `容器ID bashshell 产生新进程
    * `docker exec -it 89ec5f991a31 /bin/bash`
  * `docker attach`容器ID  进入容器，不产生新的进程
* 从容器内拷贝文件到主机
  * `docker cp ` 容器ID : 容器内路径 主机路径
    * `docker cp 89ec5f991a31:/usr/local/tomcat/webapps/ROOT/index.jsp /usr/`

* 提交容器
  * `docker commit -m='NAME' -a='作者'`容器ID 目标镜像名称:TAG

* 容器内安装 `vim`

  > apt-get update
  >
  > apt-get install vim










## 附加问题

* `docker ` 内启动 `tomcat` 慢

  修改 ` $JAVA_PATH/jre/lib/security/java.security `

  ```properties
  securerandom.source=file:/dev/random
  ```

  修改后

  ```properties
  securerandom.source=file:/dev/./urandom
  ```


## 



docker exec -it [] /bin/bash

## docker 提交 备份 导出 

`https://cr.console.aliyun.com/cn-hangzhou/mirrors` 阿里加速