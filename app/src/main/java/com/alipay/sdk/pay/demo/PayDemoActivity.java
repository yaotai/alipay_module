package com.alipay.sdk.pay.demo;

import com.vstc.alipay_module.AlipayClient;
import com.vstc.alipay_module.IAliPayCall;
import com.vstc.alipay_module.PayResultCall;
import com.vstc.alipay_module.util.DialogUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
        String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCfW0SNuZFD8dYsAxMZ5yewBID0TIiSfU61t67gDcVMLihfnYPT7o8LkmhiOlf9aTPjOtrwDv+b9lmDdJ0aCSIDgeHCQwBKqjmihG85x3aqUmfs37MnInXYZf+x7rl3eXqbg4QPc2ByuNrUtvdPMJHNnujObkxolt8ke63dmSzFORjvjpIWZEY+j0kwmLxZQx6FE7V9aIIcE9FxZsScsqb1PBs0Fu65Ez4W/rSbMxdIlDuxuufH38+/2+sHAwqPqbcYNe+4choe1Q6eIbpAkr3hlDbm369G8GcSvd2GK/FxwwFjfM/WzwMyAoEVWUj1j6bWB5CharhObzsf4/v9PgUjAgMBAAECggEAUaz/p/wyfjaXZRt2UNJvPwIv7gbEAQEEtJKeqOul8fO+cbNT++3Ll0zE/4OT0ah4FcD2sg+d5xwOqRe95bhEnLOXOHAK2DoDLE7giya2pXlzFq9ZLXe2GuG8ekA3AwtaUhbIOvsRtfsU/e2BOC9Wq6MFA7j4pKqfWB2j8W4uyi44M1DdJb7TAdaRhwtq6GVMt5QFt73KfK/omSRFNJ6z8cUaOLIDSA3Bp8x37RyA0ZmcbMaQ+l6FDGY0MjBP32dfPlEs2pz8mDHkrKAw9E8VWe/HfAD87DdZgWBTOTKdY780UP74xMZuvouK92GUrgrukMQX1q0ss1w92nu4IHJJkQKBgQDWjqtgjXpdau+q7gKTlmz0D2AMWkLxgrQu/xIzplS/60mJetz4xQAOrs+OKr/3CulpBaC7iS31gnWzEd8f/npCJ8ztr5JkcuH5e5xAhGnzHvv+GroufS9w09+9ZCDM3LKSBFTNu2uRzHpu9+5wYwgrDHkce8scZoBMtCA4CpTY5QKBgQC+Iw4kyY7UOpNSfe8lZNVs/LckMV8yC6Eba1ubD7syxqDK5nMTOADhiScuK6QlkzSliTBmAeFmEZkAo5qEy1OXloVtMSVnrm4s6M5iXT5BmWKaSn0zvh8eJS9lCIdR9PN5O27XsEzAFjy3BJU8O0iKxex3NHJjjMZz8N3bLSitZwKBgQCGtIgQVILwx8S1Mj1X3+eDy2toFftVmdP6dZY96z3mSJigwgyd+0Ev9rzE6z4eojEtCKWDWHsMWt3+kUfe1Gx5wHc++y4T9rS9Z8gQEZJbW6amQK2Hpnz0f3FHfOfC9wT9+84qZHMC1LhBl1OSJ/rm3CvloCtDJ2+BuM1JogsVqQKBgQCPtjFXjt0c/lUh8bvTGL7UTMMkYQjh67ZO4jtwHVjRzR31nm92JEtOLPtnQ2cjW3tcbzAcueki28Q4nnAJVSGOnx+kY5QxJL9zxFdSM8X9kTDdC9ZMSLR+dX64L9UQFkP86vYhosiwG16yuo0N5IU1FtpfU03ufpX1RiZ6JkzrmwKBgDDNFhKq3mPGvhjVYiiH54G38EHaXNn4a39NTGJCttms3GnGygWuI71dClQayMCMYjquNymv5V2rd1CWFGT71+P5jYxOY7twnV90+1ZLm7cocOuScaWy4Ps44CNADfwbNSgDnPzB39ruPsYAc7GfccmPHX29zQoMs72if+KKLZgV";
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
            public void payFailed(String error) {
                DialogUtils.showAlert(PayDemoActivity.this, error);
            }
        });
        client.debug(true);
        client.startPay(PayDemoActivity.this);
    }




}
