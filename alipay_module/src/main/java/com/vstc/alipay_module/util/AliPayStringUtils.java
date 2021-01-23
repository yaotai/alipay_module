package com.vstc.alipay_module.util;

import android.app.Activity;
import android.util.Log;
import com.alipay.sdk.app.PayTask;
import com.vstc.alipay_module.PayConstant;
import com.vstc.alipay_module.bean.PayParams;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class AliPayStringUtils {
    /**
     * 拼接键值对
     *
     * @param key
     * @param value
     * @param isEncode
     * @return
     */
    private static String buildKeyValue(String key, String value, boolean isEncode) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append("=");
        if (isEncode) {
            try {
                sb.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                sb.append(value);
            }
        } else {
            sb.append(value);
        }
        return sb.toString();
    }


    /**
     * 对支付参数信息进行签名
     *
     * @param map
     *            待签名授权信息
     *
     * @return
     */
    public static String getSign(Map<String, String> map, String rsaKey, boolean rsa2) {
        List<String> keys = new ArrayList<String>(map.keySet());
        // key排序
        Collections.sort(keys);

        StringBuilder authInfo = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            authInfo.append(buildKeyValue(key, value, false));
            authInfo.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        authInfo.append(buildKeyValue(tailKey, tailValue, false));

        String oriSign = SignUtils.sign(authInfo.toString(), rsaKey, rsa2);
        String encodedSign = "";
        if (oriSign==null){
            return null;
        }else {
            try {
                encodedSign = URLEncoder.encode(oriSign, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return "sign=" + encodedSign;
    }

    /**
     * 要求外部订单号必须唯一。
     * @return
     */
    private static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * 构造支付订单参数信息
     *
     * @param map
     * 支付订单参数
     * @return
     */
    public static String buildOrderParam(Map<String, String> map) {
        List<String> keys = new ArrayList<String>(map.keySet());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            sb.append(buildKeyValue(key, value, true));
            sb.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        sb.append(buildKeyValue(tailKey, tailValue, true));

        return sb.toString();
    }
    public static String getDateFormat(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(time);
    }
    /**
     * 构造支付订单参数列表
     */
    public static Map<String, String> buildOrderParamMap(final PayParams params) {
        Map<String, String> keyValues = new HashMap<String, String>();
        keyValues.put("app_id", params.getAppId());
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("timeout_express",params.getTimeoutExpress());
            jsonObject.put("product_code",params.getOrderId());
            jsonObject.put("total_amount",params.getAmount());
            jsonObject.put("subject",params.getDescription());
            jsonObject.put("body",params.getBody());
            jsonObject.put("out_trade_no",getOutTradeNo());
            Log.i("buildOrderParamMap:",jsonObject.toString());
            keyValues.put("biz_content",jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        keyValues.put("charset", params.getCharset());
        keyValues.put("method", "alipay.trade.app.pay");
        keyValues.put("sign_type",params.getSignType());
        keyValues.put("timestamp", params.getTimestamp());
        keyValues.put("version", params.getVersion());

        return keyValues;
    }

    public static void paramNullCheck(Activity context,PayParams payParams) {
        if (payParams.getCharset()==null){
            payParams.setCharset(AliPayCharset.UTF_8);
        }
        if (payParams.getSignType()==null){
            payParams.setSign_type(AliPaySignType.RSA2);
        }
        if (payParams.getTimestamp()==null){
            payParams.setTimestamp(getDateFormat(System.currentTimeMillis()));
        }
        if (payParams.getVersion()==null){
            payParams.setVersion(new PayTask(context).getVersion());
        }
        if (payParams.getBody()==null){
            payParams.setBody("");
        }
        if (payParams.getTimeoutExpress()==null){
            payParams.setTimeout_express("30m");
        }
    }
}
