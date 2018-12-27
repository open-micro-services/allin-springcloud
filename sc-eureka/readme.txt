基本的服务治理：

    通过spring-cloud-starter-eureka-server和@EnableEurekaServer实现服务注册中心
    通过spring-cloud-starter-eureka和@EnableDiscoveryClient使用并注册到服务注册中心
    通过spring-cloud-starter-eureka和@EnableDiscoveryClient使用注册中心并发现服务，通过spring-cloud-starter-ribbon来实现负载均衡消费服务
