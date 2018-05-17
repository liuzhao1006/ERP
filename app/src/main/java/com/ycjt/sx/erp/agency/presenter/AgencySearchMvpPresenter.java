package com.ycjt.sx.erp.agency.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ycjt.sx.erp.agency.bean.AgencyUrlBean;
import com.ycjt.sx.erp.agency.bean.json.AgencyJson;
import com.ycjt.sx.erp.agency.bean.json.HomePageParseJson;
import com.ycjt.sx.erp.agency.fragment.IAgencySearch;
import com.ycjt.sx.erp.agency.model.AgencyMvpSearchModel;

import java.util.List;

import static com.ycjt.sx.erp.agency.config.WaitUrlConfig.getSearchUrl;
import static com.ycjt.sx.erp.agency.config.WaitUrlConfig.getSendUrl;

/**
 * Created by liuchao on 2017/7/18.
 */

public class AgencySearchMvpPresenter implements ISearchPresenter {

    private Context context;
    private IAgencySearch search;
    private final AgencyMvpSearchModel searchModel;

    public AgencySearchMvpPresenter(Context context, IAgencySearch search) {
        this.context = context;
        this.search = search;
        searchModel = new AgencyMvpSearchModel(context, this);
    }


    /**
     * 首次加载,或者下拉刷新
     *
     * @param bean
     */
    public void getSearchData(AgencyUrlBean bean) {
        searchModel.getSearchData(getSearchUrl(bean), 0);
    }

    /**
     * 加载更多
     *
     * @param bean
     */
    public void getSearchMoreData(AgencyUrlBean bean) {
        searchModel.getSearchData(getSearchUrl(bean), 1);
    }

    @Override
    public void searchSuccessed(String data) {
        List<AgencyJson.AgencyDataJson> dataList = JSON.parseArray(data, AgencyJson.AgencyDataJson.class);
        search.successedSearch(new HomePageParseJson(dataList));
    }

    @Override
    public void searchMoreSuccessed(String data) {
        List<AgencyJson.AgencyDataJson> dataList = JSON.parseArray(data, AgencyJson.AgencyDataJson.class);
        search.successedSearchMore(new HomePageParseJson(dataList));
    }

    @Override
    public void searchFailed(String message) {
        search.failed(message);
    }

    @Override
    public void startDialog() {
        search.startDialog();
    }

    @Override
    public void stopDialog() {
        search.stopDialog();
    }


}
