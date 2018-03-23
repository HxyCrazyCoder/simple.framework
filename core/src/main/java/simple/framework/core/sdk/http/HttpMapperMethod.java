package simple.framework.core.sdk.http;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import simple.framework.core.annotation.SDKApi;
import simple.framework.core.annotation.SDKOperation;
import simple.framework.core.sdk.SDKHttpRequest;
import simple.framework.core.sdk.SDKMapperMethod;

import java.lang.reflect.Method;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/20 13:44
 * Created by huangxy
 */
@Component
public class HttpMapperMethod implements SDKMapperMethod {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object execute(Method method, Object[] args) {

        SDKOperation sdkOperation = method.getAnnotation(SDKOperation.class);
        SDKHttpRequest request = null;
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg instanceof  SDKHttpRequest){
                request = (SDKHttpRequest) arg;
            }
        }

        HttpEntity httpEntity = new HttpEntity(request.getBody(),request.getHeaders());
        /**
         * 调用远程执行
         */
        switch (sdkOperation.method()){
            case GET:
                return restTemplate.getForEntity(sdkOperation.url(),request.getResponseClass(),request.getUriVariables());
            case POST:
                return restTemplate.postForEntity(sdkOperation.url(),httpEntity,request.getResponseClass(),request.getUriVariables());
            default:
                throw new RuntimeException("not support method");
        }
    }
}
