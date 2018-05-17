package com.ycjt.sx.login.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ycjt.sx.R;
import com.ycjt.sx.widget.fonts.FontTextView;

/**
 * Created by liuchao on 2017/6/27.
 */

public class OrganizationViewHolder extends RecyclerView.ViewHolder {

    public FontTextView tvOrganizationSelector;

    public OrganizationViewHolder(View itemView) {
        super(itemView);
        tvOrganizationSelector = (FontTextView) itemView.findViewById(R.id.tv_organization_selector);
    }
}
