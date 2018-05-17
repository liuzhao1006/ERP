package com.ycjt.sx.widget.listviewadapter;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.R;
import com.ycjt.sx.erp.maillist.activiy.ItemPersonalInfoActivity;
import com.ycjt.sx.erp.personal.activity.PersonalSettingActivity;
import com.ycjt.sx.erp.personal.activity.SettingContactInfomationActivity;
import com.ycjt.sx.widget.fonts.FontTextView;
import com.ycjt.sx.widget.view.CircleImageView;

import java.util.List;

/**
 * Created by Baiguang on 2015/1/21.
 * 实现 ExpandListViewAdapter
 */
public class StaffListViewAdapter<Staff> extends ExpandListViewAdapter<Staff> {

    private ImageView iv;

    /**
     * 适配器构造函数
     *
     * @param listView           ListView控件
     * @param context            程序上下文
     * @param data               数据集合
     * @param defaultExpandLevel 默认展开层级数
     * @throws IllegalAccessException
     */
    public StaffListViewAdapter(ListView listView, Context context, List<Staff> data,
                                int defaultExpandLevel) throws IllegalAccessException {
        super(listView, context, data, defaultExpandLevel);
    }

    /**
     * 创建Listview Item 的view,
     * 要做到为不同层级的item指定不同的view，需要在该方法里判断Node层级
     *
     * @param node
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        switch (node.getLevel()) {
            case 1:
                convertView = mInflater.inflate(R.layout.item_maillist_subsidiary_parent, null);
                iv = (ImageView) convertView.findViewById(R.id.iv_maillist_subsidiary_parent_icon);
                FontTextView tv = (FontTextView) convertView.findViewById(R.id.tv_maillist_subsidiary_parent_depart);
                tv.setText(node.getName());

                if (node.isExpand()) {
                    iv.setRotation(90);
                } else {
                    iv.setRotation(0);
                }


                break;
            case 2:
                convertView = mInflater.inflate(R.layout.item_maillist_subsidiary_child, null);

                tv = (FontTextView) convertView.findViewById(R.id.tv_maillist_subsidiary_child_depart);
                FontTextView tv2 = (FontTextView) convertView.findViewById(R.id.tv_maillist_subsidiary_child_depart_position);
                FontTextView tv3 = (FontTextView) convertView.findViewById(R.id.tv_maillist_subsidiary_child_depart_leader);
                ImageView ivtel = (ImageView) convertView.findViewById(R.id.expend);

                CircleImageView circleImageView = (CircleImageView) convertView.findViewById(R.id.iv_subsidiary_child_icon);
                tv3.setVisibility(View.INVISIBLE);

                String name = node.getName();
                final String[] str = name.split("::");
                tv.setText(str[0]);
                if(str[2] == null){
                    str[2] = "";
                }
                tv2.setText(str[2]);
//                tv3.setText(str[3]);
                tv3.setText("");
                ivtel.setOnClickListener(view -> {
//                        call.call(str[1]);
                    LogUtils.i("我来打电话了" + str[1]);
                });

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LogUtils.i("我来了" + str[1]);

                        Intent i = new Intent(mContext, ItemPersonalInfoActivity.class);
                        i.putExtra("name", str[3]);
                        mContext.startActivity(i);
                    }
                });

                break;

        }
        return convertView;
    }

    public interface OnCall {

        /**
         * 停止进度条
         */
        void call(String s);
    }


    private OnCall call;

    public void setCall(OnCall call) {
        this.call = call;
    }


    protected void Oncall(String s) {
        if (call != null) {
            call.call(s);
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
                    iv.setRotation((Float) valueAnimator.getAnimatedValue());
                }
            });
            valueAnimator.start();
        }
    }


}
