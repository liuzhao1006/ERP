package com.ycjt.sx.erp.workbench.chartmanager;

import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * 作者 : 刘朝,
 * on 2017/7/31,
 * GitHub : https://github.com/liuzhao1006
 */

public class ChartColorManager {

    private final ArrayList<Integer> colors;

    public ChartColorManager() {
        colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
    }

    public ArrayList<Integer> setColor() {
        return colors;
    }
}
