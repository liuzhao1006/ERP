package com.ycjt.sx.inter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liuchao on 2017/7/17.
 */

public interface RecycleViewOnClickInter {

    /**
     * 点击事件
     *
     * @param view
     * @param position
     */
    void onItemClick(View view, int position);

    /**
     * 长按点击事件
     *
     * @param view
     * @param position
     */
    void onLongItemClickListener(View view, int position);


    /**
     * 下拉刷新
     */
    void onRefresh();

    /**
     * 上划加载
     */
    void onScrolled(RecyclerView recyclerView, int dx, int dy);

    void onClick(View view);
}
