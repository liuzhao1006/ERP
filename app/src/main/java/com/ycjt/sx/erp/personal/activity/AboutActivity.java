package com.ycjt.sx.erp.personal.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.widget.fonts.FontTextView;

public class AboutActivity extends BaseActivity {

    private FontTextView tvAboutVersion;
    private FontTextView tvAboutLog;

    @Override
    protected void initView() {

        ibTopLeft.setVisibility(View.VISIBLE);
        ibTopRight.setVisibility(View.GONE);
        tvTopModdel.setVisibility(View.VISIBLE);
        ivFalse.setVisibility(View.VISIBLE);
        tvTopModdel.setText("关于");
        ibTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvAboutVersion = (FontTextView) findViewById(R.id.tv_about_version);
        tvAboutLog = (FontTextView) findViewById(R.id.tv_about_log);
        tvAboutVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("恭喜您,当前已经是最新版本了!", TOAST_SHORT);
            }
        });

        tvAboutLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("更新成功.", TOAST_SHORT);
            }
        });
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_about;
    }
}
