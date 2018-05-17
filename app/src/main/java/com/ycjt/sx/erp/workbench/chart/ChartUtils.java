package com.ycjt.sx.erp.workbench.chart;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.Button;

import com.ycjt.sx.widget.spinner.CustemObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * Created by liuchao on 2017/7/21.
 * 处理图表工具类
 */

public class ChartUtils {

    private Context context;
    private String[] array2;

    public ChartUtils(Context context) {
        this.context = context;
    }

    /**
     * 获得年
     *
     * @return
     */
    public String[] yearArray() {
        String[] arr1 = new String[47];
        String[] arr2 = new String[53];
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        for (int i = 0; i < 47; i++) {
            arr1[i] = (year - (47 - i)) + "";
        }
        for (int j = 0; j < 53; j++) {
            arr2[j] = (year + j) + "";
        }
        String[] both = concat(arr1, arr2);
        return both;
    }

    /**
     * 获取年分
     *
     * @param a
     * @param b
     * @return
     */
    public String[] concat(String[] a, String[] b) {
        String[] c = new String[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }


    /**
     * 把年转换成CustemObject类型的集合
     *
     * @return
     */
    public List<CustemObject> getYear() {
        List<CustemObject> mList = new ArrayList<>();
        String[] year = yearArray();
        for (int i = 0; i < year.length; i++) {
            mList.add(new CustemObject(year[i]));
        }
        return mList;
    }

    /**
     * 把月转换成CustemObject类型的集合
     *
     * @return
     */
    public List<CustemObject> getMonth() {
        List<CustemObject> mList = new ArrayList<>();
        String[] str = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12"};
        for (int i = 0; i < str.length; i++) {
            mList.add(new CustemObject(str[i]));
        }
        return mList;
    }

    /**
     * 从所有年份中过滤出来当前年份
     *
     * @return
     */
    public String setCurrentYearButtonText() {
        List<CustemObject> allYear = getYear();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        for (CustemObject c : allYear) {
            if ((year + "").equals(c.data)) {
                return year + "";
            }
        }
        return null;
    }

    /**
     * 从所有月份中过滤出来当前月份
     *
     * @return
     */
    public String setCurrentMonthButtonText() {
        GregorianCalendar calendar = new GregorianCalendar();
        int month = calendar.get(Calendar.MONTH) + 1;

        String monthData = "";
        if (month < 10) {
            monthData = "0" + month;
        } else if (month > 9 && month < 13) {
            monthData = month + "";
        }
        List<CustemObject> allMonth = getMonth();
        for (CustemObject c : allMonth) {
            if ((monthData + "").equals(c.data)) {
                return monthData;
            }
        }
        return null;

    }

    /**
     * 设置s6动画
     *
     * @param view
     */
    public void setS6Animal(View view) {
        RotateAnimation animRotate = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);//基于自身中心点旋转360度
        animRotate.setDuration(1000);//动画时间
        animRotate.setFillAfter(true);//保持住动画结束的状态

        AnimationSet set = new AnimationSet(false);
        set.addAnimation(animRotate);
        set.setFillAfter(true);
        view.startAnimation(set);
    }

    /**
     * 获取12个月份,格式为两位数
     *
     * @param year
     * @return
     */
    public List<String> setTime(String year) {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            if (i < 9) {
                mList.add(year + "/0" + (i + 1));
            } else if (i > 8 && i < 12) {
                mList.add(year + "/" + (i + 1));
            }
        }
        return mList;
    }


    /**
     * 设置点击线形图当月的柱状图,获取此月份
     *
     * @return
     */
    public int getMonthCurrent() {
        GregorianCalendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.MONTH);
    }
}
