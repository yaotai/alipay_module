package com.vstc.alipay_module;

import android.util.Log;

import com.vstc.alipay_module.bean.DebugInfo;
import com.vstc.alipay_module.bean.PayParams;

public class DebugInfoBox {
    private DebugInfo info;

    private static class H{
        private static DebugInfoBox debugInfoBox=new DebugInfoBox();
    }
    public static DebugInfoBox get(){
        return  H.debugInfoBox;
    }

    public DebugInfo getPayResultCall() {
        return info;
    }

    public void setPayResultCall(DebugInfo debugInfo) {
        this.info = debugInfo;
    }

    public void setLauchInfo(PayParams lauchInfo){
        check();
        info.setLauchInfo(lauchInfo);
    }

    public void setPayResult(String payResult){
        check();
        info.setPayResult(payResult);
    }

    public void setSettingInfo(PayParams settingInfo){
        check();
        info.setSettingInfo(settingInfo);
    }

    private void check(){
        if (info==null){
            info=new DebugInfo();
        }
    }
    public void  printLog(){
        printSettingInfo();
        printLauchInfo();
        printPayResult();
    }
    private void printLauchInfo(){
        print("调用支付时post参数 ",   getPayResultCall().getLauchInfo());
    }

    private void printPayResult(){
        Log.i("AliPayClient",  "支付回调信息 :"+getPayResultCall().getPayResult());
    }

    private void printSettingInfo(){
        print("调用之前输入参数", getPayResultCall().getSettingInfo());
    }
    private static void print(String tag,PayParams mPayParams){
        Log.i("AliPayClient", "================================================================");
        Log.i("AliPayClient", tag+" ：mPayParams.getAppId():"+mPayParams.getAppId());
        Log.i("AliPayClient", tag+" ：mPayParams.getOrderId():"+mPayParams.getOrderId());
        Log.i("AliPayClient", tag+" ：mPayParams.getDescription():"+mPayParams.getDescription());
        Log.i("AliPayClient", tag+" ：mPayParams.getAmount():"+mPayParams.getAmount());
        Log.i("AliPayClient", tag+" ：mPayParams.getRsaKey():"+mPayParams.getRsaKey());
        Log.i("AliPayClient", tag+" ：mPayParams.getBody():"+mPayParams.getBody());
        Log.i("AliPayClient", tag+" ：mPayParams.getCharset():"+mPayParams.getCharset());
        Log.i("AliPayClient", tag+" ：mPayParams.getSignType():"+mPayParams.getSignType());
        Log.i("AliPayClient", tag+" ：mPayParams.getTimeoutExpress():"+mPayParams.getTimeoutExpress());
        Log.i("AliPayClient", tag+" ：mPayParams.getTimestamp():"+mPayParams.getTimestamp());
        Log.i("AliPayClient", tag+" ：mPayParams.getVersion():"+mPayParams.getVersion());
        Log.i("AliPayClient", "================================================================");
        Log.i("AliPayClient", "================================================================");
    }
}
