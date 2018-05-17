package com.ycjt.sx.erp.message.holder;

import android.view.View;
import android.widget.ImageView;

import com.ycjt.sx.R;
import com.ycjt.sx.widget.fonts.FontTextView;

import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by liuchao on 2017/7/17.
 */

public class FriendHolder extends ViewHolder {

    public ImageView ivMessageImage;
    public FontTextView tvMessageTitle;
    public FontTextView tvMessageContent;
    public FontTextView tvMessageTime;

    public FriendHolder(View itemView) {
        super(itemView);

        ivMessageImage = (ImageView) itemView.findViewById(R.id.iv_message_image_item);
        tvMessageTitle = (FontTextView) itemView.findViewById(R.id.tv_message_title_item);
        tvMessageContent = (FontTextView) itemView.findViewById(R.id.tv_message_content_item);
        tvMessageTime = (FontTextView) itemView.findViewById(R.id.tv_message_time_item);

    }
}
