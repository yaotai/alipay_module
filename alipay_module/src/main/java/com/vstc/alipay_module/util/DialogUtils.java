package com.vstc.alipay_module.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.vstc.alipay_module.R;

public class DialogUtils {
    public static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton(R.string.confirm, null)
                .setOnDismissListener(onDismiss)
                .show();
    }
}
