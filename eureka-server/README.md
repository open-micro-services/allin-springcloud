
# SpringCloud 核心项目
```
springcloud config: 配置管理工具，支持使用git存储配置内容，支持应用配置的外部化存储，支持客户端配置信息刷新，加解密配置内容等
springcloud bus: 事件、消息总线、用于在集群（例如，配置变化事件）中传播状态变化，可以与springcloud config联合实现热部署
springcloud netflix: 针对多种netflix组件提供的开发工具包，其中包括eureka、hystrix、zuul、archaius等
netflix-eureka: 一个基于rest服务的服务治理组件，包括服务注册中心，服务注册与服务发现机制的实现，实现了云端负载均衡和中间层服务器的故障转移
netflix-hystrix: 容错管理工具，实现断路器模式，通过控制服务的节点，从而对延迟和故障提供更强大的容错能力
netflix-ribbon: 客户端负载均衡的服务调用组件
netflix-feign: 基于ribbon和hystrix的声明式服务调用组件
netflix-zuul: 微服务网关，提供动态路由，访问过滤等服务
netflix-archaius: 配置管理API，包含一系列配置管理API，提供动态类型化属性、线程安全配置操作、轮询框架、回调机制等功能
```

# Eureka服务注册发现流程
```
1.通过spring-cloud-starter-eureka-server和@EnableEurekaServer实现服务注册中心
2.通过spring-cloud-starter-eureka和@EnableDiscoveryClient使用并注册到服务注册中心
3.通过spring-cloud-starter-eureka和@EnableDiscoveryClient使用注册中心并发现服务，通过spring-cloud-starter-ribbon来实现负载均衡消费服务
```
# Eureka高可用服务注册发现

C:\Windows\System32\drivers\etc 编辑hosts
```
127.0.0.1 peer1
127.0.0.1 peer2
```
# Eureka运行多个服务注册发现示例
```
java -jar sc-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1 --server.port=2001
java -jar sc-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2 --server.port=2002
```
# 参考学习提升
```
https://www.cnblogs.com/jpfss/p/11314673.html

```