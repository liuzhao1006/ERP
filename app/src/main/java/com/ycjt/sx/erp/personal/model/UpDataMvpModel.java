package com.ycjt.sx.erp.personal.model;

import android.content.Context;
import android.graphics.Bitmap;

import com.ycjt.sx.base.inter.IBitmapModel;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.base.mvpbase.BaseHttpUtils;
import com.ycjt.sx.erp.personal.presenter.IPersonalPresenter;
import com.ycjt.sx.erp.personal.presenter.IPersonalUpDataPresenter;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by liuchao on 2017/7/21.
 */

public class UpDataMvpModel implements IStringModel {

    private final BaseHttpUtils instance;
    private Context context;
    private PromptDialog promptDialog;
    private IPersonalUpDataPresenter upDataPresenter;

    public UpDataMvpModel(Context context, IPersonalUpDataPresenter upDataPresenter) {
        this.context = context;
        this.upDataPresenter = upDataPresenter;
        instance = BaseHttpUtils.getInstance();
    }

    /**
     * 获取数据
     */
    public void getStringData(String url, int i) {
        instance.getStringRequest(url, i, this);
    }



    /*==============================数据回调==============================*/

    @Override
    public void successed(int what, String data) {
        upDataPresenter.getStringSuccessed(data);
    }

    @Override
    public void failed(int what, String message) {
        upDataPresenter.getFailed(message);
    }

    @Override
    public void startDialog() {
        upDataPresenter.startDialog();
    }

    @Override
    public void stopDialog() {
        upDataPresenter.stopDialog();
    }

}
