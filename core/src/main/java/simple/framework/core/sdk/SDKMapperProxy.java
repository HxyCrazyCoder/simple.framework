package simple.framework.core.sdk;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.UsesJava7;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import simple.framework.core.annotation.SDKApi;
import simple.framework.core.annotation.SDKOperation;
import simple.framework.core.sdk.http.HttpMapperMethod;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/20 13:42
 * Created by huangxy
 */
@Component
public class SDKMapperProxy<T> implements InvocationHandler,Serializable {

    public SDKMapperProxy(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            } else if (isDefaultMethod(method)) {
                return invokeDefaultMethod(proxy, method, args);
            }
        } catch (Throwable t) {
            throw t;
        }
        SDKApi sdkApi = method.getDeclaringClass().getAnnotation(SDKApi.class);
        final SDKMapperMethod sdkMapperMethod = applicationContext.getBean(sdkApi.proxyClass());
        return sdkMapperMethod.execute(method,args);

    }

    @UsesJava7
    private Object invokeDefaultMethod(Object proxy, Method method, Object[] args)
            throws Throwable {
        final Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                .getDeclaredConstructor(Class.class, int.class);
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        final Class<?> declaringClass = method.getDeclaringClass();
        return constructor.newInstance(declaringClass, MethodHandles.Lookup.PRIVATE)
                .unreflectSpecial(method, declaringClass).bindTo(proxy).invokeWithArguments(args);
    }

    /**
     * 判断是否为默认方法
     * @param method
     * @return
     */
    private boolean isDefaultMethod(Method method) {
        return ((method.getModifiers()
                & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC)
                && method.getDeclaringClass().isInterface();
    }

    private ApplicationContext applicationContext;

}
