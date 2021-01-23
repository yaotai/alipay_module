package com.vstc.alipay_module;

public class InterfacesBox {
    private PayResultCall payResultCall;
    private static class H{
        private static InterfacesBox interfacesBox=new InterfacesBox();
    }
    public static InterfacesBox get(){
        return H.interfacesBox;
    }

    public PayResultCall getPayResultCall() {
        return payResultCall;
    }

    public void setPayResultCall(PayResultCall payResultCall) {
        this.payResultCall = payResultCall;
    }




}
