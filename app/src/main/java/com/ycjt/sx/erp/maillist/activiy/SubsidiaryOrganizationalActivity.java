package com.ycjt.sx.erp.maillist.activiy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.erp.maillist.adapter.NormalRecyclerAdapter;
import com.ycjt.sx.erp.maillist.bean.MaillistUrlbean;
import com.ycjt.sx.erp.maillist.bean.json.MaillistDataJson;
import com.ycjt.sx.erp.maillist.presenter.OrganizationalMvpPresenter;
import com.ycjt.sx.widget.customrecycle.XRefreshView;
import com.ycjt.sx.widget.recycleline.DividerItemDecoration;

import java.util.List;

/**
 * http://192.168.103.247:8011/Service1.svc
 * /GetOrganizeUser/99D7E378-0BE0-4703-B157-E88C100C5DF1/DEVICEID/04f12beb-d99d-43df-ac9a-3042957d6bda/0
 */
public class SubsidiaryOrganizationalActivity extends BaseActivity implements IOrganizational {
    RecyclerView recyclerView;
    NormalRecyclerAdapter adapter;
    XRefreshView xRefreshView;
    LinearLayoutManager layoutManager;
    private OrganizationalMvpPresenter presenter;

    @Override
    protected void initView() {
        //获取到跳转过来的内容
        Bundle bundle = (Bundle) getIntent().getExtras().get("bundle");
        MaillistDataJson userBean = (MaillistDataJson) bundle.getSerializable("List");
        isRefresh = true;
        presenter = new OrganizationalMvpPresenter(this, this);
        presenter.getOrganizationalData(new MaillistUrlbean(this));
        ibTopLeft.setVisibility(View.VISIBLE);
        ibTopRight.setVisibility(View.GONE);
        tvTopModdel.setText(userBean.getName());
        ivFalse.setVisibility(View.VISIBLE);
        ibTopLeft.setOnClickListener(view -> finish());
        xRefreshView = (XRefreshView) findViewById(R.id.xrefreshview);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_test_rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.enableReleaseToLoadMore(true);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);
        xRefreshView.setOnRecyclerViewScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                presenter.getOrganizationalData(new MaillistUrlbean(SubsidiaryOrganizationalActivity.this));
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        // 刷新完成必须调用此方法停止加载
                        xRefreshView.stopLoadMore(true);
                        //当数据加载失败 不需要隐藏footerview时，可以调用以下方法，传入false，不传默认为true
                        // 同时在Footerview的onStateFinish(boolean hideFooter)，可以在hideFooter为false时，显示数据加载失败的ui
//                            xRefreshView1.stopLoadMore(false);
                    }
                }, 1000);
            }
        });
    }


    /**
     * 分中心列表
     */
    private void setCommonContract(RecyclerView r, List<MaillistDataJson> dataList) {
        adapter = new NormalRecyclerAdapter(dataList, this);
        // 静默加载模式不能设置footerview
        r.setAdapter(adapter);
        //设置刷新完成以后，headerview固定的时间
        adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(SubsidiaryOrganizationalActivity.this, ItemSubsidiaryActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("List", dataList.get(position));
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        });
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_subsidiary_organizational;
    }

    @Override
    public void successed(List<MaillistDataJson> dataList) {
        //判断是刷新还是第一次进来
        if (isRefresh) {
            setCommonContract(recyclerView, dataList);
        } else {
            if (adapter != null) {
                adapter.setData(dataList);
            } else {
                setCommonContract(recyclerView, dataList);
            }
        }
    }

    @Override
    public void failed(String s) {
        showToast(s, TOAST_SHORT);

    }

    @Override
    public void startDialog() {
        xRefreshView.startRefresh();
    }

    @Override
    public void stopDialog() {
        xRefreshView.stopRefresh();
    }


    private boolean isRefresh = true;

}
