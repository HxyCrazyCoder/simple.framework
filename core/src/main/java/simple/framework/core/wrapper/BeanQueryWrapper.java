package simple.framework.core.wrapper;

import java.util.List;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/6/21 13:21
 * Created by huangxy
 */
public class BeanQueryWrapper implements BeanQueryFunction{

    private List<BeanQueryCondition.Meta> metas;
    private String property;

    protected BeanQueryWrapper(String property,List<BeanQueryCondition.Meta> metas) {
        this.property = property;
        this.metas = metas;
    }

    @Override
    public <T> BeanQueryFunction eq(T data) {
        metas.add(createMeta("eq",data));
        return this;
    }

    @Override
    public <T> BeanQueryFunction notEq(T data) {
        metas.add(createMeta("notEq",data));
        return this;
    }

    @Override
    public <T> BeanQueryFunction in(T[] data) {
        metas.add(createMeta("in",data));
        return this;
    }

    @Override
    public <T> BeanQueryFunction notIn(T[] data) {
        metas.add(createMeta("notIn",data));
        return this;
    }

    @Override
    public <T> BeanQueryFunction gt(T data) {
        metas.add(createMeta("gt",data));
        return this;
    }

    @Override
    public <T> BeanQueryFunction lt(T data) {
        metas.add(createMeta("lt",data));
        return this;
    }

    @Override
    public <T> BeanQueryFunction gtEq(T data) {
        metas.add(createMeta("gtEq",data));
        return this;
    }

    @Override
    public <T> BeanQueryFunction ltEq(T data) {
        metas.add(createMeta("ltEq",data));
        return this;
    }

    @Override
    public <T> BeanQueryFunction between(T s, T e) {
        metas.add(createMeta("between",s,e));
        return this;
    }

    @Override
    public <T> BeanQueryFunction like(T data) {
        metas.add(createMeta("like",data));
        return this;
    }

    @Override
    public BeanQueryFunction isNull() {
        metas.add(createMeta("isNull",true));
        return this;
    }

    @Override
    public BeanQueryFunction notNull() {
        metas.add(createMeta("notNull",true));
        return this;
    }


    private <T> BeanQueryCondition.Meta createMeta(String action,T...data){
        BeanQueryCondition.Meta meta = new BeanQueryCondition.Meta();
        meta.setProperty(property);
        meta.setFunc(action);
        meta.setData(data);
        return meta;
    }

    public List<BeanQueryCondition.Meta> getMetas() {
        return metas;
    }
}
