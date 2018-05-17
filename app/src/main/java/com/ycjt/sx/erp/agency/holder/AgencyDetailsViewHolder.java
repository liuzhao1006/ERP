package com.ycjt.sx.erp.agency.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycjt.sx.R;
import com.ycjt.sx.widget.fonts.FontTextView;

/**
 * Created by liuchao on 2017/6/22.
 */

public class AgencyDetailsViewHolder extends RecyclerView.ViewHolder {


    public FontTextView tvDetailsStepName;
    public FontTextView tvDetailsReceiveName;
    public FontTextView tvDetailsReceiveTime;
    public ImageView ivAgencyDetails;
    public View viewU;
    public View viewD;

    public AgencyDetailsViewHolder(View itemView) {
        super(itemView);
        ivAgencyDetails = (ImageView) itemView.findViewById(R.id.iv_agency_details_selector);
        tvDetailsStepName = (FontTextView) itemView.findViewById(R.id.tv_agency_details_stepname);
        tvDetailsReceiveName = (FontTextView) itemView.findViewById(R.id.tv_agency_details_receivename);
        tvDetailsReceiveTime = (FontTextView) itemView.findViewById(R.id.tv_agency_details_receivetime);
        viewU = itemView.findViewById(R.id.viewu);
        viewD = itemView.findViewById(R.id.viewd);


    }
}
