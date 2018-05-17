package com.ycjt.sx.utils;

import android.content.Context;
import android.graphics.Typeface;

import com.apkfuns.logutils.LogUtils;

import java.lang.reflect.Field;

/**
 * 全局字体样式统一类
 */
public class FontsOverride {

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName, Typeface newTypeface) {
        try {
            Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            e.getMessage();
            LogUtils.i(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LogUtils.i(e.getMessage());
        }
    }
}