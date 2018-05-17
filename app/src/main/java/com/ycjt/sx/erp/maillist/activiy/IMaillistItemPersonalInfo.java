package com.ycjt.sx.erp.maillist.activiy;

import android.graphics.Bitmap;

/**
 * Created by liuchao on 2017/7/4.
 */

public interface IMaillistItemPersonalInfo {
    void successed(String s);

    void failed(String s);

    void bitMap(Bitmap bitmap);

    void bitMapFailed(String s);
}
