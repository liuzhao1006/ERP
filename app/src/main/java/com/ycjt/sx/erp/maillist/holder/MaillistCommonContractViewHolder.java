package com.ycjt.sx.erp.maillist.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ycjt.sx.R;
import com.ycjt.sx.widget.fonts.FontTextView;

/**
 * Created by liuchao on 2017/6/21.
 */

public class MaillistCommonContractViewHolder extends RecyclerView.ViewHolder {


    public ImageView lv;
    public FontTextView tvMaillistName;
    public FontTextView tvMaillistPosition;
    public ImageView ivMaillistTel;

    public MaillistCommonContractViewHolder(View itemView) {
        super(itemView);

        lv = (ImageView) itemView.findViewById(R.id.iv_maillist_left_2);
        tvMaillistName = (FontTextView) itemView.findViewById(R.id.tv_maillist_name);
        tvMaillistPosition = (FontTextView) itemView.findViewById(R.id.tv_maillist_position);
        ivMaillistTel = (ImageView) itemView.findViewById(R.id.iv_maillist_tel);

    }
}
