package com.vstc.alipay_module;

import android.content.Context;
import android.content.Intent;

public class AliPayLauncher implements IAliPayCall {
    @Override
    public void paySuccess(String msg) {

    }

    @Override
    public void payFailed(String error) {

    }

    @Override
    public void startPay(Context context, AlipayClient... client) {
        Intent intent = new Intent(context, PayInvisibleActivity.class);
        intent.putExtra(PayConstant.PAY_PARAMS_STR, client[0].getmPayParams());
        context.startActivity(intent);
    }

    @Override
    public void setPayResultCall(PayResultCall call) {

    }

    @Override
    public void debug(boolean debug) {

    }


}
