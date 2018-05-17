package com.ycjt.sx.erp.agency.fragment.childfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseFragment;
import com.ycjt.sx.erp.agency.activity.DetailsActivity;
import com.ycjt.sx.erp.agency.adapter.AgencyWaitAdapter;
import com.ycjt.sx.erp.agency.bean.AgencyUrlBean;
import com.ycjt.sx.erp.agency.bean.json.AgencyJson.AgencyDataJson;
import com.ycjt.sx.erp.agency.bean.json.HomePageParseJson;
import com.ycjt.sx.erp.agency.presenter.AgencyWaitMvpPresenter;
import com.ycjt.sx.widget.ListViewDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liuchao on 2017/6/19.
 */

public class ChildWaitFragment extends BaseFragment implements IAgencyWait {
    private SwipeMenuRecyclerView rvListView;
    private AgencyWaitAdapter adapter;
    private List<AgencyDataJson> mLists;
    private SwipeRefreshLayout waitR;
    private boolean isWaitFirst = true;
    private boolean isWaitResh = true;
    private AgencyWaitMvpPresenter mvpPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.child_fragment_wait;
    }


    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {

        mvpPresenter = new AgencyWaitMvpPresenter(mActivity, this);
        mvpPresenter.getWaitData(new AgencyUrlBean(mActivity, "1", 0));

        LogUtils.i(ChildWaitFragment.class.getSimpleName());
        mLists = new ArrayList<>();
        waitR = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_wait_layout);
        waitR.setOnRefreshListener(mOnRefreshListener);//下拉刷新
        rvListView = (SwipeMenuRecyclerView) mRootView.findViewById(R.id.rv_wait_child_agency);
        rvListView.setLayoutManager(new LinearLayoutManager(mActivity));// 布局管理器。
        rvListView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rvListView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rvListView.addItemDecoration(new ListViewDecoration());// 添加分割线。
        rvListView.addOnScrollListener(mOnScrollListener);// 添加滚动监听。

    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            isWaitResh = true;
            mvpPresenter.getWaitData(new AgencyUrlBean(mActivity, "1", 0));
        }
    };
    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {
                isWaitResh = true;
                mvpPresenter.getWaitMoreData(new AgencyUrlBean(mActivity, "1", mLists.size()));
            }
        }
    };


    @Override
    public void successed(HomePageParseJson parseJson) {
        count = 0;
        mLists.clear();
        ArrayList<AgencyDataJson> mList = parseJson.parseAgencyData();
        if (mList == null || mList.size() < 0) {
            showToast("无数据", TOAST_SHORT);
            sendReceiver(count);//count == 0 清除
            return;
        }

        for (int j = 0; j < mList.size(); j++) {
            mLists.add(mList.get(j));
        }
        setCommonContract(rvListView, mLists);
        for (int i = 0; i < mLists.size(); i++) {
            AgencyDataJson dataJson = mLists.get(i);
            String status = dataJson.getStatus();
            if (status.equals("0")) {
                count++;
            }
        }
        sendReceiver(count);
    }

    @Override
    public void failed(String s) {
        showToast(s, TOAST_SHORT);
    }

    @Override
    public void startDialog() {
        if (isWaitFirst) {
            waitR.setRefreshing(!isWaitFirst);
            isWaitFirst = false;
        } else {
            waitR.setRefreshing(!isWaitFirst);
        }
    }

    @Override
    public void stopDialog() {
        waitR.setRefreshing(isWaitFirst);
        isWaitResh = false;
    }

    private int count = 0;

    @Override
    public void successedMore(HomePageParseJson parseJson) {
        showToast("获取数据成功", TOAST_SHORT);
        count = 0;
        if (parseJson == null) {
            showToast("没有更多数据了", TOAST_SHORT);
            return;
        }
        ArrayList<AgencyDataJson> mList = parseJson.parseAgencyData();
        if (mList == null || mList.size() < 0) {
            showToast("无数据", TOAST_SHORT);
            return;
        }
        for (int j = 0; j < mList.size(); j++) {
            mLists.add(mList.get(j));
        }
        adapter.notifyDataSetChanged();
        //获取标记的代办数量
        for (int i = 0; i < mLists.size(); i++) {
            AgencyDataJson dataJson = mLists.get(i);
            String status = dataJson.getStatus();
            if (status.equals("0")) {
                count++;
            }
        }
        sendReceiver(count);
    }

    private void setCommonContract(SwipeMenuRecyclerView rlv, List<AgencyDataJson> mList) {
        adapter = new AgencyWaitAdapter(getContext(), mList);
        rlv.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getContext(), DetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("AgencyDataJson.class", mList.get(position));
            intent.putExtra("AgencyDataJson", bundle);
            startActivity(intent);
        });

    }

    /**
     * 将代办中未处理的数量封装起来通过广播发送到头布局控件中,和viewpager类中
     */
    private void sendReceiver(int count) {
        Intent intent = new Intent();
        intent.setAction("send.count");
        intent.putExtra("msg", count);
        mActivity.sendBroadcast(intent);
    }

}
