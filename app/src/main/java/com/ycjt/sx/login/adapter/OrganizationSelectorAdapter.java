package com.ycjt.sx.login.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycjt.sx.R;
import com.ycjt.sx.login.holder.OrganizationViewHolder;

import java.util.List;

/**
 * Created by liuchao on 2017/6/27.
 */

public class OrganizationSelectorAdapter extends RecyclerView.Adapter<OrganizationViewHolder> {

    private Context context;
    private List<String> mList;

    public OrganizationSelectorAdapter(Context context, List<String> mList) {

        this.context = context;
        this.mList = mList;
    }

    @Override
    public OrganizationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_organization_selector, viewGroup, false);
        OrganizationViewHolder holder = new OrganizationViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrganizationViewHolder organizationViewHolder, int i) {
        String s = mList.get(i);
        organizationViewHolder.tvOrganizationSelector.setText(s);
        if (clickListener != null) {
            organizationViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(organizationViewHolder.itemView, organizationViewHolder.getLayoutPosition());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OrganizationSelectorAdapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(OrganizationSelectorAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
