默认使用协议，支持多协议
且指定协议

消费端：
  如果provider提供多个协议可以指定协议消费



  支持协议
  默认 dubbo
          缺省协议，使用基于mina1.1.7+hessian3.2.1的tbremoting交互。
          连接个数：单连接
          连接方式：长连接
          传输协议：TCP
          传输方式：NIO异步传输
          序列化：Hessian二进制序列化
          适用范围：传入传出参数数据包较小（建议小于100K），消费者比提供者个数多，单一消费者无法压满提供者，尽量不要用dubbo协议传输大文件或超大字符串。
          适用场景：常规远程服务方法调用
       hessian
          基于Hessian的远程调用协议。
          连接个数：多连接
          连接方式：短连接
          传输协议：HTTP
          传输方式：同步传输
          序列化：表单序列化
          适用范围：传入传出参数数据包大小混合，提供者比消费者个数多，可用浏览器查看，可用表单或URL传入参数，暂不支持传文件。
          适用场景：需同时给应用程序和浏览器JS使用的服务。
       rmi
          Java标准的远程调用协议。
          连接个数：多连接
          连接方式：短连接
          传输协议：TCP
          传输方式：同步传输
          序列化：Java标准二进制序列化
          适用范围：传入传出参数数据包大小混合，消费者与提供者个数差不多，可传文件。
          适用场景：常规远程服务方法调用，与原生RMI服务互操作
       http
          基于http表单的远程调用协议。参见：[HTTP协议使用说明]
          连接个数：多连接
          连接方式：短连接
          传输协议：HTTP
          传输方式：同步传输
          序列化：表单序列化
          适用范围：传入传出参数数据包大小混合，提供者比消费者个数多，可用浏览器查看，可用表单或URL传入参数，暂不支持传文件。
          适用场景：需同时给应用程序和浏览器JS使用的服务。
       webservice
          基于WebService的远程调用协议。
          连接个数：多连接
          连接方式：短连接
          传输协议：HTTP
          传输方式：同步传输
          序列化：SOAP文本序列化
          适用场景：系统集成，跨语言调用
       thrift
          当前 dubbo 支持的 thrift 协议是对 thrift 原生协议的扩展，在原生协议的基础上添加了一些额外的头信息，比如service name，magic number等。使用dubbo thrift协议同样需要使用thrift的idl compiler编译生成相应的java代码，后续版本中会在这方面做一些增强
       memcached
       redis



       <dubbo:protocol name=“dubbo” port=“9090” server=“netty” client=“netty” codec=“dubbo”
       serialization=“hessian2” charset=“UTF-8” threadpool=“fixed” threads=“100” queues=“0” iothreads=“9”
       buffer=“8192” accepts=“1000” payload=“8388608” />