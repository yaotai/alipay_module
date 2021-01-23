package com.vstc.alipay_module.util;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AliPaySignType {
    //
    @StringDef({RSA2,RSA})
    @Retention(RetentionPolicy.SOURCE)
    public @interface  Type{}
    public static final String RSA2="RSA2";
    public static final String RSA="RSA";

}
