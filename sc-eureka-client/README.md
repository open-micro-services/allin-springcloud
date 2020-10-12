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
java -jar sc-eureka-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1 --server.port=2001
java -jar sc-eureka-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2 --server.port=2002
```

