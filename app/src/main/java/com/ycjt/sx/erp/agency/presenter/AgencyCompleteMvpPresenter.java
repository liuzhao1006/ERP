package com.ycjt.sx.erp.agency.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.erp.agency.bean.AgencyUrlBean;
import com.ycjt.sx.erp.agency.bean.json.AgencyJson;
import com.ycjt.sx.erp.agency.bean.json.HomePageParseJson;
import com.ycjt.sx.erp.agency.fragment.childfragment.IAgencyComplete;
import com.ycjt.sx.erp.agency.model.AgencyMvpCompleteModel;
import com.ycjt.sx.erp.agency.model.AgencyMvpWaitModel;

import java.util.List;

import static com.ycjt.sx.erp.agency.config.WaitUrlConfig.getCompleteUrl;
import static com.ycjt.sx.erp.agency.config.WaitUrlConfig.getWaitUrl;

/**
 * Created by liuchao on 2017/7/18.
 */

public class AgencyCompleteMvpPresenter implements ICompletePresenter {

    private Context context;
    private IAgencyComplete complete;
    private final AgencyMvpCompleteModel completeModel;

    public AgencyCompleteMvpPresenter(Context context, IAgencyComplete complete) {
        this.context = context;
        this.complete = complete;
        completeModel = new AgencyMvpCompleteModel(context, this);
    }


    /**
     * 首次加载,或者下拉刷新
     *
     * @param bean
     */
    public void getCompleteData(AgencyUrlBean bean) {
        completeModel.getCompleteData(getCompleteUrl(bean), 0);
    }

    /**
     * 加载更多
     *
     * @param bean
     */
    public void getCompleteMoreData(AgencyUrlBean bean) {
        completeModel.getCompleteData(getCompleteUrl(bean), 1);
    }

    @Override
    public void completeSuccessed(String data) {
        getData(data);
    }

    @Override
    public void completeMoreSuccessed(String data) {
        getData(data);
    }

    @Override
    public void completeFailed(String message) {
        complete.failed(message);
    }

    @Override
    public void startDialog() {
        complete.startDialog();
    }

    @Override
    public void stopDialog() {
        complete.stopDialog();
    }

    private void getData(String data) {
        List<AgencyJson.AgencyDataJson> dataList = JSON.parseArray(data, AgencyJson.AgencyDataJson.class);
        complete.successedMore(new HomePageParseJson(dataList));
    }


}
