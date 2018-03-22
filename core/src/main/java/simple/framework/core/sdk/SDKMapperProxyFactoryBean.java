package simple.framework.core.sdk;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.client.RestTemplate;
import simple.framework.core.sdk.http.HttpMapperMethod;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/20 14:04
 * Created by huangxy
 */
public class SDKMapperProxyFactoryBean<T> implements FactoryBean<T>,ApplicationContextAware,Serializable {

    private Class<T> mapperInterface;
    private ApplicationContext applicationContext;

    public SDKMapperProxyFactoryBean(){};

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        SDKMapperProxy sdkMapperProxy = new SDKMapperProxy(applicationContext);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),
                new Class[] { mapperInterface },sdkMapperProxy);
    }


    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
