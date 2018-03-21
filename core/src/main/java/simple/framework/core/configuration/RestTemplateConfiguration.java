package simple.framework.core.configuration;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/20 17:00
 * Created by huangxy
 */
@Configuration
@ConditionalOnClass(value = {RestTemplate.class})
public class RestTemplateConfiguration {


    @Value("${remote.maxTotalConnect:10}")
    private int maxTotalConnect; //连接池的最大连接数默认为0

    @Value("${remote.maxConnectPerRoute:20}")
    private int maxConnectPerRoute; //单个主机的最大连接数

    @Value("${remote.connectTimeout:2000}")
    private int connectTimeout; //连接超时默认2s

    @Value("${remote.readTimeout:30000}")
    private int readTimeout; //读取超时默认30s



    @Bean
    public ClientHttpRequestFactory getFactory(){

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create()
                .disableContentCompression()
                .setMaxConnPerRoute(maxConnectPerRoute)
                .setMaxConnTotal(maxTotalConnect).build());
        factory.setConnectTimeout(connectTimeout);            //建立连接超时时间
        factory.setConnectionRequestTimeout(readTimeout);     //请求超时时间
        return factory;

    }


    @Bean
    public RestTemplate getRestTemplate(ClientHttpRequestFactory factory){

        RestTemplate restTemplate = new RestTemplate(factory);

        //修改默认字符集，设置utf8
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));

        //由于其他部分接口可能返回text/plain类型数据，进行扩展
        restTemplate.getMessageConverters().add(new TextJsonMessageConverter());
        restTemplate.getMessageConverters().add(new TextXmlMessageConverter());

        return restTemplate;
    }

    private class TextJsonMessageConverter extends MappingJackson2HttpMessageConverter {

        public TextJsonMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            mediaTypes.add(MediaType.TEXT_HTML);
            setSupportedMediaTypes(mediaTypes);
        }
    }

    private class TextXmlMessageConverter extends MappingJackson2HttpMessageConverter {

        public TextXmlMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            mediaTypes.add(MediaType.TEXT_HTML);
            setSupportedMediaTypes(mediaTypes);
        }
    }
}
