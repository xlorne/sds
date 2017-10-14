# Socket Delivery Server


该框架是提供TCP Socket长连接的高可用方案。核心方案参考 ![socket负载均衡方案](https://github.com/1991wangliang/distribute-netty)。框架采用的核心技术框架：netty springcloud redis eureka


框架主要是为了提供一个可以负载大量socket tcp长连接的通讯协议。框架主要分为两部分delivery分发负载模块、socketServer通讯模块。模块delivery依赖redis共享数据，提供双服务提高高可用性能，socketServer基于eureka负载可支持无上线的拓展。为了提供海量socket长连接请求。


下面是SDS的架构图：

![](readme/sds.jpg)


## 代码介绍

框架将delivery与socketServer均以第三方库的方式做的封装。再试一下的时候只需要依赖他们就可以。


## demo演示

详细请参考demo文件夹下的代码。


## 启动说明

框架依赖eureka和redis，在启动项目之前要确保这两个服务已开启。

然后在运行两个demo。