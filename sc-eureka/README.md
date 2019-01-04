# Eureka服务注册发现流程
```
1.通过spring-cloud-starter-eureka-server和@EnableEurekaServer实现服务注册中心
2.通过spring-cloud-starter-eureka和@EnableDiscoveryClient使用并注册到服务注册中心
3.通过spring-cloud-starter-eureka和@EnableDiscoveryClient使用注册中心并发现服务，通过spring-cloud-starter-ribbon来实现负载均衡消费服务
```
# Eureka高可用服务注册发现

C:\Windows\System32\drivers\etc 编辑hosts
```
127.0.0.1 node1
127.0.0.1 node2
```
# Eureka运行多个服务注册发现示例


