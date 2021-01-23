package com.vstc.alipay_module;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.vstc.alipay_module.bean.PayParams;
import com.vstc.alipay_module.bean.PayResult;
import com.vstc.alipay_module.util.AliPayCharset;
import com.vstc.alipay_module.util.AliPaySignType;

/**
 * TODO 支付返回码
 * https://opendocs.alipay.com/open/common/105806
 */

public class AlipayClient implements IAliPayCall {
    private final String appId;
    private final String orderId;
    private final String amount;
    private final String description;
    private final String rsaKey;
    private final String timeout_express;
    private final String body;
    private final String charset;
    private final String sign_type;
    private final String timestamp;
    private final String version;

    public PayParams mPayParams;
    private AliPayLauncher mAliPayLauncher;

    @Override
    public void paySuccess(String msg) {

    }

    @Override
    public void payFailed(String error) {

    }

    @Override
    public void startPay(Context context, AlipayClient... client) {
        mAliPayLauncher.startPay(context, this);
    }

    public AlipayClient(Builder builder) {
        this.orderId = builder.required_orderId;
        this.amount = builder.required_amount;
        this.description = builder.required_descripton;
        this.rsaKey = builder.required_rsaKey;
        this.timeout_express = builder.timeout_express;
        this.body = builder.body;
        this.charset = builder.charset;
        this.sign_type = builder.sign_type;
        this.timestamp = builder.timestamp;
        this.version = builder.version;
        this.appId=builder.required_appId;
        mPayParams = new PayParams(
                this.appId,
                this.orderId,
                this.amount,
                this.description,
                this.rsaKey,
                this.timeout_express,
                this.body,
                this.charset,
                this.sign_type,
                this.timestamp,
                this.version
        );
        mAliPayLauncher = new AliPayLauncher();
    }

    /**
     * @param required_orderId    订单id 必填
     * @param required_amount     金额 0.10 一毛钱 必填
     * @param required_descripton 订单描述 必填
     * @param required_rsaKey     商户秘钥 必须从服务器接口获取，不能放在客户端 必填
     * @param timeout_express     超时设置 默认30m
     * @param body                自定义
     * @param charset             编码格式 默认 utf-8
     * @param sign_type           私钥类型 默认 RSA2 可选 RSA
     * @param timestamp           订单时间戳，不传默认手机本地时间
     * @param version             支付sdk版本号
     */
    public static class Builder {
        private  String required_appId;
        private String required_orderId;
        private String required_amount;
        private String required_descripton;
        private String required_rsaKey;
        private String timeout_express;
        private String body;
        private String charset;
        private String sign_type;
        private String timestamp;
        private String version;

        public Builder() {
        }

        public Builder setRequired_orderId(@NonNull String required_orderId) {
            this.required_orderId = required_orderId;
            return this;
        }

        public Builder setRequired_amount(@NonNull String required_amount) {
            this.required_amount = required_amount;
            return this;
        }

        public Builder setRequired_appId(String required_appId) {
            this.required_appId = required_appId;
            return this;
        }

        public Builder setRequired_descriptid(@NonNull String required_descripton) {
            this.required_descripton = required_descripton;
            return this;
        }

        public Builder setRequired_rsaKey(@NonNull String required_rsaKey) {
            this.required_rsaKey = required_rsaKey;
            return this;
        }

        public Builder setTimeout_express(@NonNull String timeout_express) {
            this.timeout_express = timeout_express;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setCharset(@AliPayCharset.Charset String charset) {
            this.charset = charset;
            return this;
        }

        public Builder setSign_type(@AliPaySignType.Type String sign_type) {
            this.sign_type = sign_type;
            return this;
        }

        public Builder setTimestamp(@Nullable String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setVersion(@Nullable String version) {
            this.version = version;
            return this;
        }

        public void setRequired_descripton(String required_descripton) {
            this.required_descripton = required_descripton;
        }

        public String getRequired_orderId() {
            return required_orderId;
        }

        public String getRequired_amount() {
            return required_amount;
        }

        public String getRequired_descripton() {
            return required_descripton;
        }

        public String getRequired_rsaKey() {
            return required_rsaKey;
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

        public AlipayClient build() {
            return new AlipayClient(this);
        }


    }

    public PayParams getmPayParams() {
        return mPayParams;
    }

    @Override
    public void setPayResultCall(PayResultCall call) {
        InterfacesBox.get().setPayResultCall(call);
    }

    @Override
    public void debug(boolean debug) {
        PayConstant.isDebug = debug;
        if (debug){
            DebugInfoBox.get().setSettingInfo(mPayParams);
        }
    }
}
