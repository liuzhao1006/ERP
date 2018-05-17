package com.ycjt.sx.erp.personal.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ycjt.sx.R;

/**
 * Created by liuchao on 2017/6/29.
 */

public class FeedbookViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivFeedBook;

    public FeedbookViewHolder(View itemView) {
        super(itemView);
        ivFeedBook = (ImageView) itemView.findViewById(R.id.iv_feed_book);
    }
}
