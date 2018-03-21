package simple.framework.sdk.wechat.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.framework.core.sdk.http.SDKHttpRequest;
import simple.framework.sdk.wechat.api.WeChatPayApi;

import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/20 18:43
 * Created by huangxy
 */
@RestController
public class Controller {

    @Autowired
    private WeChatPayApi weChatPayApi;

    @RequestMapping("/pay")
    public void pay(){

        SDKHttpRequest request = new SDKHttpRequest.Builder()
                .addUriVariables("","")
                .addUriVariables("","")
                .setResponseClass(String.class)
                .builder();

        ResponseEntity out = weChatPayApi.unifiedOrder(request);
        System.out.println(out.getBody());
    }


}
