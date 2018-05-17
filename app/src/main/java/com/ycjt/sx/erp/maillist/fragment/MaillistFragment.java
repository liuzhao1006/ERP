package com.ycjt.sx.erp.maillist.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseFragment;
import com.ycjt.sx.erp.maillist.activiy.SubsidiaryOrganizationalActivity;
import com.ycjt.sx.erp.maillist.adapter.MaillistCommonContractAdapter;
import com.ycjt.sx.erp.maillist.adapter.MaillistOrganizationalAdapter;
import com.ycjt.sx.erp.maillist.bean.CommonContractBean;
import com.ycjt.sx.erp.maillist.bean.MaillistUrlbean;
import com.ycjt.sx.erp.maillist.bean.SubsidiaryBean;
import com.ycjt.sx.erp.maillist.bean.json.MaillistDataJson;
import com.ycjt.sx.erp.maillist.presenter.MaillistOandCMvpPresenter;
import com.ycjt.sx.erp.personal.activity.PersonalSettingActivity;
import com.ycjt.sx.widget.Ripple;
import com.ycjt.sx.widget.animal.AnimationUtils;
import com.ycjt.sx.widget.recycleline.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuchao on 2017/6/19.
 */

public class MaillistFragment extends BaseFragment implements View.OnClickListener, IMaillistFragment {
    private List<SubsidiaryBean> mList = new ArrayList<SubsidiaryBean>();
    private List<CommonContractBean> commonList = new ArrayList<CommonContractBean>();
    private SwipeMenuRecyclerView rlMaillistFragmentOrganizational;//组织架构列表
    private SwipeMenuRecyclerView rlvMaillistCommonContract;//常用联系人列表
    private SwipeRefreshLayout swipe_wait_organization_layout;

    private boolean isRefresh = true;

    private MaillistOrganizationalAdapter adapter;
    private MaillistOandCMvpPresenter presenter;


    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {
        //获取常用联系人
//        presenter.getCommon(MAILLIST_COMMON);
        presenter = new MaillistOandCMvpPresenter(mActivity, this);
        //获取组织机构数据
        presenter.getOrganizationalData(new MaillistUrlbean(mActivity));
        rlMaillistFragmentOrganizational = (SwipeMenuRecyclerView) mRootView.findViewById(R.id.rlv_maillist_fragment_organizational);
        rlvMaillistCommonContract = (SwipeMenuRecyclerView) mRootView.findViewById(R.id.rlv_maillist_common_contract);
//        updataIcon(setMaillistSearch);

        swipe_wait_organization_layout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_wait_organization_layout);
        swipe_wait_organization_layout.setOnRefreshListener(mOnRefreshListener);

        // 添加滚动监听。
        rlMaillistFragmentOrganizational.addOnScrollListener(mOnScrollListener);



    }

    /**
     * 组织架构
     */
    private void setOrganizational(RecyclerView r, List<MaillistDataJson> dataList) {
        r.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new MaillistOrganizationalAdapter(mActivity, dataList);
        r.setAdapter(adapter);
        r.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adapter.setOnItemClickListener(new MaillistOrganizationalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LogUtils.i(dataList.get(position).toString());
                //MaillistDataJson{
                // ID='04f12beb-d99d-43df-ac9a-3042957d6bda',
                // Name='亿程集团',
                // Number='6',
                // Type=1,
                // Status=0,
                // ParentID='00000000-0000-0000-0000-000000000000',
                // Sort=1,
                // Depth=0,
                // ChildsLength=304,
                // ChargeLeader=null,
                // Leader=null,
                // Note=null}
                Intent intent = new Intent(getContext(), SubsidiaryOrganizationalActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("List", dataList.get(position));
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }


    /**
     * 常用联系人
     */
    private void setCommonContract(RecyclerView r, List<MaillistDataJson> dataList) {
        r.setLayoutManager(new LinearLayoutManager(mActivity));

        commonList.clear();
        for (int i = 0; i < 10; i++) {
            CommonContractBean bean = new CommonContractBean();
            bean.setLv(getBytes(getBitmap(R.drawable.ren_icon)));
            bean.setName("刘朝" + i);
            bean.setPosition("大清生物" + i);
            bean.setTel(getBytes(getBitmap(R.drawable.icon_call)));
            bean.setNumTel("18629533616");
            commonList.add(bean);
        }
        MaillistCommonContractAdapter adapter = new MaillistCommonContractAdapter(mActivity, dataList);
        r.setAdapter(adapter);
        r.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adapter.setOnItemClickListener(new MaillistCommonContractAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), PersonalSettingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("List", dataList.get(position));
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_maillist;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }


    @Override
    public void startDialog() {
        swipe_wait_organization_layout.setRefreshing(true);
    }

    @Override
    public void stopDialog() {
        swipe_wait_organization_layout.setRefreshing(false);
    }


    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i("我重新回来了" + MaillistFragment.class.getSimpleName());
    }


    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了

                LogUtils.i(1);
            }
        }
    };


    /**
     * 刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            isRefresh = false;
            //获取组织机构数据
            presenter.getOrganizationalData(new MaillistUrlbean(mActivity));

        }
    };


    @Override
    public void successedOrganizational(List<MaillistDataJson> dataList) {
        if (isRefresh) {
            setOrganizational(rlMaillistFragmentOrganizational, dataList);
        } else {
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            } else {
                setOrganizational(rlMaillistFragmentOrganizational, dataList);
            }

        }
    }

    @Override
    public void failedOrganizational(String message) {

    }

    @Override
    public void successedCommonLinkMan(List<MaillistDataJson> dataList) {

    }

    @Override
    public void failedCommonLinkMan(String message) {

    }
}
