package simple.framework.sdk.wechat.api;

import org.springframework.http.ResponseEntity;
import simple.framework.core.annotation.SDKApi;
import simple.framework.core.annotation.SDKOperation;
import simple.framework.core.sdk.SDKHttpRequest;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/21 10:56
 * Created by huangxy
 */
@SDKApi
public interface WeChatTransferApi {

    @SDKOperation(url = "https://api.mch.weixin.qq.com/mmpaysptrans/pay_bank")
    public <T> ResponseEntity<T> applyToBank(SDKHttpRequest<T> request);

    @SDKOperation(url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers")
    public <T> ResponseEntity<T> applyToLQ(SDKHttpRequest<T> request);

}
