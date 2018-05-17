package com.ycjt.sx.erp.workbench.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycjt.sx.R;
import com.ycjt.sx.erp.maillist.bean.CommonContractBean;
import com.ycjt.sx.erp.maillist.holder.MaillistCommonContractViewHolder;
import com.ycjt.sx.erp.workbench.holder.WorkBenchBusinessViewHolder;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by liuchao on 2017/6/21.
 */

public class WorkBenchBusinessAdapter extends RecyclerView.Adapter<WorkBenchBusinessViewHolder> {


    private List<String> mList;
    private Context context;


    public WorkBenchBusinessAdapter(Context context, List<String> mList) {
        this.mList = mList;
        this.context = context;

    }

    @Override
    public WorkBenchBusinessViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_work_bench_business, viewGroup, false);
        WorkBenchBusinessViewHolder holder = new WorkBenchBusinessViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(WorkBenchBusinessViewHolder workBenchBusinessViewHolder, int i) {

        String s = mList.get(i);

        switch (i) {
            case 0:
                workBenchBusinessViewHolder.ivShop.setBackgroundResource(R.color.bule_overlay);
                break;
            case 1:
                workBenchBusinessViewHolder.ivShop.setBackgroundResource(R.color.purple);
                break;
            case 2:
                workBenchBusinessViewHolder.ivShop.setBackgroundResource(R.color.purple);
                break;
            case 3:
                workBenchBusinessViewHolder.ivShop.setBackgroundResource(R.color.blue_dark);
                break;
            case 4:
                workBenchBusinessViewHolder.ivShop.setBackgroundResource(R.color.dark_green);
                break;
            case 5:
                workBenchBusinessViewHolder.ivShop.setBackgroundResource(R.color.purple);
                break;
            case 6:
                workBenchBusinessViewHolder.ivShop.setBackgroundResource(R.color.yellow);
                break;
            case 7:
                workBenchBusinessViewHolder.ivShop.setBackgroundResource(R.color.rose_red);
                break;
        }
        workBenchBusinessViewHolder.tvWorkBenchBusiness.setText(s);

        if (clickListener != null) {
            workBenchBusinessViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(workBenchBusinessViewHolder.itemView, workBenchBusinessViewHolder.getLayoutPosition());
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

    private WorkBenchBusinessAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(WorkBenchBusinessAdapter.OnItemClickListener clickListener) {
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
