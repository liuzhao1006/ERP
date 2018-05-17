package com.ycjt.sx.erp.maillist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ycjt.sx.R;
import com.ycjt.sx.erp.maillist.bean.json.MaillistDataJson;
import com.ycjt.sx.utils.DensityUtil;
import com.ycjt.sx.widget.customrecycle.adapter.BaseRecyclerAdapter;
import com.ycjt.sx.widget.fonts.FontTextView;

import java.util.List;


public class SimpleAdapter extends BaseRecyclerAdapter<SimpleAdapter.SimpleAdapterViewHolder> {
    private List<MaillistDataJson> list;
    private int largeCardHeight, smallCardHeight;

    public SimpleAdapter(List<MaillistDataJson> list, Context context) {
        this.list = list;
        largeCardHeight = DensityUtil.dip2px(context, 150);
        smallCardHeight = DensityUtil.dip2px(context, 100);
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        MaillistDataJson subsidiaryBean = list.get(position);
        String name = subsidiaryBean.getName();
        holder.tvMaillistUnitName.setText(name);
        if (clickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }

    }

    @Override
    public int getAdapterItemViewType(int position) {
        return 0;
    }

    @Override
    public int getAdapterItemCount() {
        return list.size();
    }

    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view);
    }

    public void setData(List<MaillistDataJson> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_maillist_organizatiinal, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v);
        return vh;
    }

    public void insert(MaillistDataJson person, int position) {
        insert(list, person, position);
    }

    public void remove(int position) {
        remove(list, position);
    }

    public void clear() {
        clear(list);
    }

    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemLv;
        public FontTextView tvMaillistUnitName;

        public SimpleAdapterViewHolder(View itemView) {
            super(itemView);
            itemLv = (ImageView) itemView.findViewById(R.id.iv_maillist_left_1);
            tvMaillistUnitName = (FontTextView) itemView.findViewById(R.id.tv_maillist_unit_name);
        }
    }

    public MaillistDataJson getItem(int position) {
        if (position < list.size())
            return list.get(position);
        else
            return null;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private MaillistOrganizationalAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(MaillistOrganizationalAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

}