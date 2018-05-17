package com.ycjt.sx.login.view;

import com.ycjt.sx.login.bean.JsonLogin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuchao on 2017/6/18.
 */

public interface ILogin {

    /**
     * 登录成功
     *
     * @param mList
     */
    void loginSuccessed(ArrayList<String> mList);

    /**
     * 登录失败
     *
     * @param s
     */
    void loginFailed(String s);

    void startDialog();

    void stopDialog();

    /**
     * 将个人信息传递给前台
     *
     * @param info
     */
//    void UserInfo(JsonLogin info);
}
