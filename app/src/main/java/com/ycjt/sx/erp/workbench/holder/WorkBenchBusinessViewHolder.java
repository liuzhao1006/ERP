package com.ycjt.sx.erp.workbench.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycjt.sx.R;
import com.ycjt.sx.widget.fonts.FontTextView;

/**
 * Created by liuchao on 2017/6/21.
 */

public class WorkBenchBusinessViewHolder extends RecyclerView.ViewHolder {


    public FontTextView tvWorkBenchBusiness;
    public ImageView ivShop;

    public WorkBenchBusinessViewHolder(View itemView) {
        super(itemView);

        ivShop = (ImageView) itemView.findViewById(R.id.iv_work_shop);
        tvWorkBenchBusiness = (FontTextView) itemView.findViewById(R.id.tv_work_bench_business);

    }
}
