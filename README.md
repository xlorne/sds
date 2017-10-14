# Socket Delivery Server


该框架是提供TCP Socket长连接的高可用方案。核心方案参考 ![socket负载均衡方案](https://github.com/1991wangliang/distribute-netty)。框架采用的核心技术框架：netty springcloud redis eureka


框架主要是为了提供一个可以负载大量socket tcp长连接的通讯服务。分为两部分delivery分发负载模块、socketServer通讯模块。
模块delivery依赖redis共享数据，提供双服务提高高可用性能；socketServer基于eureka负载可支持无上线的拓展，为提供海量socket长连接请求业务。


## SDS的架构图：

![](readme/sds.jpg)


## 目录介绍

delivery 分发服务库   
socketServer socket通讯服务库  
demo    演示案例  

框架将delivery与socketServer均以第三方库的方式做的封装。再使用时只需要依赖他们就可以。


## demo演示

详细请下载demo文件夹下的代码。

socketServer主要实现`SocketEventService`接口

delivery主要实现`DeliveryServerSendEventService`接口



## 启动说明

框架依赖eureka和redis，在启动项目之前要确保这两个服务已开启。

然后在运行两个demo。