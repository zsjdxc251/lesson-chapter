# RabbitMq

## Quickstart

* 后台启动 `rabbitmq-server start &`

* 停止 `rabbitmqctl app_stop`

* 配置信息

  ```shell
  vim /usr/lib/rabbitmq/lib/rabbitmq_server-xx/ebin/rabbit.app
  ```

  *打开`rabbitmq`用户配置，`loopback_users` 中的 `<<"guest">>` 放开*

* 启动管理插件

  ```sh
  ./rabbitmq-plugins enable rabbitmq_management
  ```

* 默认端口

  * `tcp`连接默认端口 `5672`

  * `web`端访问默认端口 `15672`



