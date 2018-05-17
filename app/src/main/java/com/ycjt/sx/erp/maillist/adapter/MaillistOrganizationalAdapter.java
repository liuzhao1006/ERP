package com.ycjt.sx.erp.maillist.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycjt.sx.R;
import com.ycjt.sx.erp.maillist.bean.SubsidiaryBean;
import com.ycjt.sx.erp.maillist.bean.json.MaillistDataJson;
import com.ycjt.sx.erp.maillist.holder.MaillistOrganizationalViewHolder;
import com.ycjt.sx.widget.Ripple;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by liuchao on 2017/6/21.
 */

public class MaillistOrganizationalAdapter extends RecyclerView.Adapter<MaillistOrganizationalViewHolder> {
    private Context context;
    private List<MaillistDataJson> mList;
    private final Ripple ripple;

    public MaillistOrganizationalAdapter(Context context, List<MaillistDataJson> mList) {
        this.mList = mList;
        this.context = context;
        ripple = new Ripple(context);

    }


    @Override
    public MaillistOrganizationalViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_maillist_organizatiinal, viewGroup, false);
        MaillistOrganizationalViewHolder holder = new MaillistOrganizationalViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MaillistOrganizationalViewHolder maillistOrganizationalViewHolder, int i) {
        MaillistDataJson subsidiaryBean = mList.get(i);
        String name = subsidiaryBean.getName();
        maillistOrganizationalViewHolder.tvMaillistUnitName.setText(name);
        if (clickListener != null) {
            maillistOrganizationalViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(maillistOrganizationalViewHolder.itemView, maillistOrganizationalViewHolder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public byte[] getBytes(Bitmap bitmap) {
        //实例化字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);//压缩位图
        return baos.toByteArray();//创建分配字节数组
    }

    public Bitmap getBitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);//从字节数组解码位图
    }

}
