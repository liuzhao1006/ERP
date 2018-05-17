package com.ycjt.sx.erp.message.holder;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ycjt.sx.R;
import com.ycjt.sx.widget.fonts.FontTextView;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by liuchao on 2017/7/17.
 */

public class MessageHolder extends ViewHolder {

    public ImageView ivMessageImage;
    public FontTextView tvMessageTitle;
    public FontTextView tvMessageContent;
    public FontTextView tvMessageTime;
    public Badge badge;
    public Context context;
    public LinearLayout ivMessageImageParent;

    public MessageHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;

        ivMessageImage = (ImageView) itemView.findViewById(R.id.iv_message_image_item);
        tvMessageTitle = (FontTextView) itemView.findViewById(R.id.tv_message_title_item);
        tvMessageContent = (FontTextView) itemView.findViewById(R.id.tv_message_content_item);
        tvMessageTime = (FontTextView) itemView.findViewById(R.id.tv_message_time_item);
        ivMessageImageParent = (LinearLayout) itemView.findViewById(R.id.iv_message_image_parent);


        badge = new QBadgeView(context).bindTarget(itemView.findViewById(R.id.iv_message_image_parent));
        badge.setBadgeGravity(Gravity.END | Gravity.TOP);
        badge.setBadgeTextSize(12, true);
        badge.setBadgePadding(2, true);
        badge.setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
            @Override
            public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                if (dragState == STATE_SUCCEED) {
                    Toast.makeText(context, String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
