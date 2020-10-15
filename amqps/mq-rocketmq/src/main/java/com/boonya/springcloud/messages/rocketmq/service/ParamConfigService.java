package com.boonya.springcloud.messages.rocketmq.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ParamConfigService {
    @Value("${fee-plat.fee-plat-group}")
    public String feePlatGroup ;
    @Value("${fee-plat.fee-plat-topic}")
    public String feePlatTopic ;
    @Value("${fee-plat.fee-account-tag}")
    public String feeAccountTag ;
}
