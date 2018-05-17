package com.ycjt.sx.erp.personal.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.ycjt.sx.erp.personal.bean.PersonalBean;
import com.ycjt.sx.erp.personal.fragment.IPersonal;
import com.ycjt.sx.erp.personal.model.PersonMvpModel;

import static com.ycjt.sx.erp.personal.PersonalUrlConfig.getPersonalUrl;

/**
 * Created by liuchao on 2017/6/20.
 */

public class PersonalMvpPresenter implements IPersonalPresenter {

    private IPersonal personal;
    private Context context;
    private PersonMvpModel mvpModel;

    public PersonalMvpPresenter(Context context, IPersonal personal) {
        this.personal = personal;
        this.context = context;

        mvpModel = new PersonMvpModel(context, this);
    }

    /**
     * 获取个人信息
     */
    public void getInfo(PersonalBean bean) {
//        mvpModel.getStringData(getPersonalUrl(bean));
    }

    public void getBitInfo(PersonalBean bean) {
        mvpModel.getBitMapData(getPersonalUrl(bean));

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
        personal.successed(content);
    }

    @Override
    public void getFailed(String message) {
        personal.failed(message);
    }

    @Override
    public void getBitMapSuccessed(Bitmap bitmap) {
        personal.bitMap(bitmap);
    }
}
