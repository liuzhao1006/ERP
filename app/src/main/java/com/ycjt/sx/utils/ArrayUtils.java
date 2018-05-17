package com.ycjt.sx.utils;

import java.util.Calendar;

/**
 * 作者 : 刘朝,
 * on 2017/8/2,
 * GitHub : https://github.com/liuzhao1006
 */

public class ArrayUtils {

    public static String[] mergeArray() {
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
    public static String[] concat(String[] a, String[] b) {
        String[] c = new String[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

}
