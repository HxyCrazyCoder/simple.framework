package simple.framework.sdk.alipay.api;

import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import org.springframework.http.ResponseEntity;
import simple.framework.core.annotation.SDKApi;
import simple.framework.core.annotation.SDKOperation;
import simple.framework.core.sdk.SDKHttpRequest;
import simple.framework.sdk.alipay.extend.AliPayHttpMapperMethod;

import java.util.Map;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/22 17:21
 * Created by huangxy
 */
@SDKApi(url = "https://openapi.alipay.com/gateway.do",proxyClass = AliPayHttpMapperMethod.class)
public interface AliPaymentApi {

    /**
     * 创建订单（app支付)
     * @param customVariables
     * @param request
     * @return
     */
    @SDKOperation(key = "alipay.trade.app.pay")
    public AlipayTradeAppPayRequest orderCreateFromApp(Map<String,String> customVariables,AlipayTradeAppPayRequest request);

    /**
     * 订单结果查询
     * @param customVariables
     * @param request
     * @return
     */
    @SDKOperation(key = "alipay.trade.query")
    public AlipayTradeQueryResponse orderQuery(Map<String,String> customVariables, AlipayTradeQueryRequest request);

    /**
     * 退款申请
     * @param customVariables
     * @param request
     * @return
     */
    @SDKOperation(key = "alipay.trade.refund")
    public AlipayTradeRefundResponse refundApply(Map<String,String> customVariables, AlipayTradeRefundRequest request);

    /**
     * 退款结果查询
     * @param customVariables
     * @param request
     * @return
     */
    @SDKOperation(key = "alipay.trade.fastpay.refund.query")
    public AlipayTradeFastpayRefundQueryResponse refundQuery(Map<String,String> customVariables, AlipayTradeFastpayRefundQueryRequest request);

}
