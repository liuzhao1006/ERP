package com.ycjt.sx.utils;

import android.support.v4.util.LruCache;

/**
 * Created by liuchao on 2017/6/21.
 */

public class MyLruCache extends LruCache {
    public MyLruCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(Object key, Object value) {
        return super.sizeOf(key, value);
    }
}
