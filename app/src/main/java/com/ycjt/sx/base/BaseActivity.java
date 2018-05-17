package com.ycjt.sx.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.apkfuns.logutils.LogUtils;
import com.litesuits.common.utils.DialogUtil;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.ycjt.sx.R;
import com.ycjt.sx.app.SystemBarTintManager;
import com.ycjt.sx.app.YCJTApplication;
import com.ycjt.sx.utils.Utils;
import com.ycjt.sx.utils.http.nohttp.HttpListener;
import com.ycjt.sx.utils.http.nohttp.HttpResponseListener;
import com.ycjt.sx.widget.fonts.FontTextView;

import java.io.ByteArrayOutputStream;

import butterknife.ButterKnife;

/**
 * activity基类
 */
public abstract class BaseActivity extends FragmentActivity {

    /*==初始化头布局==*/
    public AppCompatImageButton ibTopLeft;
    public AppCompatImageButton ibTopRight;
    public FontTextView tvTopModdel;

    /*===权限管理===*/
    private static final int REQUEST_CODE_PERMISSION_SD = 100;
    private static final int REQUEST_CODE_PERMISSION_OTHER = 101;
    private static final int REQUEST_CODE_SETTING = 300;

    /*==Toast相关==*/
    private long showTime = 0;
    protected static Toast moToastInstance;
    protected static final int TOAST_SHORT = Toast.LENGTH_SHORT;
    protected static final int TOAST_LONG = Toast.LENGTH_LONG;


    /*==进度条相关==*/
    public Dialog mProgressDialog;
    private ProgressDialog progressDialog;

    /*=网络相关=*/
    /**
     * 请求队列。
     */
    private RequestQueue mQueue;
    /**
     * 用来标记取消。
     */
    private Object object = new Object();
    private MaterialDialog show;
    public ImageView ivFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Logger.e(getClass().getName());
        setContentView(getLayoutRes());
        YCJTApplication.addActivity(this);
        //设定状态栏的颜色，当版本大于4.4时起作用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(this, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        // 使用颜色资源
        tintManager.setStatusBarTintResource(R.drawable.shape_head_background);
        // 初始化请求队列，传入的参数是请求并发值。
        mQueue = NoHttp.newRequestQueue(1);
        initTopView();

        initSave(savedInstanceState);

    }

    @TargetApi(19)
    private static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void initSave(Bundle savedInstanceState) {
    }

    protected abstract void initView();


    /**
     * 加载一个布局
     *
     * @return
     */
    public abstract int getLayoutRes();


    /**
     * 获取头布局参数，父类获取到空间 默认全部显示，子类可以直接使用。
     */
    public void initTopView() {
        ibTopLeft = (AppCompatImageButton) findViewById(R.id.ib_top_base_left);
        ibTopRight = (AppCompatImageButton) findViewById(R.id.ib_top_base_right);
        tvTopModdel = (FontTextView) findViewById(R.id.tv_top_base_moddle);
        ivFalse = (ImageView) findViewById(R.id.iv_head_false);
        initView();
    }

    /*==============================Android权限申请和处理==============================*/


    /*==============================Android中Toast封装==============================*/

    /**
     * 显示Toast
     */
    public void showToast(String psText, int piDuration) {
        if (System.currentTimeMillis() - showTime > 3000) {
            if (moToastInstance == null)
                moToastInstance = Toast.makeText(this, psText, piDuration);
            else {
                moToastInstance.setDuration(piDuration);
                moToastInstance.setText(psText);
            }
            moToastInstance.show();
            showTime = System.currentTimeMillis();
        }
    }


    /**
     * 隐藏Toast
     */
    public void hideToast() {
        if (moToastInstance != null)
            moToastInstance.cancel();
    }

    /*=========进度条相关========*/
    public void showDialog(boolean horizontal, String title, String message) {
        if (show == null) {
            show = new MaterialDialog.Builder(this)
                    .title(title)
                    .content(message)
                    .progress(true, 0)
                    .progressIndeterminateStyle(horizontal)
                    .canceledOnTouchOutside(false)
                    .show();
        }

    }

    /**
     * 关闭ProgressDialog
     */
    public void cancleDialog() {
        if (show != null && show.isShowing()) {
            show.dismiss();
        }
    }

    /*==网络相关==*/
    public boolean CheckNetwork() {
        boolean flag = false;
        ConnectivityManager cwjManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cwjManager.getActiveNetworkInfo() != null)
            flag = cwjManager.getActiveNetworkInfo().isAvailable();
        if (flag) {
            LogUtils.v("----CheckNetwork() 检查网络------ , 有网络可用");
        } else {
            LogUtils.v("----CheckNetwork() 检查网络------ , 无网络可用");
            // showMessage("无网络可用,请检查!");
        }
        return flag;
    }


    public void showCheckNetDialog() {
        DialogUtil.dialogBuilder(this, "网络故障", "是否开启网络").setPositiveButton("设置网络",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent;
                        intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                        startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).show();
    }

    /*--网络相关--*/
    public <T> void request(int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean
            isLoading) {
        request.setCancelSign(object);
        mQueue.add(what, request, new HttpResponseListener<>(this, request, callback, canCancel, isLoading));
    }

    @Override
    protected void onDestroy() {

        // 和声明周期绑定，退出时取消这个队列中的所有请求，当然可以在你想取消的时候取消也可以，不一定和声明周期绑定。
        mQueue.cancelBySign(object);

        // 因为回调函数持有了activity，所以退出activity时请停止队列。
        mQueue.stop();

        super.onDestroy();
    }



    protected void cancelAll() {
        mQueue.cancelAll();
    }

    protected void cancelBySign(Object object) {
        mQueue.cancelBySign(object);
    }

    public Bitmap getBitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);//从字节数组解码位图
    }

    /**
     * 获取bitmap对象
     *
     * @param resId
     * @return
     */
    public Bitmap getBitmap(int resId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        return bitmap;
    }


    /**
     * 处理键盘和隐藏的方法
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (Utils.isShouldHideInput(getCurrentFocus(), ev)) {
                Utils.hideSoftInput(getApplicationContext(),
                        getCurrentFocus().getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 代码修改TextInputEditText展示样式
     *
     * @param edittext
     */
    public void updataIcon(TextInputEditText edittext) {

        Drawable leftDrawable = edittext.getCompoundDrawables()[0];
        if (leftDrawable != null) {
            leftDrawable.setBounds(0, 0, 30, 30);
            edittext.setCompoundDrawables(leftDrawable, edittext.getCompoundDrawables()[1], edittext.getCompoundDrawables()[2], edittext.getCompoundDrawables()[3]);
        }
    }


}

