package com.ycjt.sx.erp.agency.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;
import com.ycjt.sx.R;
import com.ycjt.sx.erp.agency.bean.AgencySearchBean;
import com.ycjt.sx.erp.agency.bean.json.AgencyJson;
import com.ycjt.sx.erp.agency.bean.json.AgencyJson.AgencyDataJson;
import com.ycjt.sx.erp.agency.holder.AgencySearchViewHolder;
import com.ycjt.sx.erp.maillist.adapter.MaillistCommonContractAdapter;
import com.ycjt.sx.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by liuchao on 2017/6/22.
 */

public class AgencySearchAdapter extends SwipeMenuAdapter<AgencySearchViewHolder> {

    private Context context;
    private List<AgencyDataJson> mList;

    public AgencySearchAdapter(Context context, List<AgencyDataJson> mList) {
        this.context = context;
        this.mList = mList;

    }


    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_agency_search, parent, false);

        return view;
    }

    @Override
    public AgencySearchViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        AgencySearchViewHolder holder = new AgencySearchViewHolder(realContentView);
        return holder;
    }

    @Override
    public void onBindViewHolder(AgencySearchViewHolder agencySearchViewHolder, int i) {
        AgencyDataJson bean = mList.get(i);
        String auditName = bean.getSenderName();
        String state = bean.getStatus();
        String node = bean.getPosition();
        String theme = bean.getTitle();
        String time = bean.getSenderTime();

        String timeTag = time.substring(6, 19);
        long l = Long.parseLong(timeTag);
        String timeStyle = Utils.time(l);

        agencySearchViewHolder.tvAgencySearchAuditName.setText(auditName);
        agencySearchViewHolder.tvAgencySearchNode.setText(node);
        agencySearchViewHolder.tvAgencySearchState.setText(state);
        agencySearchViewHolder.tvAgencySearchTheme.setText(theme);
        agencySearchViewHolder.tvAgencySearchTime.setText(timeStyle);


        //item点击事件
        if (clickListener != null) {
            agencySearchViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(agencySearchViewHolder.itemView, agencySearchViewHolder.getLayoutPosition());
                }
            });
        }

    }

    public String dateTime(long time) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return formatter.format(calendar.getTime());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private AgencySearchAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(AgencySearchAdapter.OnItemClickListener clickListener) {
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
