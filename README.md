# Socket Delivery Server

框架主要是为了提供一个可以负载大量socket tcp长连接的通讯服务。分为两部分delivery分发负载模块、socketServer通讯模块。
模块delivery依赖redis共享数据，提供双服务提高高可用性能；socketServer基于eureka负载可提供无上限的拓展支持，提供海量socket长连接请求业务。


使用说明见 [wiki](https://github.com/1991wangliang/sds/wiki)