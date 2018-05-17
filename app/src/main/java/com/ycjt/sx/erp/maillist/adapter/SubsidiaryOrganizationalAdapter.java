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
import com.ycjt.sx.erp.maillist.holder.SubsidiaryOrganizationalViewHolder;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by liuchao on 2017/6/21.
 */

public class SubsidiaryOrganizationalAdapter extends RecyclerView.Adapter<SubsidiaryOrganizationalViewHolder> {


    private Context context;
    private List<SubsidiaryBean> mList;

    public SubsidiaryOrganizationalAdapter(Context context, List<SubsidiaryBean> mList) {
        this.context = context;
        this.mList = mList;

    }

    @Override
    public SubsidiaryOrganizationalViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_maillist_organizatiinal, viewGroup, false);
        SubsidiaryOrganizationalViewHolder holder = new SubsidiaryOrganizationalViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SubsidiaryOrganizationalViewHolder subsidiaryOrganizationalViewHolder, int i) {
        SubsidiaryBean subsidiaryBean = mList.get(i);
        byte[] lv = subsidiaryBean.getLv();
        String name = subsidiaryBean.getName();

        Bitmap bLv = getBitmap(lv);
        subsidiaryOrganizationalViewHolder.tvMaillistUnitName.setText(name);
        subsidiaryOrganizationalViewHolder.itemLv.setImageBitmap(bLv);

        if (clickListener != null) {
            subsidiaryOrganizationalViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(subsidiaryOrganizationalViewHolder.itemView, subsidiaryOrganizationalViewHolder.getLayoutPosition());
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

    private SubsidiaryOrganizationalAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(SubsidiaryOrganizationalAdapter.OnItemClickListener clickListener) {
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
