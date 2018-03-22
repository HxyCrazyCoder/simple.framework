package simple.framework.core.annotation;

import org.springframework.web.bind.annotation.RequestMethod;
import simple.framework.core.sdk.SDKMapperMethod;
import simple.framework.core.sdk.http.HttpMapperMethod;

import java.lang.annotation.*;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/20 11:32
 * Created by huangxy
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SDKOperation {

    String key() default "";

    String url() default "";

    String path() default "";

    RequestMethod method() default RequestMethod.POST;

    Class<? extends SDKMapperMethod> proxyClass() default HttpMapperMethod.class;
}
