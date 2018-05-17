package com.ycjt.sx.erp.personal.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.ycjt.sx.erp.personal.activity.IPersonalSetting;
import com.ycjt.sx.erp.personal.bean.PersonalBean;
import com.ycjt.sx.erp.personal.bean.PersonalSettingBean;
import com.ycjt.sx.erp.personal.fragment.IPersonal;
import com.ycjt.sx.erp.personal.model.PersonMvpModel;
import com.ycjt.sx.erp.personal.model.PersonSettingMvpModel;

import static com.ycjt.sx.erp.personal.PersonalUrlConfig.getPersonalSettingUrl;
import static com.ycjt.sx.erp.personal.PersonalUrlConfig.getPersonalUrl;

/**
 * 个人设置图像获取
 */
public class PersonalSettingMvpPresenter implements IPersonalSettingPresenter {

    private IPersonalSetting personal;
    private Context context;
    private PersonSettingMvpModel mvpModel;

    public PersonalSettingMvpPresenter(Context context, IPersonalSetting personal) {
        this.personal = personal;
        this.context = context;

        mvpModel = new PersonSettingMvpModel(context, this);
    }


    /**
     * 更新图像
     */
    public void upDataImage(PersonalSettingBean bean) {
        mvpModel.getStringData(getPersonalSettingUrl(bean), bean.getMapToJson());
    }


    @Override
    public void startDialog() {
        personal.startDialog();
    }

    @Override
    public void stopDialog() {
        personal.stopDialog();
    }

    @Override
    public void getStringSuccessed(String content) {
        personal.successedInfo(content);
    }

    @Override
    public void getFailed(String message) {
        personal.failedInfo(message);
    }

    @Override
    public void getBitMapSuccessed(Bitmap bitmap) {

    }
}
