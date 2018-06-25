package simple.framework.core.wrapper;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/6/21 13:23
 * Created by huangxy
 */
public interface BeanQueryFunction {

    <T> BeanQueryFunction eq(T data);

    <T> BeanQueryFunction notEq(T data);

    <T> BeanQueryFunction in(T... data);

    <T> BeanQueryFunction notIn(T... data);

    <T> BeanQueryFunction gt(T data);

    <T> BeanQueryFunction lt(T data);

    <T> BeanQueryFunction gtEq(T data);

    <T> BeanQueryFunction ltEq(T data);

    <T> BeanQueryFunction between(T s, T e);

    <T> BeanQueryFunction like(T data);

    BeanQueryFunction isNull();

    BeanQueryFunction notNull();



}
