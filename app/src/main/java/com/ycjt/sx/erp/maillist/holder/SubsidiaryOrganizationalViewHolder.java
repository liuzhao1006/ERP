package com.ycjt.sx.erp.maillist.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycjt.sx.R;
import com.ycjt.sx.widget.fonts.FontTextView;

/**
 * Created by liuchao on 2017/6/21.
 */

public class SubsidiaryOrganizationalViewHolder extends RecyclerView.ViewHolder {

    public ImageView itemLv;
    public FontTextView tvMaillistUnitName;

    public SubsidiaryOrganizationalViewHolder(View itemView) {
        super(itemView);

        itemLv = (ImageView) itemView.findViewById(R.id.iv_maillist_left_1);
        tvMaillistUnitName = (FontTextView) itemView.findViewById(R.id.tv_maillist_unit_name);
    }
}
