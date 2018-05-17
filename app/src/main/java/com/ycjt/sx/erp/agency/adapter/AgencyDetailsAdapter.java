package com.ycjt.sx.erp.agency.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.R;
import com.ycjt.sx.erp.agency.bean.json.AgencyDetailsDataJson;
import com.ycjt.sx.erp.agency.holder.AgencyDetailsViewHolder;
import com.ycjt.sx.utils.Utils;

import java.util.List;

/**
 * Created by liuchao on 2017/6/27.
 */

public class AgencyDetailsAdapter extends RecyclerView.Adapter<AgencyDetailsViewHolder> {

    private Context context;
    private List<AgencyDetailsDataJson> dataJsonList;

    public AgencyDetailsAdapter(Context context, List<AgencyDetailsDataJson> dataJsonList) {
        this.context = context;
        this.dataJsonList = dataJsonList;

    }


    @Override
    public AgencyDetailsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_details, viewGroup, false);
        AgencyDetailsViewHolder holder = new AgencyDetailsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AgencyDetailsViewHolder agencyDetailsViewHolder, int i) {

        AgencyDetailsDataJson data = dataJsonList.get(i);

        if (i == dataJsonList.size() - 1) {
            agencyDetailsViewHolder.viewD.setVisibility(View.INVISIBLE);
        } else {
            agencyDetailsViewHolder.viewD.setVisibility(View.VISIBLE);
        }

        if (i == 1) {
            agencyDetailsViewHolder.viewU.setBackgroundResource(R.color.agency_detail_color);
        }

        if (i == 0) {

            agencyDetailsViewHolder.ivAgencyDetails.setBackgroundResource(R.drawable.agency_details_selector);
            agencyDetailsViewHolder.viewU.setVisibility(View.INVISIBLE);
            agencyDetailsViewHolder.viewD.setBackgroundResource(R.color.agency_detail_color);
        } else {
            agencyDetailsViewHolder.viewU.setVisibility(View.VISIBLE);
        }
        String flowID = data.getFlowID();
        String id = data.getId();
        String instanceID = data.getInstanceID();
        String receiveName = data.getReceiveName();
        String receiveTime = data.getReceiveTime();
        int status = data.getStatus();
        String stepName = data.getStepName();
        String title = data.getTitle();

        LogUtils.i(data.toString());
        String timeTag = receiveTime.substring(6, 19);
        long l = Long.parseLong(timeTag);
        String time = Utils.time(l);

        agencyDetailsViewHolder.tvDetailsStepName.setText(stepName);
        agencyDetailsViewHolder.tvDetailsReceiveTime.setText(time);
        agencyDetailsViewHolder.tvDetailsReceiveName.setText(receiveName);


        //item点击事件
        if (clickListener != null) {
            agencyDetailsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(agencyDetailsViewHolder.itemView, agencyDetailsViewHolder.getLayoutPosition());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return dataJsonList.size();
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private AgencyDetailsAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(AgencyDetailsAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
