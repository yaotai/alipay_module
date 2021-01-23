package com.vstc.alipay_module.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.vstc.alipay_module.R;




public class TipDialog extends Dialog {
    private Context context;
    private TextView tv_ok;
      private TextView tv_content;

    public TipDialog(Context context) {
         //super(context);
         super(context,R.style.dialog);
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.WRAP_CONTENT);
        // 绑定Layout
        this.setContentView(R.layout.dialog_tip);
        Window window = TipDialog.this.getWindow();
        window.setGravity(Gravity.CENTER);
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = (int) (((Activity) context).getWindowManager().getDefaultDisplay().getWidth() * 0.8);
//        lp.height = (int) (((Activity) context).getWindowManager().getDefaultDisplay().getHeight() * 0.8);
      //  window.setAttributes(lp);

        // 禁止点击其他地方自动关闭
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        tv_ok = (TextView) findViewById(R.id.tv_ok);
        tv_content = (TextView) findViewById(R.id.tv_content);

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (okListenner != null) {
                    okListenner.ok();
                }
            }
        });

    }


    private ActionListenner okListenner;

    public void setOkListenner(ActionListenner okListenner) {
        this.okListenner = okListenner;
    }
    public void show(String content){
        tv_content.setText(content);
        this.show();
    }
    public interface ActionListenner {
        void ok();
    }
}
