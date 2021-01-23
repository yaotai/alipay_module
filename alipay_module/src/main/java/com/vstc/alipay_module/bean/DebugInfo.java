package com.vstc.alipay_module.bean;

public class DebugInfo {
    private PayParams settingInfo;
    private PayParams lauchInfo;
    private String payResult;

    public PayParams getSettingInfo() {
        return settingInfo;
    }

    public void setSettingInfo(PayParams settingInfo) {
        this.settingInfo = settingInfo;
    }

    public PayParams getLauchInfo() {
        return lauchInfo;
    }

    public void setLauchInfo(PayParams lauchInfo) {
        this.lauchInfo = lauchInfo;
    }

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }
}
