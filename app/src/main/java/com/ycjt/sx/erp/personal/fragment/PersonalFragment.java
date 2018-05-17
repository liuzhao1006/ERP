package com.ycjt.sx.erp.personal.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.yanzhenjie.album.Album;
import com.ycjt.sx.R;
import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.app.YCJTApplication;
import com.ycjt.sx.base.BaseFragment;
import com.ycjt.sx.erp.personal.activity.AboutActivity;
import com.ycjt.sx.erp.personal.activity.FeedBackActivity;
import com.ycjt.sx.erp.personal.activity.NewInfoNotificationActivity;
import com.ycjt.sx.erp.personal.activity.PersonalSettingActivity;
import com.ycjt.sx.erp.personal.activity.UpDataPwdActivity;
import com.ycjt.sx.erp.personal.bean.PersonalBean;
import com.ycjt.sx.erp.personal.presenter.PersonalMvpPresenter;
import com.ycjt.sx.login.bean.UserBean;
import com.ycjt.sx.login.presenter.LoginMvpPresenter;
import com.ycjt.sx.login.view.ILogin;
import com.ycjt.sx.login.view.LoginActivity;
import com.ycjt.sx.login.view.OrganizationActivity;
import com.ycjt.sx.utils.PrefUtils;
import com.ycjt.sx.widget.animal.AnimationUtils;
import com.ycjt.sx.widget.fonts.FontTextView;
import com.ycjt.sx.widget.view.CircleImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.yanzhenjie.fragment.NoFragment.RESULT_OK;
import static com.ycjt.sx.utils.ImageViewUtils.getSmallBitmap;

/**
 * Created by liuchao on 2017/6/19.
 */

public class PersonalFragment extends BaseFragment implements View.OnClickListener, IPersonal, ILogin {

    private RelativeLayout rlPersonalUpdate;//修改密码
    private RelativeLayout rlPersonalNotification;//通知
    private RelativeLayout rlPersonalHelp;//帮助中心
    private RelativeLayout rlPersonalCommon;//通用
    private RelativeLayout rlPersonalFeedBack;//意见反馈
    private RelativeLayout rlPersonalAbout;//关于
    private Button btnPersonalLogOff;//退出登录
    private CircleImageView ivPersonalIcon;
    private FontTextView tvPersonalName;
    private FontTextView tvPersonalTel;
    private FontTextView tvPersonalUnit;
    private RelativeLayout rlPersonalInfo;//个人设置
    private RelativeLayout rlPersonalOrganizational;


    // 
    private byte[] bytes;

    private static final int ACTIVITY_REQUEST_PREVIEW_PHOTO = 104;

    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 105;

    @Override
    public void initViewSaved(View mRootView, Bundle savedInstanceState) {


        //权限判断
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请READ_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
        //获取图片
        PersonalMvpPresenter mvpPresenter = new PersonalMvpPresenter(mActivity, this);
        mvpPresenter.getBitInfo(new PersonalBean(mActivity, PrefUtils.getString(mActivity, GlobalConstants.PERSONAL_PHOTO, null)));


        rlPersonalInfo = (RelativeLayout) mRootView.findViewById(R.id.rv_personal_info);
        ivPersonalIcon = (CircleImageView) mRootView.findViewById(R.id.iv_personal_icon);
        tvPersonalName = (FontTextView) mRootView.findViewById(R.id.tv_personal_name);
        tvPersonalTel = (FontTextView) mRootView.findViewById(R.id.tv_personal_tel);
        tvPersonalUnit = (FontTextView) mRootView.findViewById(R.id.tv_personal_unit);

        tvPersonalName.setText(PrefUtils.getString(mActivity, GlobalConstants.PERSONAL_ACCOUNT, null));
        tvPersonalUnit.setText(PrefUtils.getString(mActivity, GlobalConstants.PERSONAL_POSITION, null));
        tvPersonalTel.setText(PrefUtils.getString(mActivity, GlobalConstants.PERSONAL_TEL, null));

        /*======================设置====================*/
        rlPersonalUpdate = (RelativeLayout) mRootView.findViewById(R.id.rl_personal_updata);
        rlPersonalNotification = (RelativeLayout) mRootView.findViewById(R.id.rl_personal_notification);
        rlPersonalHelp = (RelativeLayout) mRootView.findViewById(R.id.rl_personal_help);
        rlPersonalCommon = (RelativeLayout) mRootView.findViewById(R.id.rl_personal_common);
        rlPersonalFeedBack = (RelativeLayout) mRootView.findViewById(R.id.rl_personal_feedback);
        rlPersonalAbout = (RelativeLayout) mRootView.findViewById(R.id.rl_personal_about);
        rlPersonalOrganizational = (RelativeLayout) mRootView.findViewById(R.id.rl_personal_organizational);
        btnPersonalLogOff = (Button) mRootView.findViewById(R.id.btn_personal_log_out);

        rlPersonalUpdate.setOnClickListener(this);
        rlPersonalNotification.setOnClickListener(this);
        rlPersonalHelp.setOnClickListener(this);
        rlPersonalCommon.setOnClickListener(this);
        rlPersonalFeedBack.setOnClickListener(this);
        rlPersonalAbout.setOnClickListener(this);
        rlPersonalOrganizational.setOnClickListener(this);
        btnPersonalLogOff.setOnClickListener(this);

        //个人设置
        rlPersonalInfo.setOnClickListener(this);
        ivPersonalIcon.setOnClickListener(this);


    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rl_personal_updata://修改密码
                Intent i = new Intent(mActivity, UpDataPwdActivity.class);
                //跳转到修改密码页面
                if (bytes == null || bytes.length < 0) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ren_icon);
                    byte[] bytes2 = getBytes(bitmap);
                    i.putExtra("byte", bytes2);
                } else {
                    i.putExtra("byte", bytes);
                }
                startActivity(i);
                break;
            case R.id.rl_personal_notification://新消息通知
                startActivity(new Intent(mActivity, NewInfoNotificationActivity.class));
                break;
            case R.id.rl_personal_help://帮助中心
                break;
            case R.id.rl_personal_common://通用
                break;
            case R.id.rl_personal_feedback://意见反馈
                startActivity(new Intent(mActivity, FeedBackActivity.class));
                break;
            case R.id.rl_personal_about://关于
                startActivity(new Intent(mActivity, AboutActivity.class));
                break;
            case R.id.rv_personal_info:
                //页面跳转到个人设置
                Intent i2 = new Intent(mActivity, PersonalSettingActivity.class);
                if (bytes == null || bytes.length < 0) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ren_icon);
                    byte[] bytes2 = getBytes(bitmap);
                    i2.putExtra("byte", bytes2);
                } else {
                    i2.putExtra("byte", bytes);
                }
                startActivity(i2);
                break;
            case R.id.rl_personal_organizational:
                //跳转到选择组织架构页面
                setData();
                break;

            case R.id.btn_personal_log_out:
                //清除保存在缓存中的账号和密码
                //在清除掉内存缓存
                PrefUtils.putString(mActivity, GlobalConstants.USER_NAME, "");
                PrefUtils.putString(mActivity, GlobalConstants.PASS_WORD, "");
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                YCJTApplication.exit();
                getActivity().startActivity(intent);
                getActivity().finish();
                break;
            case R.id.iv_personal_icon:
//                previewImage();
                //弹窗显示图片
                uploadHeadImage();

                break;

        }
    }

    public byte[] getBytes(Bitmap bitmap) {
        //实例化字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);//压缩位图
        return baos.toByteArray();//创建分配字节数组
    }

    @Override
    public void successed(String s) {


    }

    @Override
    public void failed(String s) {
        // 请求失败 设置图片为默认图片  设置数据为默认数据
        showToast(s, TOAST_SHORT);

        ivPersonalIcon.setBackgroundResource(R.drawable.personal_icon_press);
//        tvPersonalName.setText(PrefUtils.getString(getActivity(), GlobalConstants.PERSONAL_NAME, null));
//        tvPersonalTel.setText(PrefUtils.getString(getActivity(), GlobalConstants.PERSONAL_TEL, null));
//        tvPersonalUnit.setText(PrefUtils.getString(getActivity(), GlobalConstants.PERSONAL_DEPARTMENT, null));

    }

    @Override
    public void loginSuccessed(ArrayList<String> mList) {

        if (mList.size() < 2) {
            showToast("您只有一个组织....", TOAST_SHORT);
            return;
        }
        if (mList.size() >= 2) {
            Intent intent2 = new Intent(getActivity(), OrganizationActivity.class);
            getActivity().startActivity(intent2);
            getActivity().finish();
        }

    }

    @Override
    public void loginFailed(String s) {
        showToast(s, TOAST_SHORT);
    }

    @Override
    public void startDialog() {
        showDialog(true, "正在查询", "请稍后...");
    }

    @Override
    public void stopDialog() {
        cancleDialog();

    }


    @Override
    public void bitMap(Bitmap bitmap) {
        if (bitmap != null) {
            bytes = getBytes(bitmap);
            ivPersonalIcon.setImageBitmap(bitmap);
        } else {
            showToast("获取图像失败..", TOAST_SHORT);
        }
    }

    /**
     * 登录逻辑
     */

    private void setData() {
        UserBean bean = new UserBean(mActivity);
        bean.setUserName(PrefUtils.getString(mActivity, GlobalConstants.USER_NAME, null));
        bean.setPassWord(PrefUtils.getString(mActivity, GlobalConstants.PASS_WORD, null));
        LoginMvpPresenter loginMvpPresenter = new LoginMvpPresenter(mActivity, this);
        if (loginMvpPresenter.isEmpty(bean)) {
            showToast("请重新登录", Toast.LENGTH_SHORT);
            return;
        }
        loginMvpPresenter.getLogin(bean);//传递的参数为登录的URL
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ACTIVITY_REQUEST_PREVIEW_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    LogUtils.i(data);
                    ivPersonalIcon.setImageBitmap(getBitmapObj(data.getDataString()));
                }
                break;
            }
        }

    }

    /**
     * Preview image.
     */
    private void previewImage() {
        Album.gallery(mActivity)
                .toolBarColor(ContextCompat.getColor(mActivity, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(mActivity, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(mActivity, R.color.colorPrimaryBlack)) // NavigationBar color.
                .checkFunction(true) // Does the user have an anti-selection when previewing.
                .currentPosition(R.id.iv_personal_icon)
                .start(ACTIVITY_REQUEST_PREVIEW_PHOTO);


    }

    public Bitmap getBitmapObj(String imgPath) {
        return getSmallBitmap(imgPath, 120, 180);
    }


    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private void uploadHeadImage() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_personal_info_image, null);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.iv_personal_info_image);
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll_personal_info_parent);

        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(mActivity).inflate(R.layout.fragment_personal, null);
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = mActivity.getWindow().getAttributes();
        params.alpha = 0.5f;
        mActivity.getWindow().setAttributes(params);
        if (bytes == null || bytes.length < 0) {
        } else {
            ivIcon.setImageBitmap(getBitmap(bytes));

        }
        ll.setOnClickListener(view1 -> {
            params.alpha = 1f;
            mActivity.getWindow().setAttributes(params);
            popupWindow.dismiss();
        });
    }

    public Bitmap getBitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);//从字节数组解码位图
    }

    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //获取图片
                PersonalMvpPresenter mvpPresenter = new PersonalMvpPresenter(mActivity, this);
                mvpPresenter.getBitInfo(new PersonalBean(mActivity, PrefUtils.getString(mActivity, GlobalConstants.PERSONAL_PHOTO, null)));

            }
        }
    }

}
