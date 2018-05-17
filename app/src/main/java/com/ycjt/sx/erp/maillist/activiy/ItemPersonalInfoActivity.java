package com.ycjt.sx.erp.maillist.activiy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.yanzhenjie.album.Album;
import com.ycjt.sx.R;
import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.erp.maillist.presenter.MaillistOrganizationalPresenterInfo;
import com.ycjt.sx.utils.PrefUtils;
import com.ycjt.sx.widget.fonts.FontTextView;
import com.ycjt.sx.widget.view.CircleImageView;

import java.io.Serializable;

import static com.ycjt.sx.app.InterfaceConfig.APP_IP;
import static com.ycjt.sx.app.InterfaceConfig.APP_POST;
import static com.ycjt.sx.app.InterfaceConfig.HTTP;
import static com.ycjt.sx.app.InterfaceConfig.PHOTO;
import static com.ycjt.sx.app.InterfaceConfig.QUERY_USER_INFO;
import static com.ycjt.sx.app.InterfaceConfig.URL_ADDRESS;

public class ItemPersonalInfoActivity extends BaseActivity implements IMaillistItemPersonalInfo {

    private CircleImageView ivMaillistPersonalSettingImage;
    private FontTextView tvMaillistPersonalSettingSex;
    private FontTextView tvMaillistPersonalSettingCenter;
    private FontTextView tvMaillistPersonalSettingIdentity;
    private FontTextView tvMaillistPersonalSettingTel;
    private FontTextView tvMaillistPersonalSettingLandLine;
    private FontTextView tvMaillistPersonalSettingEmail;
    private FontTextView tvMaillistPersonalSettingWeChart;
    private FontTextView tvMaillistPersonalSettingQQ;

    private static final int ACTIVITY_REQUEST_PREVIEW_PHOTO = 104;


    /**
     * ///<param name=" thisUserId ">登陆用户id</param>
     * /// <param name="userId">用户id</param>
     * /// <param name="deviceID">设备标识符</param>
     * /// <param name="OrginzeId">组织机构id</param>
     * /// <returns></returns>
     * [WebInvoke(UriTemplate = "GetUserInfo/{thisUserId}/{userId}/{deviceID}/{organizeId}", Method = "GET", ResponseFormat = WebMessageFormat.Json)]
     * BaseInfo GetUserInfo(string thisUserId,string userId, string deviceID,string OrginzeId);
     */
    //http://192.168.103.121:8011/Image/Photo/admin.jpg
    public String HOME_PAGE_DATA_URL = HTTP + APP_IP + ":" + APP_POST + PHOTO;
    private MaillistOrganizationalPresenterInfo presenterInfo;

    @Override
    protected void initView() {
        ivFalse.setVisibility(View.VISIBLE);
        ibTopLeft.setVisibility(View.VISIBLE);
        ibTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Intent intent = getIntent();
        String comInfo = intent.getStringExtra("name");

        String url = URL_ADDRESS + QUERY_USER_INFO + "/" + comInfo + "/" + PrefUtils.getString(this, GlobalConstants.PERSONAL_USERID, null) + "/" + PrefUtils.getString(this, GlobalConstants.ORG_ID, null);
        presenterInfo = new MaillistOrganizationalPresenterInfo(this);
        presenterInfo.getInfo(url);


        ivMaillistPersonalSettingImage = (CircleImageView) findViewById(R.id.iv_maillist_personal_setting_image);//图片
        tvMaillistPersonalSettingSex = (FontTextView) findViewById(R.id.tv_maillist_personal_setting_sex);//性别
        tvMaillistPersonalSettingCenter = (FontTextView) findViewById(R.id.tv_maillist_personal_setting_center);//所属中心
        tvMaillistPersonalSettingIdentity = (FontTextView) findViewById(R.id.tv_maillist_personal_setting_identity);//职位
        tvMaillistPersonalSettingTel = (FontTextView) findViewById(R.id.tv_maillist_personal_setting_tel);//电话
        tvMaillistPersonalSettingLandLine = (FontTextView) findViewById(R.id.tv_maillist_personal_setting_landline);//座机
        tvMaillistPersonalSettingEmail = (FontTextView) findViewById(R.id.tv_maillist_personal_setting_email);//邮箱
        tvMaillistPersonalSettingWeChart = (FontTextView) findViewById(R.id.tv_maillist_personal_setting_wechart);//微信
        tvMaillistPersonalSettingQQ = (FontTextView) findViewById(R.id.tv_maillist_personal_setting_qq);//QQ

        ivMaillistPersonalSettingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previewImage();
            }
        });

    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_item_personal_info;
    }

    @Override
    public void successed(String s) {
        LogUtils.i(s);
        //获取图片路径
        String jpg = PrefUtils.getString(this, GlobalConstants.PERSONAL_PHOTO, null);
        String url = HOME_PAGE_DATA_URL + jpg;

        presenterInfo.getBitInfo(url);

    }

    @Override
    public void failed(String s) {

    }

    @Override
    public void bitMap(Bitmap bitmap) {

        if (bitmap != null) {
            ivMaillistPersonalSettingImage.setImageBitmap(bitmap);
        }
    }

    @Override
    public void bitMapFailed(String s) {

    }


    /**
     * Preview image.
     */
    private void previewImage() {
        Album.gallery(this)
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryBlack)) // NavigationBar color.
                .checkFunction(true) // Does the user have an anti-selection when previewing.
                .start(ACTIVITY_REQUEST_PREVIEW_PHOTO);

    }

}
