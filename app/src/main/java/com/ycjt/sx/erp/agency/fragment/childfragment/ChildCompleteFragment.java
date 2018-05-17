package com.ycjt.sx.erp.agency.fragment.childfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseFragment;
import com.ycjt.sx.erp.agency.activity.DetailsActivity;
import com.ycjt.sx.erp.agency.adapter.AgencyWaitAdapter;
import com.ycjt.sx.erp.agency.bean.AgencyUrlBean;
import com.ycjt.sx.erp.agency.bean.json.AgencyJson.AgencyDataJson;
import com.ycjt.sx.erp.agency.bean.json.HomePageParseJson;
import com.ycjt.sx.erp.agency.presenter.AgencyCompleteMvpPresenter;
import com.ycjt.sx.erp.agency.presenter.AgencySendMvpPresenter;
import com.ycjt.sx.widget.ListViewDecoration;

import java.util.ArrayList;

/**
 * Created by liuchao on 2017/6/19.
 */

public class ChildCompleteFragment extends BaseFragment implements IAgencyComplete {

    private SwipeMenuRecyclerView rlvConpleteChild;

    private ArrayList<AgencyDataJson> mLists;
    private AgencyWaitAdapter adapter;
    private SwipeRefreshLayout completeR;

    private boolean isFirst = true;

    private boolean isComplete = true;
    private AgencyCompleteMvpPresenter mvpPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.child_fragment_complete;
    }

    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {
        mvpPresenter = new AgencyCompleteMvpPresenter(mActivity, this);
        mvpPresenter.getCompleteData(new AgencyUrlBean(mActivity, "2", 0));

        mLists = new ArrayList<>();
        rlvConpleteChild = (SwipeMenuRecyclerView) mRootView.findViewById(R.id.rv_complete_child_agency);
        completeR = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_comlete_layout);
        completeR.setOnRefreshListener(mOnRefreshListener);


        rlvConpleteChild.setLayoutManager(new LinearLayoutManager(mActivity));// 布局管理器。
        rlvConpleteChild.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rlvConpleteChild.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        rlvConpleteChild.addItemDecoration(new ListViewDecoration());// 添加分割线。
        rlvConpleteChild.addOnScrollListener(mOnScrollListener);// 添加滚动监听。
    }

    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            isComplete = true;
            mvpPresenter.getCompleteData(new AgencyUrlBean(mActivity, "2", 0));
        }
    };
    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
                isComplete = true;
                mvpPresenter.getCompleteMoreData(new AgencyUrlBean(mActivity, "2", mLists.size()));
            }
        }
    };


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
                bundle.putSerializable("AgencyDataJson.class", mLists.get(position));
                intent.putExtra("AgencyDataJson", bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void successed(HomePageParseJson parseJson) {
        //拿到json格式的字符串,将其转化到集合中
        //模拟集合 传递到详细页面
        mLists.clear();


        if (parseJson == null) {
            showToast("没有更多数据了", TOAST_SHORT);
            return;
        }
        ArrayList<AgencyDataJson> mList = parseJson.parseHaveToDoData();

        if (mList == null || mList.size() < 0) {
            showToast("无数据", TOAST_SHORT);
            return;
        }
        for (int j = 0; j < mList.size(); j++) {
            mLists.add(mList.get(j));
        }
        setCommonContract(rlvConpleteChild, mLists);

    }

    @Override
    public void failed(String s) {
        Toast.makeText(mActivity, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startDialog() {

        if (isFirst) {
            completeR.setRefreshing(!isFirst);
            isFirst = false;
        } else {
            completeR.setRefreshing(!isFirst);
        }
    }

    @Override
    public void stopDialog() {
        completeR.setRefreshing(isFirst);
        if (isComplete)
            showToast("获取数据成功", TOAST_SHORT);
        isComplete = false;
    }

    @Override
    public void successedMore(HomePageParseJson parseJson) {
        if (parseJson == null) {
            showToast("没有更多数据了", TOAST_SHORT);
            return;
        }
        ArrayList<AgencyDataJson> mList = parseJson.parseHaveToDoData();
        if (mList == null || mList.size() < 0) {
            showToast("无数据", TOAST_SHORT);
            return;
        }
        for (int j = 0; j < mList.size(); j++) {
            mLists.add(mList.get(j));
        }
        adapter.notifyDataSetChanged();
    }


}
