



# Kubernetes

## Quickstart

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



### VirtualBox 安装

* [官网下载](https://download.virtualbox.org/virtualbox/6.0.2/VirtualBox-6.0-6.0.2_128162_el7-1.x86_64.rpm)

* `yum` 安装

  >cd /etc/yum.repos.d
  >wget http://download.virtualbox.org/virtualbox/rpm/rhel/virtualbox.repo
  >yum makecache
  >yum install VirtualBox-6.0 --nogpgcheck

  在安装过程中出现了`[Errno -1] Gpg Keys not imported, cannot verify repomd.xml for repo virtualbox` 问题 在安装后面添加 `--nogpgcheck`






