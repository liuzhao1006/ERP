package com.ycjt.sx.widget.fonts;

import android.content.Context;
import android.graphics.Typeface;

/**
 * 被注释的字体 已经从assets文件夹中删除,因为这个包文件比较大有110M左右.如果需要的话从外部文件导入即可
 *
 * @author 刘朝
 *         <p>
 *         2016-12-2
 */
public class FontCustom {
    public static String font = "fonts/custom_regular.ttf";
    public static Typeface tf;

    /***
     * 设置字体
     *
     * @return
     */
    public static Typeface setFont(Context context) {
        if (tf == null) {
            tf = Typeface.createFromAsset(context.getAssets(), font);
        }
        return tf;
    }
}
