package com.ycjt.sx.erp.workbench.chartmanager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 作者 : 刘朝,
 * on 2017/7/31,
 * GitHub : https://github.com/liuzhao1006
 */

public class PieChartManager {

    protected Typeface mTfRegular;
    protected Typeface mTfLight;

    private PieChart mChart;
    private Context context;
    private String pieTitle;
    private ArrayList<Integer> colors;

    public PieChartManager(Context context, PieChart mChart, String pieTitle) {

        this.pieTitle = pieTitle;
        this.context = context;
        this.mChart = mChart;

        colors = new ChartColorManager().setColor();

        mTfRegular = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");


        //设置饼状图名称
        Description description = new Description();
        description.setText(pieTitle);
        description.setXOffset(75f);
        mChart.setDescription(description);
    }


    /**
     * 设置中心圆形
     *
     * @param holeRadius
     * @param circleRadius
     * @param drawCenterText
     */
    public void setCircle(float holeRadius, float circleRadius, boolean drawCenterText) {
        mChart.setHoleRadius(holeRadius);
        mChart.setTransparentCircleRadius(circleRadius);
        mChart.setDrawCenterText(true);
    }

    /**
     * 通过触摸启用图表的旋转
     *
     * @param rotationEnable
     * @param hightPerTapEnable
     */
    public void setTouchRotation(boolean rotationEnable, boolean hightPerTapEnable) {
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);
    }


    /**
     * 设置每个模块对应的标题,默认在buttom 可以设置不同文字, 也可以设置为不显示
     *
     * @param drawInside
     * @param wrapEnable
     * @param enable
     */
    public void setLegend(boolean drawInside, boolean wrapEnable, boolean enable) {
        Legend l = mChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
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
     * 设置图标内容
     *
     * @param map        公司集合,每个公司总额集合
     * @param range      图标最大百分比     默认为100%
     * @param countMoney 公司总额
     */
    public void setmChart(Map<String, Double> map, int range, double countMoney) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        //设置图标和饼状图上下边距 以及 区域之间的间距
        for (Entry<String, Double> entry : map.entrySet()) {
            Double itemMoneycount = entry.getValue();
            entries.add(new PieEntry((float) (itemMoneycount / countMoney) * 100, entry.getKey()));
        }


        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setDrawIcons(false);//设置图标......?星星图标
        dataSet.setIconsOffset(new MPPointF(0, range));//设置图标偏移量,默认在区域中间
        dataSet.setSliceSpace(1f);//设置区域之间的空隙
        dataSet.setSelectionShift(10f);//设置上下边距的距离
        dataSet.setColors(colors);
        // dataSet.setSelectionShift(0f);//设置饼状图大小小于零表示饼状图绘制到空间外部了

        //设置区域百分比大小
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(6f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(mTfLight);

        mChart.setData(data);
        //设置区域内部的字体大小颜色 字体格式
        mChart.setEntryLabelColor(Color.BLACK);
        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(6f);//设置区域内文字大小
        mChart.invalidate();
    }

    /*============================================数据传递================================================*/
}
