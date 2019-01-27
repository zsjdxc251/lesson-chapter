



# Kubernetes

## Quickstart

>```sh
>yum makecache fast
>yum install -y kubelet kubeadm kubectl
>```

### kubectl 安装

* 可参考[官网](https://kubernetes.io/docs/tasks/tools/install-kubectl/#install-kubectl)

  官网的话涉及的需要科学上网，且在使用过程中 

  `/etc/yum.conf` 关闭`ssl` 验证

   ```shell
  sslverify=false
   ```

* [github安装](https://github.com/kubernetes/kubernetes/blob/master/CHANGELOG.md#client-binaries-1)

  * 选择版本

  >## Current release:
  >
  >- [CHANGELOG-1.13.md](https://github.com/kubernetes/kubernetes/blob/master/CHANGELOG-1.13.md)

  * 选择安装环境包

  > ### Client Binaries
  >
  > [kubernetes-client-linux-amd64.tar.gz](https://dl.k8s.io/v1.13.0/kubernetes-client-linux-amd64.tar.gz)

  * 通过`wget` 下载到本地
  * 解压 进入 看到 `kubectl` 文件
  * `mv kubectl /usr/local/bin/kubectl`
  * `kubectl version` 看到信息代表安装成功

### minikube 安装

* [github](https://github.com/kubernetes/minikube) 安装

  >### Linux
  >
  >```sh
  >curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 \
  >  && sudo install minikube-linux-amd64 /usr/local/bin/minikube
  >```

  此处涉及到科学上网则需要下载 `minikube-linux-amd64`

* 本地安装 

  下载好 `minikube-linux-amd64` 

  执行 `install minikube-linux-amd64 /usr/local/bin/minikube` 命令可安装



### kubeadm 安装

```sh
apt-get install -y kubelet kubeadm kubectl
```

```shell
 kubeadm init \
   --kubernetes-version=v1.13.0 \
   --pod-network-cidr=10.244.0.0/16 \
   --service-cidr 172.19.0.0/20 \
    --apiserver-advertise-address=192.168.61.11 \
   --ignore-preflight-errors=Swap \
   --token-ttl 0
```



### VirtualBox 安装

* [官网下载](https://download.virtualbox.org/virtualbox/6.0.2/VirtualBox-6.0-6.0.2_128162_el7-1.x86_64.rpm)

* `yum` 安装

  >cd /etc/yum.repos.d
  >wget http://download.virtualbox.org/virtualbox/rpm/rhel/virtualbox.repo
  >yum makecache
  >yum install VirtualBox-6.0 --nogpgcheck

  在安装过程中出现了`[Errno -1] Gpg Keys not imported, cannot verify repomd.xml for repo virtualbox` 问题 在安装后面添加 `--nogpgcheck`





## kubectl 基本使用

* `namespace`

  * 查看 `kubectl get namespaces`

  * 指定命名空间创建 

    `kubectl --namespace=<inssert-namespace-name-here> run nginx --image=nginx`

    `kubectl --namespace=<insert-namespace-name-here> get pods`

* `node`
  * 查看 `kubectl get nodes`
  * `kubectl taint nodes --all node-role.kubernetes.io/master-`
  * `kubectl get node --show-labels`
  * `kubectl label node nodeName key=value` 设置

* `pod`
  * 查看 `kubectl get pods`
  * 删除 `kubectl delete pod {podName}`
  * 查看详细信息 `kubectl describe pod {podName}`
  * 查看所有`pod` 情况 `kubectl get pod --all-namespaces -o wide`
  * 暴露服务 `kubectl expose pods nginx` 默认是`ClusterIP`

* `deployment`
  * 查看 `kubectl get deployments`
  * 查看历史 `kubectl rollout history deployment nginx-deployment`
  * 回滚 `kubectl rollout undo deployment nginx-deployment`

* `service`(`svc`)

  * 查看 `kubectl get services`
  * 缩写查看 `kubectl get svc`
  * 删除 `kubectl delete service {serviceName}`
  * 查看详细信息 `kubectl describe service {serviceName}`

* `ReplicationController`

  * 扩容 `kubectl scale rc nginx --replicas=5`

* `cvs` 证书

  * 查看`kubectl get csr`

* 查看集群状态 `kubectl cluster-info`

* 查看节点日志 `journalctl -f -u kubelet`

* 查看集群状态 `kubectl get cs`

* 端口转发 `kubectl port-forward nginx 8888:80`

## ymal 应用

### 简单应用

* `kubectl create -f nginx.yml`  创建一个 `pod`

  ```yaml
  apiVersion: v1
  kind: Pod
  metadata: 
    name: nginx1
    labels:
      app: nginx2
  spec:
    containers:
    - name: nginx3
      image: nginx
      ports:
      - containerPort: 80
  ```

  > NAME     READY   STATUS    RESTARTS   AGE     LABELS
  > nginx1   1/1     Running   0          6m44s   app=nginx2

### 副本创建

* 创建一个`ReplicationController`

  ```yaml
  apiVersion: v1
  kind: ReplicationController
  metadata:
    name : nginx1
  spec:
    replicas: 3
    selector:
      app: nginx2    # 必须和 labels.app一样
    template:
      metadata:
        name: nginx4
        labels:
          app: nginx2
      spec:
        containers: 
        - name: nginx6
          image: nginx
          ports:
          - containerPort: 80
  ```

  * `kubectl get rs`
  * `kubectl get rc` -> `ReplicationController` 缩写
  * `kubectl scale rc nginx1 --replicas=5` 扩容 ，也可以缩容 参数改小即可
  * `kubectl delete rc` 删除分片

### 修改版本及回退

* 创建一个 `Deployment`

  ```yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name : dp-nginx1
    labels:
      app: dp-nginx
  spec:
    replicas: 3
    selector:
      matchLabels:
        app: dp-nginx2
    template:
      metadata:
        name: dp-nginx4
        labels:
          app: dp-nginx2
      spec:
        containers: 
        - name: dp-nginx6
          image: nginx:1.12.2
          ports:
          - containerPort: 80
  ```

  此处会创建三个`pod` 

  * 查看 `kubectl get deployment`
  * 暴露端口 `kubectl expose deployment dp-nginx1 --type=NodePort --port 80`   
  * 查看`kubectl describe service serviceName` 详细信息  
  * `kubectl delete deployment my-nginx` 删除 `deployment`
  * `kubectl set image deployment dp-nginx1 dp-nginx6=nginx:1.13` 升级`nginx` 版本
  * `kubectl rollout history deployment dp-nginx1` 查看历史版本
  * `kubectl describe deployment dp-nginx1` 查看是否升级成功
  * 版本回退 `kubectl rollout undo deployment dp-nginx1`
  * `kubectl edit dployment dp-nginx1` 编辑 `dp-nginx1` 文件

##  进入POD

* `kubectl exec -it dp-nginx1-66d9568847-cm6qb sh`
* `kubectl exec -it dp-nginx1-66d9568847-dnzcv -c dp-nginx6 -- sh` 进入`pod` 对应的容器



## 额外命令

* 进入网卡 脚本 目录`cd /etc/sysconfig/network-scripts`

* `ip addr`
* `ip netns list` 查看网络命名空间
* `ip addr add namepsaceName`
* `ip netns exec namespaceName ip addr` 进入 命名空间
* `ip link` 查看网络设备
* `ip netns exec namespaceName ip link set dev lo up`
* `ip link add linkName1 type veth peer name linkName2`
* `ip link`
* `ip link set linkName netns namespaceName` 把 `link` 放入到命名空间内
* `ip link`
* `ip netns exec namespaceName ip addr`
* `ip netns exec namespaceName ip addr add 192.168.10.1/24 dev linkName` 添加 `ip`
* `ip netns exec namespaceName ip addr`
* `ip netns exec namespaceName ip link set dev linkName up` 启动
* `ip netns exec b2 ping 192.168.10.1` 在 2中是否能`ping` 通 1
* 安装 `yum install -y bridge-utils ` 
* `brctl show`

## Node

容器之间进行交互使用`pause` 容器

## Service

* `Service` 分为三类
  * `ClusterIP`
  * `NodePort`
  * `LadBalancer`









## 参考资料

https://blog.csdn.net/qq_34701586/article/details/78732470





https://www.kubernetes.org.cn/4956.html





http://blog.51cto.com/ylw6006/2071845



https://blog.csdn.net/huwh_/article/details/77922093
