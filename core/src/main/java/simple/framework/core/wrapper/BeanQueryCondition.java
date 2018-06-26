package simple.framework.core.wrapper;


import simple.framework.core.page.PageRequest;

import java.io.Serializable;
import java.util.*;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/6/21 13:55
 * Created by huangxy
 */
public class BeanQueryCondition implements Serializable{

    private String [] selectColumn;
    private String dimensionType;
    private Map<String,List<Meta>> conditionData;
    private PageRequest page;
    private BeanQueryCondition(){}

    private BeanQueryCondition(String [] selectColumn,String dimensionType,Map<String, List<Meta>> conditionData,PageRequest page) {
        this.conditionData = conditionData;
        this.dimensionType = dimensionType;
        this.selectColumn = selectColumn;
        this.page = page;
    }

    public String getDimensionType() {
        return dimensionType;
    }

    public PageRequest getPage() {
        return page;
    }

    public String[] getSelectColumn() {
        return selectColumn;
    }

    public void setSelectColumn(String[] selectColumn) {
        this.selectColumn = selectColumn;
    }

    public Map<String, List<Meta>> getConditionData() {
        return conditionData;
    }

    public void setConditionData(Map<String, List<Meta>> conditionData) {
        this.conditionData = conditionData;
    }

    public static class Meta implements Serializable{

        private String property;

        private String func;

        private Object[] data;

        public String getFunc() {
            return func;
        }

        public void setFunc(String func) {
            this.func = func;
        }

        public Object[] getData() {
            return data;
        }

        public void setData(Object[] data) {
            this.data = data;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }


    public static class Builder {

        private String [] selectColumn;
        private String dimensionType;
        private Map<String,BeanQueryWrapper> wrapperMap = new HashMap<>();

        private PageRequest page = new PageRequest();

        public BeanQueryFunction property(String name){
            BeanQueryWrapper wrapper = wrapperMap.get(name);
            if(wrapper==null){
                wrapper = new BeanQueryWrapper(name,new ArrayList<>());
                wrapperMap.put(name,wrapper);
            }
            return wrapper;
        }

        public Builder dimensionType(String dimensionType){
            this.dimensionType = dimensionType;
            return this;
        }

        public Builder select(String ...property){
            selectColumn = property;
            return this;
        }

        public Builder select(Class<?> clazz){
            return this;
        }

        public Builder page(PageRequest page){
            this.page = page;
            return this;
        }

        public BeanQueryCondition builder(){

            Map<String,List<Meta>> propertyFuncMeta = new TreeMap<>();
            wrapperMap.forEach((k,v)->{
                propertyFuncMeta.put(k,v.getMetas());
            });

            return new BeanQueryCondition(selectColumn,dimensionType,propertyFuncMeta,page);
        }
    }
}
