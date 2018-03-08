package simple.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/2/27 17:28
 * Created by huangxy
 */
@SpringBootApplication
@EnableEurekaServer
public class ApplicationEureka {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationEureka.class,args);
    }
}
