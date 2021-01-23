package com.vstc.alipay_module.util;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AliPayCharset {
    //
    @StringDef({UTF_8,UTF_16,ASCII,GBK})
    @Retention(RetentionPolicy.SOURCE)
    public @interface  Charset{}
    public static final String UTF_8="utf-8";
    public static final String UTF_16="utf-16";
    public static final String ASCII="ASCII";
    public static final String GBK="GBK";
}
