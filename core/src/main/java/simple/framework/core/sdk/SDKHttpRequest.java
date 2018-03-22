package simple.framework.core.sdk;

import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/21 11:14
 * Created by huangxy
 */
public class SDKHttpRequest<T> {

    private Map<String,Object> uriVariables;

    private Object body;

    private Class<T> responseClass;

    private HttpHeaders headers;

    private SDKHttpRequest(Builder builder){
        this.uriVariables = builder.uriVariables;
        this.body = builder.body;
        this.responseClass = builder.responseClass;
        this.headers = builder.headers;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public Map<String, Object> getUriVariables() {
        return uriVariables;
    }

    public Object getBody() {
        return body;
    }

    public Class<?> getResponseClass() {
        return responseClass;
    }

    public static final class Builder<T>{

        private Map<String,Object> uriVariables = new HashMap<>();

        private Object body;

        private Class<T> responseClass;

        private HttpHeaders headers = new HttpHeaders();

        public Builder setUriVariables(Map<String, Object> uriVariables) {
            this.uriVariables = uriVariables;
            return this;
        }

        public Builder addUriVariables(String key,Object val){
            this.uriVariables.put(key,val);
            return this;
        }

        public Builder addHeader(String key,String val){
            headers.add(key,val);
            return this;
        }

        public Builder setBody(Object body) {
            this.body = body;
            return this;
        }

        public Builder setResponseClass(Class<T> responseClass) {
            this.responseClass = responseClass;
            return this;
        }

        public SDKHttpRequest builder(){
            return new SDKHttpRequest(this);
        }
    }
}
