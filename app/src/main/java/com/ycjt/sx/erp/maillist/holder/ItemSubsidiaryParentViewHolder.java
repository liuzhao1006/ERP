package com.ycjt.sx.erp.maillist.holder;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ycjt.sx.R;
import com.ycjt.sx.base.mvpbase.BaseViewHolder;
import com.ycjt.sx.erp.maillist.bean.json.ItemMaillistOrgDataJson;
import com.ycjt.sx.widget.fonts.FontTextView;

/**
 * Created by liuchao on 2017/6/26.
 */

public class ItemSubsidiaryParentViewHolder extends BaseViewHolder {

    private Context context;
    private View itemView;
    private ImageView ivMaillistSubsidiaryParentExpand;
    private ImageView ivMaillistSubsidiaryParentIcon;
    private FontTextView tvMaillistsubsidiaryParentDepart;
    private RelativeLayout container;

    public ItemSubsidiaryParentViewHolder(Context context, View itemView) {
        super(itemView);

        this.context = context;
        this.itemView = itemView;
    }

    public void bindView(final ItemMaillistOrgDataJson itemSubsidiaryBean, final int pos, final ItemClickListener listener) {


        successed(pos);
        container = (RelativeLayout) itemView.findViewById(R.id.container);
        ivMaillistSubsidiaryParentExpand = (ImageView) itemView.findViewById(R.id.iv_maillist_expand);
        ivMaillistSubsidiaryParentIcon = (ImageView) itemView.findViewById(R.id.iv_maillist_subsidiary_parent_icon);
        tvMaillistsubsidiaryParentDepart = (FontTextView) itemView.findViewById(R.id.tv_maillist_subsidiary_parent_depart);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivMaillistSubsidiaryParentExpand
                .getLayoutParams();
        ivMaillistSubsidiaryParentExpand.setLayoutParams(params);
        ivMaillistSubsidiaryParentIcon.setBackgroundResource(R.drawable.ren_icon);
        tvMaillistsubsidiaryParentDepart.setText(itemSubsidiaryBean.getOrganizeName());

        if (itemSubsidiaryBean.isExpand()) {
            ivMaillistSubsidiaryParentExpand.setRotation(90);
        } else {
            ivMaillistSubsidiaryParentExpand.setRotation(0);
        }


        //父布局OnClick监听
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    if (itemSubsidiaryBean.isExpand()) {
                        listener.onHideChildren(itemSubsidiaryBean);
                        itemSubsidiaryBean.setExpand(false);
                        rotationExpandIcon(90, 0);
                    } else {
                        listener.onExpandChildren(itemSubsidiaryBean);
                        itemSubsidiaryBean.setExpand(true);
                        rotationExpandIcon(0, 90);
                    }

                }
            }
        });
    }

    public interface getPositionitem {
        int getpos(int s);
    }

    private getPositionitem item;


    public void setOnItem(getPositionitem item) {
        this.item = item;
    }


    protected void successed(int s) {
        if (item != null) {
            item.getpos(s);
        }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void rotationExpandIcon(float from, float to) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);//属性动画
            valueAnimator.setDuration(500);
            valueAnimator.setInterpolator(new DecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ivMaillistSubsidiaryParentExpand.setRotation((Float) valueAnimator.getAnimatedValue());
                }
            });
            valueAnimator.start();
        }
    }
}
