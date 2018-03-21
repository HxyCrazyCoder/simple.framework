package simple.framework.sdk.alipay.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/19 14:52
 * Created by huangxy
 */
@ConfigurationProperties(prefix = "simple.sdk.alipay")
public class AlipayConfigProperties {

    private Map<String,App> app;

    private Map<String,Merchant> merchant;

    public Map<String, App> getApp() {
        return app;
    }

    public void setApp(Map<String, App> app) {
        this.app = app;
    }

    public Map<String, Merchant> getMerchant() {
        return merchant;
    }

    public void setMerchant(Map<String, Merchant> merchant) {
        this.merchant = merchant;
    }

    public static class App{

        private String appId;

        private String secure;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getSecure() {
            return secure;
        }

        public void setSecure(String secure) {
            this.secure = secure;
        }
    }

    public static class Merchant{

        private String merchantName;

        private String merchantId;

        private String pubKey;
        private String priKey;

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getPubKey() {
            return pubKey;
        }

        public void setPubKey(String pubKey) {
            this.pubKey = pubKey;
        }

        public String getPriKey() {
            return priKey;
        }

        public void setPriKey(String priKey) {
            this.priKey = priKey;
        }
    }

}
