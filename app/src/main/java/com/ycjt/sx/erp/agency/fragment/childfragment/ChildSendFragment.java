package com.ycjt.sx.erp.agency.fragment.childfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseFragment;
import com.ycjt.sx.erp.agency.activity.DetailsActivity;
import com.ycjt.sx.erp.agency.adapter.AgencyWaitAdapter;
import com.ycjt.sx.erp.agency.bean.AgencyUrlBean;
import com.ycjt.sx.erp.agency.bean.json.AgencyJson.AgencyDataJson;
import com.ycjt.sx.erp.agency.bean.json.HomePageParseJson;
import com.ycjt.sx.erp.agency.presenter.AgencySendMvpPresenter;
import com.ycjt.sx.erp.agency.presenter.AgencyWaitMvpPresenter;
import com.ycjt.sx.widget.ListViewDecoration;

import java.util.ArrayList;

/**
 * Created by liuchao on 2017/6/19.
 */

public class ChildSendFragment extends BaseFragment implements IAgencySend {

    private SwipeMenuRecyclerView rlvSendChild;
    private ArrayList<AgencyDataJson> mLists;
    private AgencyWaitAdapter adapter;
    private SwipeRefreshLayout sendR;
    private boolean isSendFirst = true;
    private boolean isSendResh = true;
    private AgencySendMvpPresenter sendMvpPresenter;


    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {

        sendMvpPresenter = new AgencySendMvpPresenter(mActivity, this);
        sendMvpPresenter.getSendData(new AgencyUrlBean(mActivity, "3", 0));

        mLists = new ArrayList<>();

        sendR = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_send_layout);
        sendR.setOnRefreshListener(mOnRefreshListener);
        rlvSendChild = (SwipeMenuRecyclerView) mRootView.findViewById(R.id.rv_send_child_agency);
        rlvSendChild.setLayoutManager(new LinearLayoutManager(mActivity));// 布局管理器。
        rlvSendChild.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlvSendChild.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlvSendChild.addItemDecoration(new ListViewDecoration());// 添加分割线。
        // 添加滚动监听。
        rlvSendChild.addOnScrollListener(mOnScrollListener);

    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            isSendResh = true;
            sendMvpPresenter.getSendData(new AgencyUrlBean(mActivity, "3", 0));
        }
    };

    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
                isSendResh = true;
                sendMvpPresenter.getSendMoreData(new AgencyUrlBean(mActivity, "3", mLists.size()));
            }
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.child_fragment_send;
    }

    @Override
    public void successed(HomePageParseJson parseJson) {
        //拿到json格式的字符串,将其转化到集合中
        //模拟集合 传递到详细页面
        mLists.clear();
        ArrayList<AgencyDataJson> mList = parseJson.parseYesIDoData();
        if (mList == null || mList.size() < 0) {
            showToast("无数据", TOAST_SHORT);
            return;
        }
        for (int j = 0; j < mList.size(); j++) {
            mLists.add(mList.get(j));
        }
        setCommonContract(rlvSendChild, mLists);
    }

    @Override
    public void failed(String s) {
        showToast(s, TOAST_SHORT);
    }

    @Override
    public void startDialog() {

        if (isSendFirst) {
            sendR.setRefreshing(!isSendFirst);
            isSendFirst = false;
        } else {

            sendR.setRefreshing(!isSendFirst);
        }
    }

    @Override
    public void stopDialog() {
        sendR.setRefreshing(isSendFirst);
        isSendResh = false;
    }

    @Override
    public void successedMore(HomePageParseJson parseJson) {

        if (parseJson == null) {
            showToast("没有更多数据了", TOAST_SHORT);
            return;
        }

        showToast("获取数据成功", TOAST_SHORT);
        ArrayList<AgencyDataJson> mList = parseJson.parseYesIDoData();
        if (mList == null || mList.size() < 0) {
            showToast("无数据", TOAST_SHORT);
            return;
        }
        for (int j = 0; j < mList.size(); j++) {
            mLists.add(mList.get(j));
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * listView展示
     *
     * @param rlv
     */

    private void setCommonContract(SwipeMenuRecyclerView rlv, ArrayList<AgencyDataJson> mList) {
        adapter = new AgencyWaitAdapter(getContext(), mList);
        rlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new AgencyWaitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("AgencyDataJson.class", mList.get(position));
                intent.putExtra("AgencyDataJson", bundle);
                startActivity(intent);
            }
        });
    }


}
