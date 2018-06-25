package simple.framework.core.adapter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.util.BeanDefinitionUtils;
import org.springframework.stereotype.Component;
import simple.framework.core.wrapper.BeanQueryCondition;

import javax.persistence.criteria.*;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/6/21 17:33
 * Created by huangxy
 */
@Component
public class JpaSpecificationResolver {

    public <T> Specification<T> formatAndCondition(BeanQueryCondition cdt, Class<T> clazz){
        return new Specification<T>(){
            @Override
            public Predicate toPredicate(Root<T> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {

                List<Predicate> list = new ArrayList<Predicate>();

                //待查询字段
                String [] columns = cdt.getSelectColumn();
                //遍历属性
                cdt.getConditionData().forEach((k,v)->{
                    //遍历属性条件
                    v.forEach(item->{
                        Predicate predicate = toCreatePredicate(root,criteriaBuilder,clazz,item);
                        if (predicate!=null){
                            list.add(predicate);
                        }
                    });
                });

                //返回where后and条件
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        };
    }

    private static <X,Y extends Comparable> Predicate toCreatePredicate(Root<X> root,
                                                                        CriteriaBuilder builder,
                                                                        Class<?> clazz,
                                                                        BeanQueryCondition.Meta meta){
        //属性不存在 返回null
        PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(clazz,meta.getProperty());
        if (descriptor == null){
            return null;
        }

        Path<Y> path = root.get(meta.getProperty());
        switch (meta.getFunc()) {
            case "eq":
                return builder.equal(path,meta.getData()[0]);
            case "notEq":
                return builder.notEqual(path,meta.getData()[0]);
            case "in":
                return builder.in(path).getExpression().in(meta.getData());
            case "notIn":
                return builder.in(path).getExpression().in(meta.getData()).not();
            case "gt":
                return builder.greaterThan(path,(Y) meta.getData()[0]);
            case "lt":
                return builder.lessThan(path,(Y) meta.getData()[0]);
            case "gtEq":
                return builder.greaterThanOrEqualTo(path,(Y) meta.getData()[0]);
            case "ltEq":
                return builder.lessThanOrEqualTo(path,(Y) meta.getData()[0]);
            case "between":
                return builder.between(path,(Y)meta.getData()[0],(Y)meta.getData()[1]);
            case "like":
                Path<String> stringPath = root.get(meta.getProperty());
                return builder.like(stringPath,String.valueOf(meta.getData()[0]));
            case "isNull":
                return builder.isNull(path);
            case "notNull":
                return builder.isNotNull(path);
        }
        return null;
    }

}
