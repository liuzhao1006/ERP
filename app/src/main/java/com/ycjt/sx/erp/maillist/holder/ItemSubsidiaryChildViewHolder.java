package com.ycjt.sx.erp.maillist.holder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.ycjt.sx.R;
import com.ycjt.sx.base.mvpbase.BaseViewHolder;
import com.ycjt.sx.erp.maillist.bean.json.ItemMaillistOrgDataJson.OUserListBean;
import com.ycjt.sx.widget.fonts.FontTextView;

/**
 * Created by liuchao on 2017/6/26.
 */

public class ItemSubsidiaryChildViewHolder extends BaseViewHolder {

    private Context context;
    public View itemView;
    private ImageView ivSubsidiaryChildTel;
    private ImageView ivSubsidiaryChildIcon;
    private FontTextView tvMaillistSubsidiaryChildName;
    private FontTextView tvMaillistSubsidiaryChildNameLeader;
    private FontTextView tvMaillistSubsidiaryChildPosition;

    public ItemSubsidiaryChildViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
    }

    public void bindView(final OUserListBean itemSubsidiaryBean, final int i) {
        ivSubsidiaryChildTel = (ImageView) itemView.findViewById(R.id.expend);
        ivSubsidiaryChildIcon = (ImageView) itemView.findViewById(R.id.iv_subsidiary_child_icon);
        tvMaillistSubsidiaryChildName = (FontTextView) itemView.findViewById(R.id.tv_maillist_subsidiary_child_depart);
        tvMaillistSubsidiaryChildNameLeader = (FontTextView) itemView.findViewById(R.id.tv_maillist_subsidiary_child_depart_leader);
        tvMaillistSubsidiaryChildPosition = (FontTextView) itemView.findViewById(R.id.tv_maillist_subsidiary_child_depart_position);

        ivSubsidiaryChildIcon.setBackgroundResource(R.drawable.ren_icon);
        ivSubsidiaryChildTel.setBackgroundResource(R.drawable.maillist_icon_tel);
        tvMaillistSubsidiaryChildName.setText(itemSubsidiaryBean.getUserName());
        tvMaillistSubsidiaryChildNameLeader.setText((Integer) itemSubsidiaryBean.getDeptPosition());
        tvMaillistSubsidiaryChildPosition.setText((String) itemSubsidiaryBean.getDeptPosition());
        ivSubsidiaryChildTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialUpPage(context, (String) itemSubsidiaryBean.getPhone());
            }
        });
    }

    /**
     * 跳转到打电话页面
     */
    public void getDialUpPage(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
