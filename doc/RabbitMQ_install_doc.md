## 1. 安装依赖环境

在线安装依赖环境：

```shell
yum install build-essential openssl openssl-devel unixODBC unixODBC-devel make gcc gcc-c++ kernel-devel m4 ncurses-devel tk tc xz

```



## 2. 安装Erlang

[上传](./rabbitmq)

- `erlang-18.3-1.el7.centos.x86_64.rpm`
- `socat-1.7.3.2-5.el7.lux.x86_64.rpm`
- `rabbitmq-server-3.6.5-1.noarch.rpm`

```sh
# 安装
rpm -ivh erlang-18.3-1.el7.centos.x86_64.rpm
```

> 如果出现如下错误
>
> ![1565526174751](./assets/1565526174751.png)
>
> 说明gblic 版本太低。我们可以查看当前机器的gblic 版本
>
> ```shell
> strings /lib64/libc.so.6 | grep GLIBC
> ```
>
> ![1565526264426](./assets/1565526264426.png)
>
> 当前最高版本2.12，需要2.15.所以需要升级glibc
>
> - 使用yum更新安装依赖
>
>   ```shell
>   sudo yum install zlib-devel bzip2-devel openssl-devel ncurses-devel sqlite-devel readline-devel tk-devel gcc make -y
>   ```
>
> - 下载rpm包
>
>   ```sh
>   wget http://copr-be.cloud.fedoraproject.org/results/mosquito/myrepo-el6/epel-6-x86_64/glibc-2.17-55.fc20/glibc-utils-2.17-55.el6.x86_64.rpm &
>   wget http://copr-be.cloud.fedoraproject.org/results/mosquito/myrepo-el6/epel-6-x86_64/glibc-2.17-55.fc20/glibc-static-2.17-55.el6.x86_64.rpm &
>   wget http://copr-be.cloud.fedoraproject.org/results/mosquito/myrepo-el6/epel-6-x86_64/glibc-2.17-55.fc20/glibc-2.17-55.el6.x86_64.rpm &
>   wget http://copr-be.cloud.fedoraproject.org/results/mosquito/myrepo-el6/epel-6-x86_64/glibc-2.17-55.fc20/glibc-common-2.17-55.el6.x86_64.rpm &
>   wget http://copr-be.cloud.fedoraproject.org/results/mosquito/myrepo-el6/epel-6-x86_64/glibc-2.17-55.fc20/glibc-devel-2.17-55.el6.x86_64.rpm &
>   wget http://copr-be.cloud.fedoraproject.org/results/mosquito/myrepo-el6/epel-6-x86_64/glibc-2.17-55.fc20/glibc-headers-2.17-55.el6.x86_64.rpm &
>   wget http://copr-be.cloud.fedoraproject.org/results/mosquito/myrepo-el6/epel-6-x86_64/glibc-2.17-55.fc20/nscd-2.17-55.el6.x86_64.rpm &
>   ```
>
>   
>
> - 安装rpm包
>
>   ```shell
>   sudo rpm -Uvh *-2.17-55.el6.x86_64.rpm --force --nodeps
>   ```
>
>   
>
> - 安装完毕后再查看glibc版本,发现glibc版本已经到2.17了
>
>   ```shell
>   strings /lib64/libc.so.6 | grep GLIBC
>   ```
>
>
> ![1565528746057](./assets/1565528746057.png)





## 3. 安装RabbitMQ

```sh
# 安装
rpm -ivh socat-1.7.3.2-5.el7.lux.x86_64.rpm

# 安装
rpm -ivh rabbitmq-server-3.6.5-1.noarch.rpm
```



## 4. 开启管理界面及配置

```sh
# 开启管理界面
rabbitmq-plugins enable rabbitmq_management
# 修改默认配置信息
vim /usr/lib/rabbitmq/lib/rabbitmq_server-3.6.5/ebin/rabbit.app 
# 比如修改密码、配置等等，例如：loopback_users 中的 <<"guest">>,只保留guest
```




## 5. 启动

```sh
service rabbitmq-server start # 启动服务
service rabbitmq-server stop # 停止服务
service rabbitmq-server restart # 重启服务
```

- 设置配置文件

```shell
cd /usr/share/doc/rabbitmq-server-3.6.5/
cp rabbitmq.config.example /etc/rabbitmq/rabbitmq.config
```

- 访问`ip:15672`即可，初始账号和密码都是 `guest`
- 可以自行设置`Admin`和`Virtual Host`
