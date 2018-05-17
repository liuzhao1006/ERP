package com.ycjt.sx.erp.message.fragment;

import android.app.Activity;
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
import com.ycjt.sx.erp.maillist.activiy.ContactActivity;
import com.ycjt.sx.erp.message.activity.FriendActivity;
import com.ycjt.sx.erp.message.adapter.MessageAdapter;
import com.ycjt.sx.erp.message.bean.Messagebean;
import com.ycjt.sx.inter.RecycleViewOnClickInter;
import com.ycjt.sx.widget.pushrefresh.Refresh;
import com.ycjt.sx.widget.pushrefresh.ScrollLoad;
import com.ycjt.sx.widget.recycleline.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import static com.ycjt.sx.utils.Utils.dp2px;

/**
 * Created by liuchao on 2017/6/19.
 */

public class MessageFragment extends BaseFragment implements RecycleViewOnClickInter {

    private List<Messagebean> mList;
    private SwipeMenuRecyclerView rlvMessage;
    private SwipeRefreshLayout refresh;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }


    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {
        listData();//设置假数据

        refresh = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_message_layout);
        rlvMessage = (SwipeMenuRecyclerView) mRootView.findViewById(R.id.rlv_message_fragment);
        refresh.setOnRefreshListener(new Refresh(this));
        rlvMessage.addOnScrollListener(new ScrollLoad(this));
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        rlvMessage.isItemViewSwipeEnabled();
        rlvMessage.addItemDecoration(new SpacesItemDecoration(dp2px(mActivity, 10), 1, getResources().getColor(R.color.itemLine)));//添加分割线
        rlvMessage.setLayoutManager(manager);
        rlvMessage.setAdapter(new MessageAdapter(mActivity, mList, this));


    }

    /**
     * 模拟假数据
     */
    public void listData() {
        mList = new ArrayList<>();
        //获取到未读消息数量
        int messageCount = 0;
        Messagebean messagebean1 = new Messagebean("0", "公告 ", "我是公告", 100 % 2, System.currentTimeMillis(), messageCount + 300);
        mList.add(messagebean1);

        Messagebean messagebean2 = new Messagebean("0", "工作提醒 ", "我是工作提醒", 100 % 2, System.currentTimeMillis(), messageCount + 3);
        mList.add(messagebean2);

        for (int i = 0; i < 50; i++) {
            Messagebean messagebean = new Messagebean("0", "刘朝 ~~ " + i, "我是一颗小小的石头", 100 % 2, System.currentTimeMillis(), messageCount + i);
            mList.add(messagebean);
        }
    }


    @Override
    public void onItemClick(View view, int position) {

        LogUtils.i(mList.get(position).toString());
        if (mList.get(position).getType() == 0) {
//            Intent intent = new Intent(mActivity, FriendActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("Messagebean.class", mList.get(position));
//            intent.putExtra("Messagebean", bundle);
//            mActivity.overridePendingTransition(R.anim.anim_next_out, R.anim.anim_next_in);
            Intent intent = new Intent(mActivity, ContactActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onLongItemClickListener(View view, int position) {
        LogUtils.i(mList.get(position).toString());
    }

    @Override
    public void onRefresh() {
        rlvMessage.postDelayed(() -> refresh.setRefreshing(false), 2000);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

    }

    /**
     * item图像点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        showToast("我来了,", TOAST_SHORT);
    }

}
