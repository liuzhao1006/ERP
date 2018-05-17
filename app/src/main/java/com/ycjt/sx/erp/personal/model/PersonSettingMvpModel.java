package com.ycjt.sx.erp.personal.model;

import android.content.Context;
import android.graphics.Bitmap;

import com.ycjt.sx.base.inter.IBitmapModel;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.base.mvpbase.BaseHttpUtils;
import com.ycjt.sx.erp.personal.presenter.IPersonalPresenter;
import com.ycjt.sx.erp.personal.presenter.IPersonalSettingPresenter;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by liuchao on 2017/7/21.
 */

public class PersonSettingMvpModel implements IStringModel, IBitmapModel {

    private final BaseHttpUtils instance;
    private Context context;
    private PromptDialog promptDialog;
    private IPersonalSettingPresenter personal;

    public PersonSettingMvpModel(Context context, IPersonalSettingPresenter personal) {
        this.context = context;
        this.personal = personal;
        instance = BaseHttpUtils.getInstance();
    }

    /**
     * 获取数据
     */
    public void getStringData(String url,int i) {
        instance.getStringRequest(url, i, this);
    }

    /**
     * 上传图片的方法 post
     *
     * @param personalSettingUrl
     * @param mapToJson
     */
    public void getStringData(String personalSettingUrl, String mapToJson) {
        instance.postJsonUpLoad(personalSettingUrl, mapToJson, this);
    }

    public void getBitMapData(String url) {
        instance.getBitMapRequest(url, this);
    }


    /*==============================数据回调==============================*/

    @Override
    public void successed(int what,String data) {
        personal.getStringSuccessed(data);
    }

    @Override
    public void failed(int what,String message) {
        personal.getFailed(message);
    }

    @Override
    public void startDialog() {
        personal.startDialog();
    }

    @Override
    public void stopDialog() {
        personal.stopDialog();
    }

    /*================================图片回调===============================*/

    @Override
    public void successedBit(Bitmap bitmap) {
        personal.getBitMapSuccessed(bitmap);
    }

    @Override
    public void failedBit(String s) {
        personal.getFailed(s);
    }

    @Override
    public void startDialogBit() {
        personal.startDialog();
    }

    @Override
    public void stopDialogBit() {
        personal.stopDialog();
    }


}
