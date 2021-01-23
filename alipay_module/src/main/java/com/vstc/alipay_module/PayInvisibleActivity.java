package com.vstc.alipay_module;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import com.alipay.sdk.app.PayTask;
import com.vstc.alipay_module.bean.PayParams;
import com.vstc.alipay_module.bean.PayResult;
import com.vstc.alipay_module.util.AliPayStringUtils;
import com.vstc.alipay_module.util.DialogUtils;

import java.util.Map;

/**
 *  重要说明：
 *
 *  本 Demo 只是为了方便直接向商户展示支付宝的整个支付流程，所以将加签过程直接放在客户端完成
 *  （包括 OrderInfoUtil2_0_HK 和 OrderInfoUtil2_0）。
 *
 *  在真实 App 中，私钥（如 RSA_PRIVATE 等）数据严禁放在客户端，同时加签过程务必要放在服务端完成，
 *  否则可能造成商户私密数据泄露或被盗用，造成不必要的资金损失，面临各种安全风险。
 *
 *  Warning:
 *
 *  For demonstration purpose, the assembling and signing of the request parameters are done on
 *  the client side in this demo application.
 *
 *  However, in practice, both assembling and signing must be carried out on the server side.
 */
public class PayInvisibleActivity extends Activity {
	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_AUTH_FLAG = 2;
    private PayParams payParams;
    private PayResultCall payResultCall;
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				@SuppressWarnings("unchecked")
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
				/**
				 * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息
				String resultStatus = payResult.getResultStatus();
				// 判断resultStatus 为9000则代表支付成功
                if (PayConstant.isDebug){
                	DebugInfoBox.get().setPayResult("resultInfo:"+resultInfo+" ,resultStatus:"+resultStatus);
                    DebugInfoBox.get().printLog();

                }
				if (TextUtils.equals(resultStatus, "9000")) {
					// 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
					//showAlert(PayInvisibleActivity.this, getString(R.string.pay_success) + payResult);
					if (payResultCall!=null){
						payResultCall.paySuccess(payResult.toString());
						finish();
					}
				} else {
					// 该笔订单真实的支付结果，需要依赖服务端的异步通知。
				//	showAlert(PayInvisibleActivity.this, getString(R.string.pay_failed) + payResult);
					if (payResultCall!=null){
						payResultCall.payFailed(payResult.toString());
						finish();
					}
				}
				break;
			}
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		setContentView(R.layout.layout_pay_invisible);
		payParams=(PayParams)getIntent().getSerializableExtra(PayConstant.PAY_PARAMS_STR);
		if (payParams==null){
			finish();
		}
		payResultCall=InterfacesBox.get().getPayResultCall();
		payLanuch(payParams);
	}

	/**
	 * 支付宝支付业务示例
	 */
	public void payLanuch(PayParams mPayParams) {
		/*
		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
		 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
		 * orderInfo 的获取必须来自服务端；
		 */
		if (payParams.getAppId()==null||payParams.getAppId().equals("")){
			DialogUtils.showAlert( this, "appId is null or equals\"\"");
			return;
		}
		if (payParams.getRsaKey()==null||payParams.getRsaKey().equals("")){
			DialogUtils.showAlert( this, "RSA is null or equals\"\"");
			return;
		}
		if (payParams.getAmount()==null||payParams.getAmount().equals("")){
			DialogUtils.showAlert( this, "Amount is null or equals\"\"");
			return;
		}
		if (payParams.getDescription()==null){
			DialogUtils.showAlert( this, "RSA is null");
			return;
		}
		if (payParams.getOrderId()==null||payParams.getOrderId().equals("")){
			DialogUtils.showAlert( this, "orderId is null or equals\"\"");
			return;
		}
        AliPayStringUtils.paramNullCheck(this,payParams);
        boolean rsa2 = (payParams.getRsaKey().length() > 0);
		 Map<String, String> params = AliPayStringUtils.buildOrderParamMap(mPayParams);
        if (PayConstant.isDebug){
            DebugInfoBox.get().setLauchInfo(payParams);
        }
		String orderParam = AliPayStringUtils.buildOrderParam(params);
		String sign = AliPayStringUtils.getSign(params, payParams.getRsaKey(), rsa2);
		final String orderInfo = orderParam + "&" + sign;
		final Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				PayTask alipay = new PayTask(PayInvisibleActivity.this);
				Map<String, String> result = alipay.payV2(orderInfo, true);
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}


	
	/**
	 * 获取支付宝 SDK 版本号。
	 */
	public void showSdkVersion(View v) {
		PayTask payTask = new PayTask(this);
		String version = payTask.getVersion();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		InterfacesBox.get().setPayResultCall(null);
	}
}
