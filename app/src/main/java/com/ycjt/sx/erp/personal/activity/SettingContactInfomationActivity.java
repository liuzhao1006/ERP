package com.ycjt.sx.erp.personal.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ycjt.sx.R;
import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.erp.personal.bean.InfoJson;
import com.ycjt.sx.erp.personal.presenter.PersonalInfoPresenter;
import com.ycjt.sx.utils.PrefUtils;
import com.ycjt.sx.widget.fonts.FontTextView;
import com.ycjt.sx.widget.view.CircleImageView;

import static com.ycjt.sx.app.InterfaceConfig.APP_IP;
import static com.ycjt.sx.app.InterfaceConfig.APP_POST;
import static com.ycjt.sx.app.InterfaceConfig.HTTP;
import static com.ycjt.sx.app.InterfaceConfig.PERSONAL_UPLOAD_INFO;
import static com.ycjt.sx.utils.Utils.toURLEncoded;

public class SettingContactInfomationActivity extends BaseActivity implements View.OnClickListener, IInfo {


    private Button btnSettingContactSave;
    public String PERSONAL_UPLOAD_INFO_URL = HTTP + APP_IP + ":" + APP_POST + PERSONAL_UPLOAD_INFO + PrefUtils.getString(this, GlobalConstants.PERSONAL_USERID, null) + "/" + PrefUtils.getString(this, GlobalConstants.PERSONAL_DEVICEID, null) + "/";
    private EditText et_setting_contact_tel;
    private EditText et_setting_contact_landline;
    private EditText et_setting_contact_email;
    private EditText et_setting_contact_wechart;
    private EditText et_setting_contact_qq;

    private static final int ACTIVITY_REQUEST_PREVIEW_RESULT = 105;
    private CircleImageView iv_personal_icon;
    private FontTextView tv_personal_tel;
    private FontTextView tv_personal_unit;
    private FontTextView tv_personal_name;

    @Override
    protected void initView() {

        tvTopModdel.setText("修改个人资料");
        ibTopLeft.setVisibility(View.VISIBLE);
        ivFalse.setVisibility(View.VISIBLE);
        ibTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//
//接收值
        Intent intent = getIntent();
        byte[] bytes = intent.getByteArrayExtra("byte");

        Bitmap bitmap2 = getBitmap(bytes);
        et_setting_contact_tel = (EditText) findViewById(R.id.et_setting_contact_tel);
        et_setting_contact_landline = (EditText) findViewById(R.id.et_setting_contact_landline);
        et_setting_contact_email = (EditText) findViewById(R.id.et_setting_contact_email);
        et_setting_contact_wechart = (EditText) findViewById(R.id.et_setting_contact_wechart);
        et_setting_contact_qq = (EditText) findViewById(R.id.et_setting_contact_qq);

        iv_personal_icon = (CircleImageView) findViewById(R.id.iv_personal_icon);
        tv_personal_tel = (FontTextView) findViewById(R.id.tv_personal_tel);
        tv_personal_name = (FontTextView) findViewById(R.id.tv_personal_name);
        tv_personal_unit = (FontTextView) findViewById(R.id.tv_personal_unit);

        iv_personal_icon.setImageBitmap(bitmap2);
        tv_personal_name.setText(PrefUtils.getString(this, GlobalConstants.PERSONAL_ACCOUNT, null));
        tv_personal_unit.setText(PrefUtils.getString(this, GlobalConstants.PERSONAL_POSITION, null));
        tv_personal_tel.setText(PrefUtils.getString(this, GlobalConstants.PERSONAL_TELTPHONE, null));


        btnSettingContactSave = (Button) findViewById(R.id.btn_setting_contact_save);
        btnSettingContactSave.setOnClickListener(this);


    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_setting_contact_infomation;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_setting_contact_save:
                //保存成功

                //获取数据
                String tel = et_setting_contact_tel.getText().toString().trim();
                String landline = et_setting_contact_landline.getText().toString().trim();
                String email = et_setting_contact_email.getText().toString().trim();
                String qq = et_setting_contact_qq.getText().toString().trim();
                String wechart = et_setting_contact_wechart.getText().toString().trim();
                if (TextUtils.isEmpty(tel))
                    tel = PrefUtils.getString(this, GlobalConstants.PERSONAL_TEL, null);
                if (TextUtils.isEmpty(landline))
                    landline = PrefUtils.getString(this, GlobalConstants.PERSONAL_TELTPHONE, null);
                if (TextUtils.isEmpty(email))
                    email = PrefUtils.getString(this, GlobalConstants.PERSONAL_EMAIL, null);
                if (TextUtils.isEmpty(qq))
                    qq = PrefUtils.getString(this, GlobalConstants.PERSONAL_QQ, null);
                if (TextUtils.isEmpty(wechart))
                    wechart = PrefUtils.getString(this, GlobalConstants.PERSONAL_WECHATNO, null);

//                PERSONAL_UPLOAD_INFO_URL += PrefUtils.getString(this,GlobalConstants.PASS_WORD,null) + "/" +tel + "/" + landline + "/" + email + "/" + wechart + "/" + qq;
                String url = PERSONAL_UPLOAD_INFO_URL + PrefUtils.getString(this, GlobalConstants.PASS_WORD, null) + "/" + toURLEncoded(tel) + "/" + toURLEncoded(landline) + "/" + toURLEncoded(email) + "/" + toURLEncoded(wechart) + "/" + toURLEncoded(qq);
                PersonalInfoPresenter presenter = new PersonalInfoPresenter(this);
                presenter.getInfo(url);


                break;
        }
    }

    @Override
    public void successed(InfoJson.InfoData infoData) {
        if (infoData == null) {
            showToast("数据有误", TOAST_SHORT);
            return;
        }

        PrefUtils.putString(this, GlobalConstants.PERSONAL_TELTPHONE, infoData.getTelephone());
        PrefUtils.putString(this, GlobalConstants.PERSONAL_WECHATNO, infoData.getWeChatNO());
        PrefUtils.putString(this, GlobalConstants.PERSONAL_TEL, infoData.getPhone());
        PrefUtils.putString(this, GlobalConstants.PERSONAL_EMAIL, infoData.getEmail());
        PrefUtils.putString(this, GlobalConstants.PERSONAL_QQ, infoData.getQQ());


        showToast("修改成功", TOAST_SHORT);
        //设置个人信息


        Intent data = new Intent();//只是回传数据就不用写跳转对象
        data.putExtra("data", "成功");//数据放到data里面去
        setResult(ACTIVITY_REQUEST_PREVIEW_RESULT, data);//返回data，2为result，data为intent对象
        finish();//页面销毁

    }

    @Override
    public void failed(String s) {
        showToast(s, TOAST_SHORT);
        finish();
    }


}
