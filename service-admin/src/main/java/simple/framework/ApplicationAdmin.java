package simple.framework;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/2/27 17:42
 * Created by huangxy
 */
@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class ApplicationAdmin {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationAdmin.class,args);
    }

}
