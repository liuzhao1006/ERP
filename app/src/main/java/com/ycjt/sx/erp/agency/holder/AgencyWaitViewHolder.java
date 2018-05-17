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

public class AgencyWaitViewHolder extends RecyclerView.ViewHolder {

    public FontTextView tvAgencyWaitTheme;
    public FontTextView tvAgencyWaitAuditName;
    public FontTextView tvAgencyWaitNode;
    public ImageView ivAgencyWaitState;
    public FontTextView tvAgencyWaitTime;

    public AgencyWaitViewHolder(View itemView) {
        super(itemView);
        tvAgencyWaitTheme = (FontTextView) itemView.findViewById(R.id.tv_agency_wait_theme);
        tvAgencyWaitAuditName = (FontTextView) itemView.findViewById(R.id.tv_agency_wait_auditname);
        tvAgencyWaitNode = (FontTextView) itemView.findViewById(R.id.tv_agency_wait_node);
        ivAgencyWaitState = (ImageView) itemView.findViewById(R.id.tv_agency_wait_state);
        tvAgencyWaitTime = (FontTextView) itemView.findViewById(R.id.tv_agency_wait_time);


    }
}
