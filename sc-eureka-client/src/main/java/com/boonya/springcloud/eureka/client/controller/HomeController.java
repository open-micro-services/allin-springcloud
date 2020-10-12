package com.boonya.springcloud.eureka.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/port")
    public String port() {
        return "port:" + port;
    }
}
