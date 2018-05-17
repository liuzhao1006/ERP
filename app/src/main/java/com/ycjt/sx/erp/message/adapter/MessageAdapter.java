package com.ycjt.sx.erp.message.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.ycjt.sx.R;
import com.ycjt.sx.erp.message.bean.Messagebean;
import com.ycjt.sx.erp.message.holder.MessageHolder;
import com.ycjt.sx.inter.RecycleViewOnClickInter;

import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

import static com.ycjt.sx.utils.Utils.time;

/**
 * Created by liuchao on 2017/7/17.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageHolder> {

    private Context context;
    private List<Messagebean> mList;
    private RecycleViewOnClickInter inter;

    public MessageAdapter(Context context, List<Messagebean> mList, RecycleViewOnClickInter inter) {
        this.context = context;
        this.mList = mList;
        this.inter = inter;

    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message_fragment, parent, false);
        MessageHolder holder = new MessageHolder(view, context);
        return holder;
    }

    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {

        //TODO item赋值......
        Messagebean messagebean = mList.get(position);
        if (position == 0) {
            //公告
            holder.ivMessageImage.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.broadcast));
            holder.badge.setBadgeNumber(messagebean.getMessageCount());
        } else if (position == 1) {
            //工作提醒
            holder.ivMessageImage.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.workring));
            holder.badge.setBadgeNumber(messagebean.getMessageCount());
        } else {

            Glide.with(context)
                    .load("http://192.168.103.247:8011/Image/Photo/admin.jpg")

                    .into(holder.ivMessageImage);
            holder.ivMessageImage.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ren_icon));
            holder.badge.setBadgeNumber(messagebean.getMessageCount());
        }
        holder.tvMessageTime.setText(time(messagebean.getTime()));
        holder.tvMessageTitle.setText(messagebean.getTitle());
        holder.tvMessageContent.setText(messagebean.getContent());
        if (inter != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    inter.onItemClick(view, position);
                    LogUtils.i(messagebean.getType());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    inter.onLongItemClickListener(view, position);
                    return true;
                }
            });

            holder.ivMessageImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    inter.onClick(view);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
