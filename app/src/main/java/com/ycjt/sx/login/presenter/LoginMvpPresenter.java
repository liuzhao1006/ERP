package com.ycjt.sx.login.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.login.bean.JsonBean;
import com.ycjt.sx.login.bean.JsonLogin;
import com.ycjt.sx.login.bean.UserBean;
import com.ycjt.sx.login.model.LoginMvpStringModel;
import com.ycjt.sx.login.view.ILogin;
import com.ycjt.sx.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ycjt.sx.login.service.UrlConfig.getUrl;

/**
 * Created by liuchao on 2017/7/16.
 */

public class LoginMvpPresenter implements ILoginPresenter {

    private Context context;
    private ILogin login;
    private final ArrayList<String> mList;

    public LoginMvpPresenter(Context context, ILogin login) {
        this.context = context;
        this.login = login;
        //登录成功 准备一个集合,用来登录,一些处理
        mList = new ArrayList<>();
    }

    public boolean isEmpty(UserBean user) {
        return TextUtils.isEmpty(user.getUserName()) || TextUtils.isEmpty(user.getPassWord()) || TextUtils.isEmpty(user.getDeviceID()) || TextUtils.isEmpty(user.getOrgID()) ? true : false;
    }

    /**
     * 登录
     *
     * @param user
     */
    public void getLogin(UserBean user) {
        LoginMvpStringModel mvpModel = new LoginMvpStringModel(context, this);
        mvpModel.getLogin(getUrl(user), 0);
    }

    @Override
    public void startDialog() {
        login.startDialog();
    }

    @Override
    public void stopDialog() {
        login.stopDialog();
    }

    @Override
    public void getSuccessed(String content) {
        loginParseJson(content, mList);
        login.loginSuccessed(mList);
    }

    @Override
    public void getFailed(String message) {
        login.loginFailed(message);
    }

    private void loginParseJson(String data, ArrayList<String> mList) {
        JsonBean dataBean = JSON.parseObject(data, JsonBean.class);
        List<JsonBean.OrganizeDataBean> organizeData = dataBean.getOrganizeData();

        for (int i = 0; i < organizeData.size(); i++) {
            JsonBean.OrganizeDataBean item = organizeData.get(i);
            String chargeLeader = (String) item.getChargeLeader();
            int childsLength = item.getChildsLength();
            int depth = item.getDepth();
            String idList = item.getID();
            String leader = (String) item.getLeader();
            String name = item.getName();
            String note = (String) item.getNote();
            String number = (String) item.getNumber();
            String parentID = item.getParentID();
            int sort = item.getSort();
            int status = item.getStatus();
            int type = item.getType();
            LogUtils.i(chargeLeader + "::" + childsLength + "::" + depth + "::" + idList + "::" + leader + "::" + name + "::" + note + "::" + number + "::" + parentID + "::" + sort + "::" + status + "::" + type);
            mList.add(name + "::" + idList);

        }


        //解析EtendBean数据
        JsonBean.EtendBean etend = dataBean.getEtend();
        String createPeople = etend.getCreatePeople();
        String createTime = etend.getCreateTime();
        String deviceID = etend.getDeviceID();
        String email = etend.getEmail();
        int id = etend.getId();
        String phone = etend.getPhone();
        String photo = etend.getPhoto();
        String position = etend.getPosition();
        String qq = etend.getQQ();
        String sex = etend.getSex();
        String telephone = etend.getTelephone();
        String userId = etend.getUserId();
        String weChatNO = etend.getWeChatNO();
        //解析EtendBean数据
        JsonBean.UserBean user = dataBean.getUser();
        String account = user.getAccount();
        String id1 = user.getID();
        String name = user.getName();
        String note = user.getNote();
        String organizeID = user.getOrganizeID();
        Object organizeName = user.getOrganizeName();
        String password = user.getPassword();
        int sort = user.getSort();
        int status = user.getStatus();
        String statusText = user.getStatusText();
        //封装所需的数据到集合中
        JsonLogin info = new JsonLogin(organizeID, account, createPeople, createTime, deviceID, email, id, phone, photo, position, qq, sex, telephone, userId, weChatNO);
        PrefUtils.putString(context, GlobalConstants.PERSONAL_ACCOUNT, info.user);
        PrefUtils.putString(context, GlobalConstants.PERSONAL_CREATEPEOPLE, info.createPeople);
        PrefUtils.putString(context, GlobalConstants.PERSONAL_CREATETIME, info.createTime);
        PrefUtils.putString(context, GlobalConstants.PERSONAL_DEVICEID, info.deviceID);
        PrefUtils.putString(context, GlobalConstants.PERSONAL_EMAIL, info.email);
        PrefUtils.putInt(context, GlobalConstants.PERSONAL_ID, info.id);
        PrefUtils.putString(context, GlobalConstants.PERSONAL_PHONE, info.phone);
        PrefUtils.putString(context, GlobalConstants.PERSONAL_PHOTO, info.photo);
        PrefUtils.putString(context, GlobalConstants.PERSONAL_POSITION, info.position);
        PrefUtils.putString(context, GlobalConstants.PERSONAL_QQ, info.qq);
        PrefUtils.putString(context, GlobalConstants.PERSONAL_SEX, info.sex);
        PrefUtils.putString(context, GlobalConstants.PERSONAL_TELTPHONE, info.telephone);
        PrefUtils.putString(context, GlobalConstants.PERSONAL_USERID, info.userId);
        PrefUtils.putString(context, GlobalConstants.PERSONAL_WECHATNO, info.weChatNO);
        PrefUtils.putString(context, GlobalConstants.PERSONAL_ORG, info.orgId);
//
    }
}
