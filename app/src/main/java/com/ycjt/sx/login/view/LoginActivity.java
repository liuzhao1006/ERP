package com.ycjt.sx.login.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.R;
import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.base.MainActivity;
import com.ycjt.sx.login.bean.UserBean;
import com.ycjt.sx.login.presenter.LoginMvpPresenter;
import com.ycjt.sx.utils.PrefUtils;
import com.ycjt.sx.widget.fonts.FontTextView;

import java.util.ArrayList;

import static com.ycjt.sx.app.InterfaceConfig.APP_ORG_ID;

/**
 * Created by liuchao on 2017/6/17.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILogin {

    private TextInputEditText etUserName;
    private TextInputEditText etPassWord;
    private String userName;
    private String passWord;
    private Button btnLogin;
    private FontTextView tvLoginFind;
    private LoginMvpPresenter mvpPresenter;
    private UserBean bean;

    @Override
    protected void initView() {
        //设置网络
        if (!CheckNetwork()) showCheckNetDialog();
        mvpPresenter = new LoginMvpPresenter(this, this);
        //获取控件
        etUserName = (TextInputEditText) findViewById(R.id.et_login_name);
        etPassWord = (TextInputEditText) findViewById(R.id.et_login_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvLoginFind = (FontTextView) findViewById(R.id.tv_login_findlogin);
        btnLogin.setOnClickListener(this);
        tvLoginFind.setText("若忘记密码，请联系系统管理员找回或重置！");
        //修改输入框样式
        updataIcon(etUserName);
        updataIcon(etPassWord);
        userName = PrefUtils.getString(this, GlobalConstants.USER_NAME, null);
        passWord = PrefUtils.getString(this, GlobalConstants.PASS_WORD, null);
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord)) {
            //保存过账号和密码,就可以直接进行登录了
            //登录
            etPassWord.setText(passWord);
            etUserName.setText(userName);
            setData(userName, passWord);
        }
    }


    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                userName = etUserName.getText().toString().trim();
                passWord = etPassWord.getText().toString().trim();
                setData(userName, passWord);
                break;
        }
    }

    /**
     * 登录逻辑
     */
    private void setData(String user, String pwd) {
        bean = new UserBean(this);
        bean.setUserName(user);
        bean.setPassWord(pwd);
        if (mvpPresenter.isEmpty(bean)) {
            showToast("用户名和密码不能为空。。。", Toast.LENGTH_SHORT);
            return;
        }
        mvpPresenter.getLogin(bean);//传递的参数为登录的URL
        //服务器连接不同
//        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void loginSuccessed(ArrayList<String> mList) {
        if (mList == null || mList.size() < 0) {
            showToast("数据有误", TOAST_SHORT);
            return;
        }
        //登录成功,保存账户和密码
        PrefUtils.putString(this, GlobalConstants.USER_NAME, userName);
        PrefUtils.putString(this, GlobalConstants.PASS_WORD, passWord);
        //存储有几个组织机构
        PrefUtils.putString(this, GlobalConstants.ORG_SOME, mList.size() + "");
        if (mList.size() == 1) {
            //只有一个组织机构,直接跳转到首页
            String item = mList.get(0);
            String[] split = item.split("::");
            LogUtils.i(split[1]);
            PrefUtils.putString(LoginActivity.this, APP_ORG_ID, split[1]);
            PrefUtils.putString(LoginActivity.this, GlobalConstants.PERSONAL_CENTER, split[0]);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(LoginActivity.this, OrganizationActivity.class);
            intent.putStringArrayListExtra(GlobalConstants.SELECTOR_ORG, mList);
            startActivity(intent);
        }
        finish();
    }

    @Override
    public void loginFailed(String s) {
        showToast(s, TOAST_SHORT);
    }

    @Override
    public void startDialog() {
        showDialog(true, "正在登录", "请稍后...");
    }

    @Override
    public void stopDialog() {
        cancleDialog();
    }


}
