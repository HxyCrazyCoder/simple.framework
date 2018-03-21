package simple.framework.sdk.wechat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import simple.framework.sdk.wechat.config.WeChatConfigProperties;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/19 15:15
 * Created by huangxy
 */
@Configuration
@ComponentScan(basePackages = "simple.framework.sdk.wechat")
public class WeChatAutoConfig {

    @Bean
    public WeChatConfigProperties init(){
        return new WeChatConfigProperties();
    }
}
