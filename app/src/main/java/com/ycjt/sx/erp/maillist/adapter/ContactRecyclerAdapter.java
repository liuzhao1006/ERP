package com.ycjt.sx.erp.maillist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycjt.sx.R;
import com.ycjt.sx.erp.maillist.bean.json.MaillistDataJson;
import com.ycjt.sx.widget.fonts.FontTextView;

import java.util.HashMap;
import java.util.List;

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactRecyclerAdapter.SimpleAdapterViewHolder> {
    private List<HashMap<String, String>> list;
    private Context context;

    public ContactRecyclerAdapter(List<HashMap<String, String>> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position) {
        HashMap<String, String> map = list.get(position);
        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
            if ("phone".equals(key)) {
                holder.itemPhone.setText(map.get(key));
            }
            if ("name".equals(key)) {
                holder.itemName.setText(map.get(key));
            }
        }
        if (clickListener != null) {
            holder.itemView.setOnClickListener(v -> clickListener.onItemClick(holder.itemView, holder.getLayoutPosition()));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<HashMap<String, String>> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item_contact, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v);
        return vh;
    }

    public void insert(HashMap<String, String> person, int position) {
        list.add(position, person);
        notifyItemInserted(position);
    }

    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {


        public FontTextView itemName;
        public FontTextView itemPhone;

        public SimpleAdapterViewHolder(View itemView) {
            super(itemView);
            itemName = (FontTextView) itemView.findViewById(R.id.tv_name);
            itemPhone = (FontTextView) itemView.findViewById(R.id.tv_phone);
        }

    }

    public HashMap<String, String> getItem(int position) {
        if (position < list.size()) {
            return list.get(position);
        } else
            return null;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    protected OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


}