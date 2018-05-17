package com.ycjt.sx.base;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.ByteArrayOutputStream;


public abstract class BaseFragment extends Fragment {

    public Activity mActivity;
    public View mRootView;
    private MaterialDialog show;
    /*==Toast相关==*/
    private long showTime = 0;
    protected static Toast moToastInstance;
    protected static final int TOAST_SHORT = Toast.LENGTH_SHORT;
    protected static final int TOAST_LONG = Toast.LENGTH_LONG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        mActivity = getActivity();
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        mRootView = inflater.inflate(getLayoutId(), container, false);
        initViewSaved(mRootView, savedInstanceState);
        return mRootView;
    }

    /**
     * 保存状态
     *
     * @param mRootView
     * @param savedInstanceState
     */
    public abstract void initViewSaved(View mRootView, Bundle savedInstanceState);

    /**
     * 初始化布局
     *
     * @return
     */
    public abstract int getLayoutId();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 显示Toast
     */
    public void showToast(String psText, int piDuration) {
        if (System.currentTimeMillis() - showTime > 1500) {
            if (moToastInstance == null)
                moToastInstance = Toast.makeText(mActivity, psText, piDuration);
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


    /**
     * 做数据处理的一个方法 子类可以重写
     */
    public void initData() {


    }

    public byte[] getBytes(Bitmap bitmap) {
        //实例化字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);//压缩位图
        return baos.toByteArray();//创建分配字节数组
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

    /*=========进度条相关========*/
    public void showDialog(boolean horizontal, String title, String message) {
        show = new MaterialDialog.Builder(getActivity())
                .title(title)
                .content(message)
                .progress(true, 0)
                .progressIndeterminateStyle(horizontal)
                .canceledOnTouchOutside(false)
                .show();
    }

    /**
     * 关闭ProgressDialog
     */
    public void cancleDialog() {
        if (show != null && show.isShowing()) show.dismiss();
    }

}
