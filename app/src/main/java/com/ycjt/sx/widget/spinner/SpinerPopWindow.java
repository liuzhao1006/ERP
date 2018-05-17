package com.ycjt.sx.widget.spinner;

import java.util.List;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.ycjt.sx.R;
import com.ycjt.sx.widget.spinner.AbstractSpinerAdapter.IOnItemSelectListener;

public class SpinerPopWindow extends PopupWindow implements OnItemClickListener {

    private Context mContext;
    private ListView mListView;
    private AbstractSpinerAdapter mAdapter;
    private IOnItemSelectListener mItemSelectListener;
    private List<CustemObject> list;
    private int i;


    public SpinerPopWindow(Context context, List<CustemObject> list, int i) {
        super(context);

        mContext = context;
        this.list = list;
        this.i = i;
        init();
    }


    public void setItemListener(IOnItemSelectListener listener) {
        mItemSelectListener = listener;
    }

    public void setAdatper(AbstractSpinerAdapter adapter) {
        mAdapter = adapter;
        mListView.setAdapter(mAdapter);
    }


    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.spiner_window_layout, null);
        setContentView(view);
        if (i == 1) {
            setWidth(200);
        } else {
            setWidth(400);
        }
        if (list.size() > 5) {
            setHeight(400);
        } else {
            setHeight(600);
        }
        setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00);
        setBackgroundDrawable(dw);
        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setOnItemClickListener(this);
    }


    public <T> void refreshData(List<T> list, int selIndex) {
        if (list != null && selIndex != -1) {
            if (mAdapter != null) {
                mAdapter.refreshData(list, selIndex);
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {
        dismiss();
        if (mItemSelectListener != null) {
            mItemSelectListener.onItemClick(pos);
        }
    }


}
