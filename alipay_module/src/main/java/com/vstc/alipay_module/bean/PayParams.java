package com.vstc.alipay_module.bean;

import java.io.Serializable;

public class PayParams implements Serializable {
    private String appId;
    private  String orderId;
    private  String amount;
    private  String description;
    private  String rsaKey;
    private  String timeout_express;
    private  String body;
    private  String charset;
    private  String sign_type;
    private  String timestamp;
    private  String version;

    public PayParams(String appId,String orderId, String amount, String description, String rsaKey, String timeout_express, String body, String charset, String sign_type, String timestamp, String version) {
        this.appId=appId;
        this.orderId = orderId;
        this.amount = amount;
        this.description = description;
        this.rsaKey = rsaKey;
        this.timeout_express = timeout_express;
        this.body = body;
        this.charset = charset;
        this.sign_type = sign_type;
        this.timestamp = timestamp;
        this.version = version;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getRsaKey() {
        return rsaKey;
    }

    public String getTimeoutExpress() {
        return timeout_express;
    }

    public String getBody() {
        return body;
    }

    public String getCharset() {
        return charset;
    }

    public String getSignType() {
        return sign_type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRsaKey(String rsaKey) {
        this.rsaKey = rsaKey;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }


}
