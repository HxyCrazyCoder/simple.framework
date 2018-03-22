package simple.framework.core.annotation;

import org.springframework.web.bind.annotation.RequestMethod;
import simple.framework.core.sdk.SDKMapperMethod;
import simple.framework.core.sdk.http.HttpMapperMethod;

import java.lang.annotation.*;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/20 15:52
 * Created by huangxy
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SDKApi {

    String name() default "";

    String url() default "";

    Class< ? extends SDKMapperMethod> proxyClass() default HttpMapperMethod.class;

}
