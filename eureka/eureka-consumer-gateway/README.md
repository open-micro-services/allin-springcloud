
Spring Cloud Gateway 为 SpringBoot 应用提供了API网关支持，具有强大的智能路由与过滤器功能，本文将对其用法进行详细介绍。
# Gateway 简介

Gateway是在Spring生态系统之上构建的API网关服务，基于Spring 5，Spring Boot 2和 Project Reactor等技术。

Gateway旨在提供一种简单而有效的方式来对API进行路由，以及提供一些强大的过滤器功能， 例如：熔断、限流、重试等。

Spring Cloud Gateway 具有如下特性：

```
1、基于Spring Framework 5, Project Reactor 和 Spring Boot 2.0 进行构建；
2、动态路由：能够匹配任何请求属性；
3、可以对路由指定 Predicate（断言）和 Filter（过滤器）；
4、集成Hystrix的断路器功能；
5、集成 Spring Cloud 服务发现功能；
6、易于编写的 Predicate（断言）和 Filter（过滤器）；
7、请求限流功能；
8、支持路径重写。
```

# 相关概念
```
1、Route（路由）：路由是构建网关的基本模块，它由ID，目标URI，一系列的断言和过滤器组成，如果断言为true则匹配该路由；
2、Predicate（断言）：指的是Java 8 的 Function Predicate。 输入类型是Spring框架中的ServerWebExchange。 这使开发人员可以匹配HTTP请求中的所有内容，例如请求头或请求参数。如果请求与断言相匹配，则进行路由；
3、Filter（过滤器）：指的是Spring框架中GatewayFilter的实例，使用过滤器，可以在请求被路由前后对请求进行修改。
```

链接：https://juejin.im/post/6844903982599684103

实战项目：https://github.com/macrozheng/mall