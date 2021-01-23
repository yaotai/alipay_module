package com.alipay.sdk.pay.demo;

import com.vstc.alipay_module.AlipayClient;
import com.vstc.alipay_module.IAliPayCall;
import com.vstc.alipay_module.PayResultCall;
import com.vstc.alipay_module.util.DialogUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PayDemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_main);
    }
    /**
     * 支付宝支付业务示例
     */
    public void payV2(View v) {
        String APPID = "";
        String RSA2_PRIVATE="";
        IAliPayCall client = new AlipayClient.Builder()
                .setRequired_appId(APPID)
                .setRequired_orderId("QUICK_MSECURITY_PAY")
                .setRequired_amount("0.01")
                .setRequired_rsaKey(RSA2_PRIVATE)
                .setRequired_descriptid("测试支付")
                .build();
        client.setPayResultCall(new PayResultCall() {
            @Override
            public void paySuccess(String msg) {
                DialogUtils.showAlert(PayDemoActivity.this, msg,null);
            }

            @Override
            public void payFailed(int code ,String error) {
                DialogUtils.showAlert(PayDemoActivity.this, "code:"+code+", error:"+error,null);
            }
        });
        client.debug(true);
        client.startPay(PayDemoActivity.this);
    }




}
