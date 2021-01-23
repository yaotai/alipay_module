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
        String APPID = "2021001160661310";
        String RSA2_PRIVATE="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA64AtREYY1MNA9+c2BfSEe7pgP6ifec89ByBZuAOUxsMU3Ws4NfDKXcnwzFF73xJMHHhzdhKrrKlpje8Kx/sxwwqIJOtsfCQKfU4EPYdMyHoLwHt4ye+L7WLaknk407FhJlUrZtc1EiI20yYBV4v58kXE4G5kuybOTF12BkRZI/hVpEyzgoGTXgQMiR4yo6P97wGNNOd6PShhs6SO6PVQbwrJ4sobdUtj/L7anjMdS/3/13HAX9ZLfu9tJVbkOkOmRirPUn/pQ+taeSGHjX+xFFQAs0PDEkZSwq/7G1bNG0RWFmJP/2eDqPyeLDD+IOZCQCPVZ3gZLdQj9mBJBoFeYwIDAQAB";
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
                DialogUtils.showAlert(PayDemoActivity.this, msg);
            }

            @Override
            public void payFailed(int code ,String error) {
                DialogUtils.showAlert(PayDemoActivity.this, error);
            }
        });
        client.debug(true);
        client.startPay(PayDemoActivity.this);
    }




}
