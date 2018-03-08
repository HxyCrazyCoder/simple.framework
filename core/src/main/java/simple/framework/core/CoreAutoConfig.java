package simple.framework.core;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import simple.framework.core.adapter.RequestArgumentResolversFormInterface;

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


}
