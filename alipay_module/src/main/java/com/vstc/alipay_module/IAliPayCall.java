package com.vstc.alipay_module;

import android.content.Context;

public interface IAliPayCall {
    void setPayResultCall(PayResultCall call);
    void paySuccess(String msg);
    void payFailed(String error);
    void debug(boolean debug);
    void startPay(Context context,AlipayClient... client);
}
