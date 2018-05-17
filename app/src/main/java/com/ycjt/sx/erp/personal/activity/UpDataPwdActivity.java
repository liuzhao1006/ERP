package com.ycjt.sx.erp.personal.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.R;
import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.app.YCJTApplication;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.erp.personal.bean.UpPwdBean;
import com.ycjt.sx.erp.personal.presenter.PersonalUpDataMvpPresenter;
import com.ycjt.sx.login.view.LoginActivity;
import com.ycjt.sx.utils.PrefUtils;
import com.ycjt.sx.widget.fonts.FontTextView;

/**
 * Created by liuchao on 2017/6/20.
 * 修改密码
 */

public class UpDataPwdActivity extends BaseActivity implements View.OnClickListener, IUpDataPwd {

    /*=======个人===*/
    private ImageView ivPersonalIcon;
    private FontTextView tvPersonalName;
    private FontTextView tvPersonalTel;
    private FontTextView tvPersonalUnit;
    /*=======修改输入框====*/
    private EditText etPersonalUpdataConfirmPwd;
    private EditText etPersonalUpdataNewPwd;
    private EditText etPersonalUpdataOldPwd;
    private Button btnPersonalUpdataSave;
    private FontTextView tvPersonalForget;

    @Override
    protected void initView() {

        //接收值
        Intent intent = getIntent();
        byte[] bytes = intent.getByteArrayExtra("byte");
        Bitmap bitmap = getBitmap(bytes);

        /*===============头布局===============*/

        tvTopModdel.setText("修改密码");
        ibTopLeft.setVisibility(View.VISIBLE);
        ivFalse.setVisibility(View.VISIBLE);
        ibTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ibTopRight.setVisibility(View.GONE);
        /*==============修改密码页面,个人信息=============*/
        ivPersonalIcon = (ImageView) findViewById(R.id.iv_personal_icon);
        tvPersonalName = (FontTextView) findViewById(R.id.tv_personal_name);
        tvPersonalTel = (FontTextView) findViewById(R.id.tv_personal_tel);
        tvPersonalUnit = (FontTextView) findViewById(R.id.tv_personal_unit);

        ivPersonalIcon.setImageBitmap(bitmap);
        tvPersonalName.setText(PrefUtils.getString(this, GlobalConstants.PERSONAL_ACCOUNT, null));
        tvPersonalTel.setText(PrefUtils.getString(this, GlobalConstants.PERSONAL_TEL, null));
        tvPersonalUnit.setText(PrefUtils.getString(this, GlobalConstants.PERSONAL_POSITION, null));

        /*=====================修改密码输入框===================*/
        etPersonalUpdataOldPwd = (EditText) findViewById(R.id.et_personal_updata_old_pwd);
        etPersonalUpdataNewPwd = (EditText) findViewById(R.id.et_personal_updata_new_pwd);
        etPersonalUpdataConfirmPwd = (EditText) findViewById(R.id.et_personal_updata_confirm_pwd);

        /*===========提交=========*/
        tvPersonalForget = (FontTextView) findViewById(R.id.tv_personal_forget);
        btnPersonalUpdataSave = (Button) findViewById(R.id.btn_personal_updata_save);
        btnPersonalUpdataSave.setOnClickListener(this);
        tvPersonalForget.setText("若忘记旧密码,请联系系统管理员找回或重置!");

    }

    public Bitmap getBitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);//从字节数组解码位图
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_personal_updata_pwd;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_personal_updata_save:
                //提交信息
                getInfo();

                break;
            case R.id.tv_top_base_moddle:
                //之前activity没有销毁,可以结束这个页面.
                finish();
                break;
        }
    }

    private void getInfo() {
        String oldPwd = etPersonalUpdataOldPwd.getText().toString().trim();
        String newPwd = etPersonalUpdataNewPwd.getText().toString().trim();
        String confirmPwd = etPersonalUpdataConfirmPwd.getText().toString().trim();
        if (!newPwd.equals(confirmPwd) || TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(newPwd) || TextUtils.isEmpty(confirmPwd)) {
            showToast("请核对输入的信息", TOAST_SHORT);
            return;
        }
        UpPwdBean bean = new UpPwdBean(this);
        bean.setNewPwd(newPwd);
        bean.setOldPwd(oldPwd);
        LogUtils.i(bean.toString());
        PersonalUpDataMvpPresenter presenter = new PersonalUpDataMvpPresenter(this, this);
        presenter.getInfo(bean);
    }

    @Override
    public void successed(String s) {
        if (TextUtils.isEmpty(s)) {
            showToast("数据有误", TOAST_SHORT);
            return;
        }
        JSONObject jsonObject = JSON.parseObject(s);
        String message = jsonObject.getString("message");
        showToast(message, TOAST_SHORT);
        //修改成功,跳转到登录页面重新登录
        PrefUtils.putString(this, GlobalConstants.PASS_WORD, "");
        Intent intent = new Intent(this, LoginActivity.class);
        YCJTApplication.exit();
        startActivity(intent);
        finish();
    }

    @Override
    public void failed(String s) {
        showToast(s, TOAST_SHORT);
    }

    @Override
    public void startDialog() {
        showDialog(true, "修改密码中", "正在修改,请稍后......");
    }

    @Override
    public void stopDialog() {
        cancleDialog();
    }
}
