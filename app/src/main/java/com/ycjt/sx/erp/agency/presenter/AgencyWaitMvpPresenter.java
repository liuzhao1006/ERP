package com.ycjt.sx.erp.agency.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.erp.agency.bean.AgencyUrlBean;
import com.ycjt.sx.erp.agency.bean.json.AgencyJson;
import com.ycjt.sx.erp.agency.bean.json.HomePageParseJson;
import com.ycjt.sx.erp.agency.config.WaitUrlConfig;
import com.ycjt.sx.erp.agency.fragment.childfragment.IAgencyWait;
import com.ycjt.sx.erp.agency.model.AgencyMvpWaitModel;
import com.ycjt.sx.widget.customrecycle.adapter.LogUtils;

import java.util.List;

import static com.ycjt.sx.erp.agency.config.WaitUrlConfig.getWaitUrl;

/**
 * Created by liuchao on 2017/7/18.
 */

public class AgencyWaitMvpPresenter implements IWaitPresenter {

    private Context context;
    private IAgencyWait wait;
    private AgencyMvpWaitModel waitModel;

    public AgencyWaitMvpPresenter(Context context, IAgencyWait wait) {
        this.context = context;
        this.wait = wait;
        waitModel = new AgencyMvpWaitModel(context, this);
    }


    /**
     * 首次加载,或者下拉刷新
     *
     * @param bean
     */
    public void getWaitData(AgencyUrlBean bean) {
        LogUtils.e(getWaitUrl(bean));
        waitModel.getWaitData(getWaitUrl(bean), 0);
    }

    /**
     * 加载更多
     *
     * @param bean
     */
    public void getWaitMoreData(AgencyUrlBean bean) {
        waitModel.getWaitData(getWaitUrl(bean), 1);
    }

    @Override
    public void waitSuccessed(String data) {
        List<AgencyJson.AgencyDataJson> dataList = JSON.parseArray(data, AgencyJson.AgencyDataJson.class);
        wait.successed(new HomePageParseJson(dataList));
    }

    @Override
    public void waitMoreSuccessed(String data) {
        List<AgencyJson.AgencyDataJson> dataList = JSON.parseArray(data, AgencyJson.AgencyDataJson.class);
        wait.successedMore(new HomePageParseJson(dataList));
    }

    @Override
    public void waitFailed(String message) {
        wait.failed(message);
    }

    @Override
    public void startDialog() {
        wait.startDialog();
    }

    @Override
    public void stopDialog() {
        wait.stopDialog();
    }


}
