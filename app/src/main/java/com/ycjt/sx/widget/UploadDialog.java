package com.ycjt.sx.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.ycjt.sx.R;

/**
 * Created by liuchao on 2017/6/23.
 */

public class UploadDialog extends Dialog {
    public UploadDialog(@NonNull Context context) {
        super(context, R.style.UploadDialogStyle);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_upload_icon);
        Window window = getWindow();//获取dialog所在的窗口对象
        WindowManager.LayoutParams params = window.getAttributes();//获取当前窗口的属性
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;//设置窗口重心位置为屏幕正下方
        //重新将修改后的属性设置给当前窗口对象
        window.setAttributes(params);


        ImageView imageView = (ImageView) findViewById(R.id.iv_upload_icon);

        Button btnLocal = (Button) findViewById(R.id.btn_upload_icon);
        Button btnC = (Button) findViewById(R.id.btn_upload_icon_c);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
