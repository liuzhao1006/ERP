package com.ycjt.sx.erp.agency.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.erp.agency.bean.AgencyUrlBean;
import com.ycjt.sx.erp.agency.bean.json.AgencyJson;
import com.ycjt.sx.erp.agency.bean.json.HomePageParseJson;
import com.ycjt.sx.erp.agency.fragment.childfragment.IAgencySend;
import com.ycjt.sx.erp.agency.fragment.childfragment.IAgencyWait;
import com.ycjt.sx.erp.agency.model.AgencyMvpSendModel;
import com.ycjt.sx.erp.agency.model.AgencyMvpWaitModel;

import java.util.List;

import static com.ycjt.sx.erp.agency.config.WaitUrlConfig.getSendUrl;
import static com.ycjt.sx.erp.agency.config.WaitUrlConfig.getWaitUrl;

/**
 * Created by liuchao on 2017/7/18.
 */

public class AgencySendMvpPresenter implements ISendPresenter {

    private Context context;
    private IAgencySend send;
    private final AgencyMvpSendModel sendModel;

    public AgencySendMvpPresenter(Context context, IAgencySend send) {
        this.context = context;
        this.send = send;
        sendModel = new AgencyMvpSendModel(context, this);
    }


    /**
     * 首次加载,或者下拉刷新
     *
     * @param bean
     */
    public void getSendData(AgencyUrlBean bean) {
        sendModel.getSendData(getSendUrl(bean), 0);
    }

    /**
     * 加载更多
     *
     * @param bean
     */
    public void getSendMoreData(AgencyUrlBean bean) {
        sendModel.getSendData(getWaitUrl(bean), 1);
    }

    @Override
    public void sendSuccessed(String data) {
        List<AgencyJson.AgencyDataJson> dataList = JSON.parseArray(data, AgencyJson.AgencyDataJson.class);
        send.successed(new HomePageParseJson(dataList));
    }

    @Override
    public void sendMoreSuccessed(String data) {
        List<AgencyJson.AgencyDataJson> dataList = JSON.parseArray(data, AgencyJson.AgencyDataJson.class);
        send.successedMore(new HomePageParseJson(dataList));
    }

    @Override
    public void sendFailed(String message) {
        send.failed(message);
    }

    @Override
    public void startDialog() {
        send.startDialog();
    }

    @Override
    public void stopDialog() {
        send.stopDialog();
    }


}
