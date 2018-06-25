package simple.framework.core.adapter;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import simple.framework.core.wrapper.BeanQueryCondition;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/6/25 13:48
 * Created by huangxy
 */
@Component
public class ElasticsearchQueryResolver {

    public <T> BoolQueryBuilder formatBoolQueryBuilder(BeanQueryCondition cdt, Class<T> clazz){

        BoolQueryBuilder boolQuery= QueryBuilders.boolQuery();
        Map<String, List<BeanQueryCondition.Meta>> cdtMap = cdt.getConditionData();
        cdtMap.forEach((k,v)->{
            v.forEach(item->{
                QueryBuilder builder = toCreateFieldBuilder(item,clazz);
                if (builder==null){
                    return;
                }
                boolQuery.must(builder);
            });
        });

        return boolQuery;
    }

    private QueryBuilder toCreateFieldBuilder(BeanQueryCondition.Meta meta,Class<?> clazz){
        String property = meta.getProperty();
        switch (meta.getFunc()) {
            case "eq":
                return QueryBuilders.termQuery(property,meta.getData()[0]);
            case "notEq":
                BoolQueryBuilder notEq = QueryBuilders.boolQuery();
                return notEq.mustNot(QueryBuilders.termQuery(property,meta.getData()[0]));
            case "in":
                BoolQueryBuilder in = QueryBuilders.boolQuery();
                for (Object o : meta.getData()) {
                    in.should(QueryBuilders.termQuery(property,o));
                }
                return in;
            case "notIn":
                BoolQueryBuilder notIn = QueryBuilders.boolQuery();
                for (Object o : meta.getData()) {
                    notIn.mustNot(QueryBuilders.termQuery(property,o));
                }
                return notIn;
            case "gt":
                return QueryBuilders.rangeQuery(property).gt(meta.getData()[0]);
            case "lt":
                return QueryBuilders.rangeQuery(property).lt(meta.getData()[0]);
            case "gtEq":
                return QueryBuilders.rangeQuery(property).gte(meta.getData()[0]);
            case "ltEq":
                return QueryBuilders.rangeQuery(property).lte(meta.getData()[0]);
            case "between":
                return QueryBuilders.rangeQuery(property).gte(meta.getData()[0]).lte(meta.getData()[1]);
            case "like":
                return QueryBuilders.wildcardQuery(property,String.valueOf(meta.getData()[0]));
            case "isNull":

            case "notNull":
//                QueryBuilders.existsQuery(property);
            default:
        }
        return null;
    }
}
