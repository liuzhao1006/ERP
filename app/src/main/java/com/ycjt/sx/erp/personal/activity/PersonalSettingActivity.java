package com.ycjt.sx.erp.personal.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.apkfuns.logutils.LogUtils;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.durban.Durban;
import com.yanzhenjie.mediascanner.MediaScanner;
import com.ycjt.sx.R;
import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.erp.personal.bean.PersonalSettingBean;
import com.ycjt.sx.erp.personal.presenter.PersonalSettingMvpPresenter;
import com.ycjt.sx.utils.PrefUtils;
import com.ycjt.sx.utils.http.FileUtil;
import com.ycjt.sx.widget.fonts.FontTextView;
import com.ycjt.sx.widget.view.CircleImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.ycjt.sx.utils.ImageViewUtils.getSmallBitmap;
import static com.ycjt.sx.utils.Utils.getBytes;

/**
 * 个人信息页面
 */
public class PersonalSettingActivity extends BaseActivity implements View.OnClickListener, IPersonalSetting {

    private static final int ACTIVITY_REQUEST_SELECT_RADIO = 100;
    private static final int ACTIVITY_REQUEST_SELECT_CROP_RADIO = 101;
    private static final int ACTIVITY_REQUEST_SELECT_PHOTO = 102;
    private static final int ACTIVITY_REQUEST_TAKE_PICTURE = 103;
    private static final int ACTIVITY_REQUEST_PREVIEW_PHOTO = 104;
    private static final int ACTIVITY_REQUEST_PREVIEW_RESULT = 105;

    private RelativeLayout rlPersonalSettingCenter;
    private RelativeLayout rlPersonalSettingTel;
    private RelativeLayout rlPersonalSettingLandLine;
    private RelativeLayout rlPersonalSettingEmail;
    private RelativeLayout rlPersonalSettingWeChart;
    private RelativeLayout rlPersonalSettingQQ;
    private FontTextView tvPersonalSettingTel;
    private FontTextView tvPersonalSettingLandLine;
    private FontTextView tvPersonalSettingEmail;
    private FontTextView tvPersonalSettingWeChart;
    private FontTextView tvPersonalSettingQQ;
    private CircleImageView ivPersonalSettingImage;

    private FontTextView tvPersonalSettingSex;
    private FontTextView tvPersonalSettingPosition;
    private FontTextView tvPersonalSettingCenter;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;

    private ArrayList<String> mImageList;
    private static Bitmap bitmap;
    private byte[] bytes;
    private PersonalSettingMvpPresenter mvpPresenter;

    @Override
    protected void initView() {
        //接收值
        Intent intent = getIntent();
        bytes = intent.getByteArrayExtra("byte");
        Bitmap bitmap2 = getBitmap(bytes);
        mvpPresenter = new PersonalSettingMvpPresenter(this, this);
        mImageList = new ArrayList<>();
        tvTopModdel.setText("个人信息");
        ibTopLeft.setOnClickListener(view -> {
            this.finish();
        });
        ivFalse.setVisibility(View.VISIBLE);
        ibTopRight.setVisibility(View.GONE);

        /*========初始化修改个人信息控件========*/

        ivPersonalSettingImage = (CircleImageView) findViewById(R.id.iv_personal_setting_image);
        ivPersonalSettingImage.setOnClickListener(this);
        ivPersonalSettingImage.setImageBitmap(bitmap2);


        rlPersonalSettingCenter = (RelativeLayout) findViewById(R.id.rl_personal_setting_center);
        rlPersonalSettingCenter.setOnClickListener(this);
        rlPersonalSettingTel = (RelativeLayout) findViewById(R.id.rl_personal_setting_tel);
        rlPersonalSettingTel.setOnClickListener(this);
        rlPersonalSettingLandLine = (RelativeLayout) findViewById(R.id.rl_personal_setting_landline);
        rlPersonalSettingLandLine.setOnClickListener(this);
        rlPersonalSettingEmail = (RelativeLayout) findViewById(R.id.rl_personal_setting_email);
        rlPersonalSettingEmail.setOnClickListener(this);
        rlPersonalSettingWeChart = (RelativeLayout) findViewById(R.id.rl_personal_setting_wechart);
        rlPersonalSettingWeChart.setOnClickListener(this);
        rlPersonalSettingQQ = (RelativeLayout) findViewById(R.id.rl_personal_setting_qq);
        rlPersonalSettingQQ.setOnClickListener(this);

        tvPersonalSettingTel = (FontTextView) findViewById(R.id.tv_personal_setting_tel);
        tvPersonalSettingLandLine = (FontTextView) findViewById(R.id.tv_personal_setting_landline);
        tvPersonalSettingEmail = (FontTextView) findViewById(R.id.tv_personal_setting_email);
        tvPersonalSettingWeChart = (FontTextView) findViewById(R.id.tv_personal_setting_wechart);
        tvPersonalSettingQQ = (FontTextView) findViewById(R.id.tv_personal_setting_qq);
        tvPersonalSettingSex = (FontTextView) findViewById(R.id.tv_personal_setting_sex);
        tvPersonalSettingPosition = (FontTextView) findViewById(R.id.tv_personal_setting_identity);
        tvPersonalSettingCenter = (FontTextView) findViewById(R.id.tv_personal_setting_center);

        tvPersonalSettingEmail.setText(PrefUtils.getString(this, GlobalConstants.PERSONAL_EMAIL, null));
        tvPersonalSettingLandLine.setText(PrefUtils.getString(this, GlobalConstants.PERSONAL_TELTPHONE, null));
        tvPersonalSettingWeChart.setText(PrefUtils.getString(this, GlobalConstants.PERSONAL_WECHATNO, null));
        tvPersonalSettingQQ.setText(PrefUtils.getString(this, GlobalConstants.PERSONAL_QQ, null));
        tvPersonalSettingTel.setText(PrefUtils.getString(this, GlobalConstants.PERSONAL_TEL, null));
        tvPersonalSettingSex.setText(PrefUtils.getString(this, GlobalConstants.PERSONAL_SEX, null));
        tvPersonalSettingPosition.setText(PrefUtils.getString(this, GlobalConstants.PERSONAL_POSITION, null));
        tvPersonalSettingCenter.setText(PrefUtils.getString(this, GlobalConstants.PERSONAL_CENTER, null));


    }

    @Override
    public void initSave(Bundle savedInstanceState) {
        super.initSave(savedInstanceState);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_personal_setting;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rl_personal_setting_center:
                //跳转到通讯录页面
                break;
            case R.id.rl_personal_setting_tel:
            case R.id.rl_personal_setting_landline:
            case R.id.rl_personal_setting_email:
            case R.id.rl_personal_setting_wechart:
            case R.id.rl_personal_setting_qq:
                //以上几个控件,业务全部相同,
                //弹出弹窗,验证密码.通过后跳转到设置联系方式页面
                verification();
                break;
            case R.id.iv_personal_setting_image:
                uploadHeadImage();
                break;


        }
    }

    public Map getMapData(String s) {
        //获取上传图片数据
        Map<String, String> map = new HashMap<>();
        map.put("Images", s);
        map.put("deviceID", PrefUtils.getString(this, GlobalConstants.PERSONAL_DEVICEID, null));
        map.put("userId", PrefUtils.getString(this, GlobalConstants.PERSONAL_USERID, null));
        return map;
    }

    /**
     * 密码校验弹窗
     */
    private void verification() {
        new MaterialDialog.Builder(this)
                .title("修改前需验证密码")
                .content("")
                .inputType(
                        InputType.TYPE_CLASS_TEXT
                                | InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                                | InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .inputRange(1, 16)
                .negativeText("取消")
                .positiveText("确定")
                .input(
                        "请输入登录密码",
                        "",
                        false,
                        (dialog, input) -> verifySuccessed(input.toString().trim()))
                .theme(Theme.DARK)
                .show();

    }

    /**
     * 验证
     *
     * @param input
     */
    private void verifySuccessed(String input) {

        if (TextUtils.isEmpty(PrefUtils.getString(PersonalSettingActivity.this, GlobalConstants.PASS_WORD, null))) {
            showToast("您输入的密码有误,请重新输入", TOAST_SHORT);
            return;
        }

        if (PrefUtils.getString(PersonalSettingActivity.this, GlobalConstants.PASS_WORD, null).equals(input)) {
            //验证成功
            Intent ii = new Intent(this, SettingContactInfomationActivity.class);
            //跳转到修改密码页面
            if (bytes == null || bytes.length < 0) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ren_icon);
                byte[] bytes2 = getBytes(bitmap);
                ii.putExtra("byte", bytes2);
            } else {
                ii.putExtra("byte", bytes);
            }
            startActivityForResult(ii, ACTIVITY_REQUEST_PREVIEW_RESULT);
        } else {
            showToast("输入密码有误,请重新输入", TOAST_SHORT);
        }
    }

    @Override
    public void successedInfo(String s) {
        LogUtils.i("PersonalSettingActivity" + s);
    }

    @Override
    public void failedInfo(String s) {

    }

    @Override
    public void bitMap(Bitmap bitmap) {
        //获取到图片
        ivPersonalSettingImage.setImageBitmap(bitmap);
        showToast("更新图片成功", TOAST_SHORT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTIVITY_REQUEST_SELECT_RADIO: {
                if (resultCode == RESULT_OK) {
                    // Image crop: https://github.com/yanzhenjie/Durban
                    Durban.with(this)
                            .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                            .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                            .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryBlack)) // NavigationBar color.
                            .inputImagePaths(Album.parseResult(data))
                            .outputDirectory(FileUtil.getRootPath(this).getAbsolutePath())
                            .aspectRatio(1, 1)
                            .maxWidthHeight(500, 500)
                            .requestCode(ACTIVITY_REQUEST_SELECT_CROP_RADIO)
                            .start();
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                    showToast("设置失败", TOAST_SHORT);
                }
                break;
            }
            case ACTIVITY_REQUEST_SELECT_CROP_RADIO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Durban.parseResult(data); // Parse path.
                    for (String s : imageList) {
                        Log.i("TAG", s);
                    }
                    mImageList.clear();
                    new MediaScanner(this).scan(imageList); // Scan to system: https://github.com/yanzhenjie/MediaScanner
                    mImageList.addAll(imageList);



                    PersonalSettingBean bean = new PersonalSettingBean(this, mImageList.get(0));
                    mvpPresenter.upDataImage(bean);
                    Bitmap bitmap = getSmallBitmap(mImageList.get(0), 120, 180);
                    ivPersonalSettingImage.setImageBitmap(bitmap);
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                    showToast("设置失败", TOAST_SHORT);
                }
                break;
            }
            case ACTIVITY_REQUEST_SELECT_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList.clear();
                    mImageList = Album.parseResult(data); // Parse select result.
                    PersonalSettingBean bean = new PersonalSettingBean(this, mImageList.get(0));
                    mvpPresenter.upDataImage(bean);
                    Bitmap bitmap = getSmallBitmap(mImageList.get(0), 120, 180);
                    ivPersonalSettingImage.setImageBitmap(bitmap);
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                    showToast("设置失败", TOAST_SHORT);
                }
                break;
            }
            case ACTIVITY_REQUEST_TAKE_PICTURE: {
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList.clear();
                    mImageList.addAll(imageList);
                    String s = mImageList.get(0);


                    PersonalSettingBean bean = new PersonalSettingBean(this, mImageList.get(0));
                    mvpPresenter.upDataImage(bean);

                    Bitmap bitmap = getSmallBitmap(mImageList.get(0), 120, 180);
                    ivPersonalSettingImage.setImageBitmap(bitmap);
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                    showToast("设置失败", TOAST_SHORT);
                }
                break;
            }
            case ACTIVITY_REQUEST_PREVIEW_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList.clear();
                    mImageList = Album.parseResult(data); // Parse select result.
                    PersonalSettingBean bean = new PersonalSettingBean(this, mImageList.get(0));
                    mvpPresenter.upDataImage(bean);

                    Bitmap bitmap = getSmallBitmap(mImageList.get(0), 120, 180);
                    ivPersonalSettingImage.setImageBitmap(bitmap);
                }
                break;
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private void uploadHeadImage() {
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_upload_image, null);
        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });

        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(PersonalSettingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(PersonalSettingActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统相机
//                    gotoCamera();
                    fromCamera();
                }
                popupWindow.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(PersonalSettingActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(PersonalSettingActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
//                    gotoPhoto();
                    fromAlbumRadio();
                }
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(v -> popupWindow.dismiss());
    }

    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fromCamera();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fromAlbumRadio();
            }
        }
    }


    /**
     * Take a picture from fromCamera.
     */
    private void fromCamera() {
        Album.camera(this)
                .start(ACTIVITY_REQUEST_TAKE_PICTURE);
    }

    /**
     * Radio mode selection picture.
     */
    private void fromAlbumRadio() {
        Album.albumRadio(this)
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryBlack)) // NavigationBar color.
                .columnCount(2) // span count.
                .camera(true) // has fromCamera function.
                .start(ACTIVITY_REQUEST_SELECT_RADIO);
    }


    @Override
    public void startDialog() {
        showDialog(true, "更换图像", "正在更换,请稍后......");
    }

    @Override
    public void stopDialog() {
        cancleDialog();
    }
}
