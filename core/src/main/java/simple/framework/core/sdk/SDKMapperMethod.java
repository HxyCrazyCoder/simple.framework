package simple.framework.core.sdk;

import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/22 10:50
 * Created by huangxy
 */
public interface SDKMapperMethod<T> {

    public <T> Object execute(Method method,Object[] args);

}
