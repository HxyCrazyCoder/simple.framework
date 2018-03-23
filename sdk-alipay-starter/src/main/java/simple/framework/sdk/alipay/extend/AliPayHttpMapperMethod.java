package simple.framework.sdk.alipay.extend;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import simple.framework.core.annotation.SDKApi;
import simple.framework.core.annotation.SDKOperation;
import simple.framework.core.sdk.SDKHttpRequest;
import simple.framework.core.sdk.SDKMapperMethod;

import java.lang.reflect.Method;
import java.security.Security;
import java.util.Date;
import java.util.Map;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/22 11:45
 * Created by huangxy
 */
@Component
public class AliPayHttpMapperMethod implements SDKMapperMethod{

    static {
        //清除安全设置
        Security.setProperty("jdk.certpath.disabledAlgorithms", "");
    }

    @Override
    public Object execute(Method method, Object[] args) {

        SDKApi sdkApi = method.getDeclaringClass().getAnnotation(SDKApi.class);

        Map<String,String> customVariables = null;
        AlipayRequest alipayRequest = null;
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg instanceof  Map){
                customVariables = (Map<String, String>) arg;
            }else if(arg instanceof AlipayRequest){
                alipayRequest = (AlipayRequest) arg;
            }
        }

        //采用阿里原生SDK
        AlipayClient alipayClient = new DefaultAlipayClient(sdkApi.url(),
                customVariables.get("appId"),
                customVariables.get("privateKey"),
                customVariables.get("format"),
                customVariables.get("charset"),
                customVariables.get("alipayPublicKey"),
                customVariables.get("signType"));

        try {
            return alipayClient.execute(alipayRequest);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
    }
}
