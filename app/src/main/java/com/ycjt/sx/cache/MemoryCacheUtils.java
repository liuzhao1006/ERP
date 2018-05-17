package com.ycjt.sx.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * 作者 : 刘朝,
 * on 2017/8/1,
 * GitHub : https://github.com/liuzhao1006
 */

public class MemoryCacheUtils {

    private LruCache<String, Bitmap> mLruCache;

    public MemoryCacheUtils() {
        long maxMemory = Runtime.getRuntime().maxMemory();//获取虚拟机分配的最大内存,默认16MB
        System.out.println("maxMemory:" + maxMemory);
        //maxSize:内存缓存上限
        mLruCache = new LruCache<String, Bitmap>((int) (maxMemory / 8)) {
            //返回单个对象占用内存的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //计算图片占用内存大小
                int byteCount = value.getRowBytes() * value.getHeight();
                return byteCount;
            }
        };
    }

    //写缓存
    public void setMemoryCache(String url, Bitmap bitmap) {
        mLruCache.put(url+".jpg", bitmap);
    }

    //读缓存
    public Bitmap getMemroyCache(String url) {
        return mLruCache.get(url+".jpg");
    }


}
