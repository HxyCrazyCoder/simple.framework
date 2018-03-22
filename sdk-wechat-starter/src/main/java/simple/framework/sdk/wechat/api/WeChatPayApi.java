package simple.framework.sdk.wechat.api;

import org.springframework.http.ResponseEntity;
import simple.framework.core.annotation.SDKApi;
import simple.framework.core.annotation.SDKOperation;
import simple.framework.core.sdk.SDKHttpRequest;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/20 13:15
 * Created by huangxy
 */
@SDKApi
public interface WeChatPayApi {

    /**
     * 订单创建
      * @param request
     * @param <T>
     * @return
     */
    @SDKOperation(key = "unifiedOrder",url = "https://api.mch.weixin.qq.com/pay/unifiedorder")
    public <T> ResponseEntity<T> unifiedOrder(SDKHttpRequest<T> request);

    /**
     * 订单查询
     * @param request
     * @param <T>
     * @return
     */
    @SDKOperation(key = "orderQuery",url = "https://api.mch.weixin.qq.com/pay/orderquery")
    public <T> ResponseEntity<T> orderQuery(SDKHttpRequest<T> request);

    /**
     * 关闭订单
     * @param request
     * @param <T>
     * @return
     */
    @SDKOperation(key = "orderClose",url = "https://api.mch.weixin.qq.com/pay/closeorder")
    public <T> ResponseEntity<T> orderClose(SDKHttpRequest<T> request);

    /**
     * 退款申请
     * @param request
     * @param <T>
     * @return
     */
    @SDKOperation(key = "refund",url = "https://api.mch.weixin.qq.com/secapi/pay/refund")
    public <T> ResponseEntity<T> refundApply(SDKHttpRequest<T> request);

    /**
     * 退款查询
     * @param request
     * @param <T>
     * @return
     */
    @SDKOperation(key = "refundQuery",url = "https://api.mch.weixin.qq.com/pay/refundquery")
    public <T> ResponseEntity<T> refundQuery(SDKHttpRequest<T> request);

    /**
     * 账单下载
     * @param request
     * @param <T>
     * @return
     */
    @SDKOperation(key = "downloadBill",url = "https://api.mch.weixin.qq.com/pay/downloadbill")
    public <T> ResponseEntity<T> downloadBill(SDKHttpRequest<T> request);

    /**
     * 账单下载
     * @param request
     * @param <T>
     * @return
     */
    @SDKOperation(key = "downloadFundFlow",url = "https://api.mch.weixin.qq.com/pay/downloadfundflow")
    public <T> ResponseEntity<T> downloadFundFlow(SDKHttpRequest<T> request);

}
