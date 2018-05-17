package com.ycjt.sx.erp.personal.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycjt.sx.R;
import com.ycjt.sx.erp.personal.holder.FeedbookViewHolder;
import com.ycjt.sx.utils.ImageDispose;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuchao on 2017/6/29.
 */

public class FeedBookImageAdapter extends RecyclerView.Adapter<FeedbookViewHolder> {

    private List<BitmapDrawable> list;
    private Context context;

    public FeedBookImageAdapter(Context context, List<BitmapDrawable> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public FeedbookViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed_book_image, viewGroup, false);
        FeedbookViewHolder holder = new FeedbookViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FeedbookViewHolder feedbookViewHolder, int i) {
        BitmapDrawable bitmap = list.get(i);
//        feedbookViewHolder.ivFeedBook.setImageBitmap(bitmap);

        if (clickListener != null) {
            feedbookViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(feedbookViewHolder.itemView, feedbookViewHolder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private FeedBookImageAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(FeedBookImageAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
