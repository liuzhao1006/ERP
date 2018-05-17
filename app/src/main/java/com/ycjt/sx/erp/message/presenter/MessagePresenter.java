package com.ycjt.sx.erp.message.presenter;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by liuchao on 2017/7/17.
 */

public class MessagePresenter implements IMessagePresenter {

    private Context context;
    private ArrayList<String> mList;

    public MessagePresenter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
    }

    public void getStringContent(String url) {

    }

    @Override
    public void startDialog() {

    }

    @Override
    public void stopDialog() {

    }

    @Override
    public void getSuccessed(String content) {

    }

    @Override
    public void getFailed(String message) {

    }
}
