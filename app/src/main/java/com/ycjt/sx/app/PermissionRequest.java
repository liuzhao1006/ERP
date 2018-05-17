package com.ycjt.sx.app;

import android.Manifest;
import android.content.Context;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.util.List;

/**
 * Created by liuchao on 2017/6/17.
 */

public class PermissionRequest {

    private Context context;
    private PermissionCallback callback;

    public PermissionRequest(Context context, PermissionCallback callback) {
        this.callback = callback;
        this.context = context;
    }

    public void request() {
        AndPermission.with(context)
                .requestCode(110)
                .permission(
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.READ_SMS
                )
                .callback(this)
                .start();
    }

    @PermissionYes(110)
    public void yes(List<String> permissions) {
        this.callback.onSuccessful();
    }

    @PermissionNo(110)
    public void no(List<String> permissions) {
        this.callback.onFailure();
    }

    public interface PermissionCallback {
        void onSuccessful();

        void onFailure();
    }


}
