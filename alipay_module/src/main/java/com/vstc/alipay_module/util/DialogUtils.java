package com.vstc.alipay_module.util;
import android.app.Activity;
import android.content.Context;
import com.vstc.alipay_module.view.TipDialog;

public class DialogUtils {
    public static void showAlert(Activity ctx, String info,final TipDialog.ActionListenner  onDismiss) {
        showAlertDialog(ctx, info, onDismiss);
    }

    private static void showAlertDialog(Context ctx, String info,final TipDialog.ActionListenner  onDismiss) {
        TipDialog dialog=new TipDialog(ctx);
        dialog.setOkListenner(new TipDialog.ActionListenner() {
            @Override
            public void ok() {
                if (onDismiss!=null){
                    onDismiss.ok();
                }
            }
        });
        dialog.show(info);
    }
}
