package com.ycjt.sx.widget.pushrefresh;

import android.support.v7.widget.RecyclerView;

import com.ycjt.sx.inter.RecycleViewOnClickInter;

/**
 * Created by liuchao on 2017/7/17.
 */

public class ScrollLoad extends RecyclerView.OnScrollListener {
    private RecycleViewOnClickInter inter;

    public ScrollLoad(RecycleViewOnClickInter inter) {
        this.inter = inter;

    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (inter != null) {
            inter.onScrolled(recyclerView, dx, dy);
        }
    }
}
