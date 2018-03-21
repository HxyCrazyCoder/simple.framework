package simple.framework.core.sdk.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import simple.framework.core.annotation.SDKApi;
import simple.framework.core.annotation.SDKOperation;

import java.lang.reflect.Method;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/20 13:44
 * Created by huangxy
 */
public class HttpRestTemplateMapperMethod {

    private SDKApi sdkApi;

    private SDKOperation sdkOperation;

    private Method method;

    private RestTemplate restTemplate;

    public HttpRestTemplateMapperMethod(SDKApi sdkApi,SDKOperation sdkOperation,Method method,RestTemplate restTemplate) {
        this.sdkApi = sdkApi;
        this.sdkOperation = sdkOperation;
        this.restTemplate = restTemplate;
        this.method = method;
    }

    /**
     * 默认参数
     * @param args
     * @return
     */
    public Object execute(Object[] args){

        SDKHttpRequest request = null;
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg instanceof  SDKHttpRequest){
                request = (SDKHttpRequest) arg;
            }
        }

        /**
         * 调用远程执行
         */
        switch (sdkOperation.method()){
            case GET:
                return restTemplate.getForEntity(sdkOperation.url(),request.getResponseClass(),request.getUriVariables());
            case POST:
                return restTemplate.postForEntity(sdkOperation.url(),request.getBody(),request.getResponseClass(),request.getUriVariables());
            default:
                throw new RuntimeException("not support method");
        }

    }
}
