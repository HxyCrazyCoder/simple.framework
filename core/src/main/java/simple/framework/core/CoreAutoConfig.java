package simple.framework.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import simple.framework.core.adapter.RequestArgumentResolversFormInterface;
import simple.framework.core.configuration.RestTemplateConfiguration;
import simple.framework.core.sdk.SDKRegisterPostProcessor;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/7 17:43
 * Created by huangxy
 */
@Configuration
@ComponentScan(basePackages = "simple.framework.core")
public class CoreAutoConfig {

    @Bean
    public RequestArgumentResolversFormInterface resolversFormInterface(RequestMappingHandlerAdapter adapter, ConfigurableBeanFactory beanFactory){
        RequestArgumentResolversFormInterface resolversFormInterface = new RequestArgumentResolversFormInterface();
        resolversFormInterface.modifyArgumentResolvers(adapter,beanFactory);
        return resolversFormInterface;
    }

    @Bean
    public SDKRegisterPostProcessor sdkRegisterPostProcessor(@Value("${simple.sdk.enable.basePackages}") String[] basePackages){
        basePackages[0] = "simple.framework.sdk.wechat.api";
        return new SDKRegisterPostProcessor(basePackages);
    }

    @Bean
    public RestTemplateConfiguration templateConfiguration(){
        return new RestTemplateConfiguration();
    }

}
