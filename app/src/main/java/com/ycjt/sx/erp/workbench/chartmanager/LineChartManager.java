package com.ycjt.sx.erp.workbench.chartmanager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import com.apkfuns.logutils.LogUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.ycjt.sx.R;
import com.ycjt.sx.erp.workbench.widget.MyAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者 : 刘朝,
 * on 2017/7/31,
 * GitHub : https://github.com/liuzhao1006
 */

public class LineChartManager {
    protected Typeface mTfRegular;
    protected Typeface mTfLight;
    private Context context;
    private BarChart mChart;
    private List<String> mList;
    private List<BarEntry> yVals1;
    private int i;

    public LineChartManager(Context context, BarChart mChart, List<String> mList, String pieTitle) {
        this.context = context;
        this.mChart = mChart;
        this.mList = mList;
        mTfRegular = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");
        //设置图标名称为空
        Description description = new Description();
        description.setText("");
        mChart.setDescription(description);
    }


    /**
     * 设置饼状图开启动画
     *
     * @param durationMillistX
     * @param durationMillistY
     */
    public void setPieAnimal(int durationMillistX, int durationMillistY) {
        mChart.highlightValues(null);
        mChart.animateXY(durationMillistX, durationMillistY);
        mChart.invalidate();
    }

    /**
     * 设置每个模块对应的标题,默认在buttom 可以设置不同文字, 也可以设置为不显示
     */

    public void setLineLegend() {
        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setWordWrapEnabled(false);
        l.setEnabled(false);
        l.setTextSize(6f);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(0f);
//        l.setYOffset(0f);
    }

    /**
     * 设置xy轴样式
     */
    public void setFormatter() {
        IAxisValueFormatter xAxisFormatter = new MyAxisValueFormatter(mList);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);//设置底线是否可显
        xAxis.setTextSize(6f);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(xAxisFormatter);
        mChart.setVisibleXRange(1, 5);
        YAxis axisRight = mChart.getAxisRight();
        axisRight.setDrawAxisLine(false);
        axisRight.setDrawGridLines(false);
        axisRight.setDrawLabels(false);
        YAxis axisLeft = mChart.getAxisLeft();
        axisLeft.setDrawAxisLine(false);
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawLabels(false);
        axisLeft.setZeroLineWidth(1);
        axisLeft.setAxisMinimum(0f);//设置柱子底部与x轴重合
        axisRight.setAxisMinimum(0f);//设置柱子底部与x轴重合
    }


    /**
     * 设置柱状图常用属性
     */
    public void setBarChartCommmon() {
        //设置高亮显示
        mChart.setHighlightFullBarEnabled(true);
        mChart.setDrawValueAboveBar(true);
        //设置支持触控
        mChart.setTouchEnabled(true);
        //设置是否支持拖拽
        mChart.setDragEnabled(true);
        //设置能否缩放
        mChart.setScaleEnabled(true);
        //设置true支持两个指头向X、Y轴的缩放，如果为false，只能支持X或者Y轴的当方向缩放
        mChart.setPinchZoom(true);
        //设置阴影
        mChart.setDrawBarShadow(false);
        //设置所有的数值在图形的上面,而不是图形上
        mChart.setDrawValueAboveBar(true);
        //设置最大的能够在图表上显示数字的图数
        mChart.setMaxVisibleValueCount(60);
    }


    /**
     * 设置数据
     *
     * @param map
     * @param allYearSaleCount
     * @param range
     */
    public void setLineData(Map<String, Map<String, Double>> map, double allYearSaleCount, int range) {

        i = 0;
        yVals1 = new ArrayList<>();
        try {

            for (String monthX : map.keySet()) {
                if (allYearSaleCount == 0.0) {
                    allYearSaleCount = 0.1;
                }
                for (String monthY : map.get(monthX).keySet()) {
                    if (monthX.equals(monthY)) {
                        Double monthYValue = map.get(monthX).get(monthY);

                        double random = Math.random();//假数据
                        double v1 = random * 100;//假数据
//                        float v = (float) ((monthYValue * range) / allYearSaleCount);
//                        yVals1.add(new BarEntry(i++, v, monthYValue ));
                        float v = (float) v1 * range / 500;
                        yVals1.add(new BarEntry(i++, v, v1));
                    }

                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        BarDataSet set1;
        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "");
            set1.setDrawIcons(false);
            set1.setColors(new int[]{Color.rgb(201, 240, 222)});
            set1.setHighLightColor(Color.rgb(98, 225, 164));
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.5f);
            data.setValueTextColor(R.color.colorPrimary);
            mChart.setData(data);
        }
        mChart.invalidate();
    }
}
