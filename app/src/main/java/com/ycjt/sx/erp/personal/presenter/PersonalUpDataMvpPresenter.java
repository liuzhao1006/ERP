package com.ycjt.sx.erp.personal.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.ycjt.sx.erp.personal.activity.IUpDataPwd;
import com.ycjt.sx.erp.personal.bean.PersonalBean;
import com.ycjt.sx.erp.personal.bean.UpPwdBean;
import com.ycjt.sx.erp.personal.fragment.IPersonal;
import com.ycjt.sx.erp.personal.model.PersonMvpModel;
import com.ycjt.sx.erp.personal.model.UpDataMvpModel;

import static com.ycjt.sx.erp.personal.PersonalUrlConfig.getPersonalUrl;
import static com.ycjt.sx.erp.personal.PersonalUrlConfig.getUpDataUrl;

/**
 * Created by liuchao on 2017/6/20.
 */

public class PersonalUpDataMvpPresenter implements IPersonalUpDataPresenter {

    private IUpDataPwd pwd;
    private Context context;
    private UpDataMvpModel mvpModel;

    public PersonalUpDataMvpPresenter(Context context, IUpDataPwd pwd) {
        this.pwd = pwd;
        this.context = context;
        mvpModel = new UpDataMvpModel(context, this);
    }

    /**
     * 获取个人信息
     */
    public void getInfo(UpPwdBean bean) {
        String url = getUpDataUrl(bean);
        if (!TextUtils.isEmpty(url)) {
            mvpModel.getStringData(url, 0);
        } else {
            pwd.failed("修改密码失败,请联系管理员");
        }

    }


    @Override
    public void startDialog() {
        pwd.startDialog();
    }

    @Override
    public void stopDialog() {
        pwd.stopDialog();
    }

    @Override
    public void getStringSuccessed(String content) {
        pwd.successed(content);
    }

    @Override
    public void getFailed(String message) {
        pwd.failed(message);
    }

}
