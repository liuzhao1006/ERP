package com.ycjt.sx.base.listener;

import android.graphics.Bitmap;

import com.apkfuns.logutils.LogUtils;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;
import com.ycjt.sx.base.inter.IBitmapModel;
import com.ycjt.sx.cache.LocalCacheUtils;
import com.ycjt.sx.cache.MemoryCacheUtils;

/**
 * Created by liuchao on 2017/7/17.
 */

public class BitmapListener implements OnResponseListener<Bitmap> {
    private IBitmapModel model;
    private LocalCacheUtils cacheUtils;
    private MemoryCacheUtils memoryCacheUtils;
    private String imageUrl;

    public BitmapListener(String url, IBitmapModel model) {
        this.model = model;

        String[] split = url.split("/");
        imageUrl = split[split.length - 1];

        cacheUtils = new LocalCacheUtils();
        memoryCacheUtils = new MemoryCacheUtils();
    }

    @Override
    public void onStart(int what) {
        model.startDialogBit();
    }

    @Override
    public void onSucceed(int what, Response<Bitmap> response) {
        if (response.responseCode() == 200) {
            model.successedBit(response.get());
            cacheUtils.setLocalCache(imageUrl, response.get());//写缓存
            memoryCacheUtils.setMemoryCache(imageUrl, response.get());//写缓存
        } else if (response.responseCode() == 404) {
            LogUtils.i("404了");
            model.failedBit("服务器出错, 请稍后进入,或者联系管理员......");

        } else if (response.responseCode() == 304) {
            //访问网络路径发生改变,比如response.responseCode() == 400
            Bitmap bitmap = memoryCacheUtils.getMemroyCache(imageUrl);
            if (bitmap != null) {
                model.successedBit(bitmap);
                LogUtils.i("我是内存缓存");
                return;
            }
            bitmap = cacheUtils.getLocalCache(imageUrl);
            if (bitmap != null) {
                model.successedBit(bitmap);
                LogUtils.i("我是本地缓存");
                return;
            }
            model.failedBit("无缓存数据");

        } else {
            model.failedBit("我也不知道怎么了,反正就是找不到您要的资源......");
        }
    }

    @Override
    public void onFailed(int what, Response<Bitmap> response) {
        LogUtils.i(response.getException().getMessage());
        try {
            if (response.get() == null) {
                model.failedBit("服务器错误,稍后再试");
            }
            model.failedBit(response.get().toString());
        } catch (Exception e) {
            e.printStackTrace();
            model.failedBit("服务器错误,稍后再试");
        }
    }

    @Override
    public void onFinish(int what) {
        model.stopDialogBit();
    }
}
