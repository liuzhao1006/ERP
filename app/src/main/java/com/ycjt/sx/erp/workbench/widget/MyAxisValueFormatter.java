package com.ycjt.sx.erp.workbench.widget;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;
import java.util.List;

public class MyAxisValueFormatter implements IAxisValueFormatter {

    private List<String> mList;

//    private DecimalFormat mFormat;

    public MyAxisValueFormatter(List<String> mList) {
//        mFormat = new DecimalFormat("###,###,###,##0.0");
        this.mList = mList;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mList.get((int) value % mList.size());
    }
}
