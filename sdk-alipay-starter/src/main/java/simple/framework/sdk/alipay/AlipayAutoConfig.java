package simple.framework.sdk.alipay;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import simple.framework.sdk.alipay.config.AlipayConfigProperties;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/19 15:15
 * Created by huangxy
 */
@Configuration
@ComponentScan(basePackages = "simple.framework.sdk.alipay")
public class AlipayAutoConfig {

    @Bean
    public AlipayConfigProperties init(){
        return new AlipayConfigProperties();
    }
}
