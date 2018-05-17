package com.ycjt.sx.erp.maillist.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycjt.sx.R;
import com.ycjt.sx.erp.maillist.bean.CommonContractBean;
import com.ycjt.sx.erp.maillist.bean.SubsidiaryBean;
import com.ycjt.sx.erp.maillist.bean.json.MaillistDataJson;
import com.ycjt.sx.erp.maillist.holder.MaillistCommonContractViewHolder;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by liuchao on 2017/6/21.
 */

public class MaillistCommonContractAdapter extends RecyclerView.Adapter<MaillistCommonContractViewHolder> {


    private List<MaillistDataJson> mList;
    private Context context;

    public MaillistCommonContractAdapter(Context context, List<MaillistDataJson> mList) {
        this.mList = mList;
        this.context = context;

    }

    @Override
    public MaillistCommonContractViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_maillist_common_contract, viewGroup, false);
        MaillistCommonContractViewHolder holder = new MaillistCommonContractViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MaillistCommonContractViewHolder maillistCommonContractViewHolder, int i) {

        MaillistDataJson bean = mList.get(i);
        String name = bean.getName();
        String position = bean.getID();
        String numTel = bean.getNumber();
//        Bitmap bLv = getBitmap(bean.getLv());
//        Bitmap bTel = getBitmap(bean.getTel());

//        maillistCommonContractViewHolder.lv.setImageBitmap(bLv);
        maillistCommonContractViewHolder.ivMaillistTel.setBackgroundResource(R.drawable.maillist_icon_tel);
        maillistCommonContractViewHolder.lv.setBackgroundResource(R.drawable.ren_icon);
        maillistCommonContractViewHolder.tvMaillistName.setText(name);
        maillistCommonContractViewHolder.tvMaillistPosition.setText(position);
        if (clickListener != null) {
            maillistCommonContractViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(maillistCommonContractViewHolder.itemView, maillistCommonContractViewHolder.getLayoutPosition());
                }
            });
        }

        maillistCommonContractViewHolder.ivMaillistTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialUpPage(context, numTel);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private MaillistCommonContractAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(MaillistCommonContractAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


//    /**
//     * 跳转到联系人
//     */
//
//    public void getContacts(Context context, String phoneNumber) {
//        Intent intentPhone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
//        context.startActivity(intentPhone);
//    }
//
//
//    /**
//     * 直接打电话
//     */
//    public void getPhoneing(Context context, String phoneNumber) {
//        Intent intentPhone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
//        context.startActivity(intentPhone);
//    }

    /**
     * 跳转到打电话页面
     */
    public void getDialUpPage(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
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
