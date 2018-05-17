package com.ycjt.sx.erp.personal.model;

import android.content.Context;
import android.graphics.Bitmap;

import com.ycjt.sx.base.inter.IBitmapModel;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.base.mvpbase.BaseHttpUtils;
import com.ycjt.sx.erp.personal.presenter.IPersonalPresenter;
import com.ycjt.sx.login.presenter.ILoginPresenter;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by liuchao on 2017/7/21.
 */

public class PersonMvpModel implements IStringModel, IBitmapModel {

    private final BaseHttpUtils instance;
    private Context context;
    private PromptDialog promptDialog;
    private IPersonalPresenter personal;

    public PersonMvpModel(Context context, IPersonalPresenter personal) {
        this.context = context;
        this.personal = personal;
        instance = BaseHttpUtils.getInstance();
    }

    /**
     * 获取数据
     */
    public void getStringData(String url,int i) {
        instance.getStringRequest(url,i, this);
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
