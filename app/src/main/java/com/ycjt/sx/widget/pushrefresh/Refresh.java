package com.ycjt.sx.widget.pushrefresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.ycjt.sx.inter.RecycleViewOnClickInter;

/**
 * Created by liuchao on 2017/7/17.
 */

public class Refresh implements SwipeRefreshLayout.OnRefreshListener {
    private RecycleViewOnClickInter inter;

    public Refresh(RecycleViewOnClickInter inter) {
        this.inter = inter;
    }

    @Override
    public void onRefresh() {
        if (inter != null) {
            inter.onRefresh();
        }
    }
}
