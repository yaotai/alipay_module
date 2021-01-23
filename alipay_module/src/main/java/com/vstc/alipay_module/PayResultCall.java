package com.vstc.alipay_module;

public interface PayResultCall {
    void paySuccess(String msg);
    void payFailed(int code,String error);
}
