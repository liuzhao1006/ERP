package com.ycjt.sx.erp.message.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.R;
import com.ycjt.sx.erp.message.bean.Messagebean;
import com.ycjt.sx.erp.message.holder.FriendHolder;
import com.ycjt.sx.inter.RecycleViewOnClickInter;

import java.util.List;

import static com.ycjt.sx.utils.Utils.time;

/**
 * Created by liuchao on 2017/7/17.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendHolder> {

    private Context context;
    private List<Messagebean> mList;
    private RecycleViewOnClickInter inter;

    public FriendAdapter(Context context, List<Messagebean> mList, RecycleViewOnClickInter inter) {
        this.context = context;
        this.mList = mList;
        this.inter = inter;
    }

    @Override
    public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message_fragment, parent, false);
        FriendHolder holder = new FriendHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FriendHolder holder, int position) {

        //TODO item赋值......
        Messagebean messagebean = mList.get(position);
        holder.tvMessageTime.setText(time(messagebean.getTime()));
        if (messagebean.getImage().equals("0")) {
            holder.ivMessageImage.setBackgroundResource(R.drawable.ren_icon);
        }
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
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
