package com.ycjt.sx.erp.personal.activity;

import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.suke.widget.SwitchButton;
import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseActivity;

public class NewInfoNotificationActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 参考GitHub项目https://github.com/zcweng/SwitchButton
     */
    private SwitchButton sbPersonalNewInfoReceive;

    @Override
    protected void initView() {
        tvTopModdel.setText("新消息通知");
        ibTopLeft.setVisibility(View.VISIBLE);
        ivFalse.setVisibility(View.VISIBLE);
        ibTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sbPersonalNewInfoReceive = (SwitchButton) findViewById(R.id.sb_personal_new_info_receive);
        sbPersonalNewInfoReceive.setChecked(true);//设置默认开关

        //isChecked()--> 开关关闭
        if (sbPersonalNewInfoReceive.isChecked()) {
            LogUtils.i("开关打开着");
        } else {
            LogUtils.i("开关关闭");
        }
        sbPersonalNewInfoReceive.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                LogUtils.i("我的状态被修改了" + isChecked);
            }
        });


    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_new_info_notification;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
