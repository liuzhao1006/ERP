package com.ycjt.sx.erp.message.model;

import android.content.Context;
import android.graphics.Bitmap;

import com.ycjt.sx.base.inter.IBitmapModel;
import com.ycjt.sx.base.inter.IStringModel;
import com.ycjt.sx.base.mvpbase.BaseHttpUtils;
import com.ycjt.sx.erp.message.presenter.IMessagePresenter;

/**
 * Created by liuchao on 2017/7/17.
 */

public class MessageStringModel implements IStringModel, IBitmapModel {

    private Context context;
    private IMessagePresenter messagePresenter;

    private BaseHttpUtils instance;

    public MessageStringModel(Context context, IMessagePresenter messagePresenter) {
        this.context = context;
        this.messagePresenter = messagePresenter;
        instance = BaseHttpUtils.getInstance();
    }


    /**
     * 获取消息列表
     *
     * @param url
     */
    public void getMessageData(String url,int i) {
        instance.getStringRequest(url,i,  this);

    }


    @Override
    public void successed(int what,String data) {

    }

    @Override
    public void failed(int what,String message) {

    }

    @Override
    public void startDialog() {

    }

    @Override
    public void stopDialog() {

    }


    /**
     * 获取图像
     *
     * @param url
     */
    public void getMessageBitMap(String url) {
        instance.getBitMapRequest(url, this);
    }


    @Override
    public void successedBit(Bitmap bitmap) {

    }

    @Override
    public void failedBit(String s) {

    }

    @Override
    public void startDialogBit() {

    }

    @Override
    public void stopDialogBit() {

    }
}
