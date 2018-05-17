package com.ycjt.sx.erp.agency.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.R;
import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.erp.agency.adapter.AgencyDetailsAdapter;
import com.ycjt.sx.erp.agency.bean.json.AgencyDetailsDataJson;
import com.ycjt.sx.erp.agency.bean.json.AgencyJson.AgencyDataJson;
import com.ycjt.sx.erp.agency.presenter.AgencyDetailsPresenter;
import com.ycjt.sx.utils.PrefUtils;
import com.ycjt.sx.utils.Utils;
import com.ycjt.sx.widget.fonts.FontTextView;

import java.util.List;

import static com.ycjt.sx.app.InterfaceConfig.APP_IP;
import static com.ycjt.sx.app.InterfaceConfig.APP_POST;
import static com.ycjt.sx.app.InterfaceConfig.HOME_PAGE_FLOW;
import static com.ycjt.sx.app.InterfaceConfig.HTTP;

public class DetailsActivity extends BaseActivity implements IAgencyDetails {


    private FontTextView tvDetailsTheme;
    private FontTextView tvDetailsNode;
    private FontTextView tvDetailsName;
    private FontTextView tvDetailsTime;

    private int currentState = 0;

    public String HOME_PAGE_DATA_URL = HTTP + APP_IP + ":" + APP_POST + HOME_PAGE_FLOW + PrefUtils.getString(this, GlobalConstants.PERSONAL_USERID, null) + "/" + PrefUtils.getString(this, GlobalConstants.PERSONAL_DEVICEID, null) + "/";
    private RecyclerView rlvDetails;
    private AgencyDataJson serializable;

    @Override
    protected void initView() {

        ibTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            Bundle agencyDataJson = intent.getBundleExtra("AgencyDataJson");
            serializable = (AgencyDataJson) agencyDataJson.getSerializable("AgencyDataJson.class");
            LogUtils.i(serializable);
            HOME_PAGE_DATA_URL += serializable.getFlowID() + "/" + serializable.getInstanceID();
            AgencyDetailsPresenter presenter = new AgencyDetailsPresenter(this, HOME_PAGE_DATA_URL, "s");
            presenter.getWaitMessage();
        }

        rlvDetails = (RecyclerView) findViewById(R.id.underLineLayout);


        ibTopLeft.setVisibility(View.VISIBLE);
        ivFalse.setVisibility(View.VISIBLE);
        ibTopRight.setVisibility(View.GONE);
        tvDetailsTheme = (FontTextView) findViewById(R.id.tv_details_theme);
        tvDetailsNode = (FontTextView) findViewById(R.id.tv_details_node);
        tvDetailsName = (FontTextView) findViewById(R.id.tv_details_name);
        tvDetailsTime = (FontTextView) findViewById(R.id.tv_details_time);
        ibTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsActivity.this.finish();
            }
        });

        String time = Utils.time(Long.parseLong(serializable.getSenderTime().substring(6, 19)));
        tvTopModdel.setText("流程详情");
        tvDetailsTheme.setText(serializable.getTitle());
        tvDetailsName.setText("当前节点:" + serializable.getPosition());
        tvDetailsName.setText("发送人:" + serializable.getSenderName());
        tvDetailsTime.setText(time);


    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_details;
    }


    @Override
    public void successed(List<AgencyDetailsDataJson> dataList) {
        setDetailsData(rlvDetails, dataList);
    }

    @Override
    public void failed(String s) {
        showToast(s, TOAST_SHORT);
    }

    private void setDetailsData(RecyclerView rlv, List<AgencyDetailsDataJson> mList) {
        rlv.setLayoutManager(new LinearLayoutManager(this));
        AgencyDetailsAdapter adapter = new AgencyDetailsAdapter(this, mList);
        rlv.setAdapter(adapter);
//        rlv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter.setOnItemClickListener(new AgencyDetailsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


            }
        });
    }
}
