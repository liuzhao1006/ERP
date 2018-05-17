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

public class AgencySearchViewHolder extends RecyclerView.ViewHolder {

    public FontTextView tvAgencySearchTheme;
    public FontTextView tvAgencySearchAuditName;
    public FontTextView tvAgencySearchNode;
    public FontTextView tvAgencySearchState;
    public FontTextView tvAgencySearchTime;

    public AgencySearchViewHolder(View itemView) {
        super(itemView);
        tvAgencySearchTheme = (FontTextView) itemView.findViewById(R.id.tv_agency_search_theme);
        tvAgencySearchAuditName = (FontTextView) itemView.findViewById(R.id.tv_agency_search_auditname);
        tvAgencySearchNode = (FontTextView) itemView.findViewById(R.id.tv_agency_search_node);
        tvAgencySearchState = (FontTextView) itemView.findViewById(R.id.tv_agency_search_state);
        tvAgencySearchTime = (FontTextView) itemView.findViewById(R.id.tv_agency_search_time);


    }
}
