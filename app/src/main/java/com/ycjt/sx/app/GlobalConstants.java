package com.ycjt.sx.app;

import com.ycjt.sx.utils.PrefUtils;

import static com.ycjt.sx.app.InterfaceConfig.APP_IP;
import static com.ycjt.sx.app.InterfaceConfig.APP_LOGIN_URL;
import static com.ycjt.sx.app.InterfaceConfig.APP_POST;
import static com.ycjt.sx.app.InterfaceConfig.HOME_PAGE;
import static com.ycjt.sx.app.InterfaceConfig.HOME_PAGE_CURRENT_SIZE;
import static com.ycjt.sx.app.InterfaceConfig.HOME_PAGE_ORDERSTATE;
import static com.ycjt.sx.app.InterfaceConfig.HOME_PAGE_QUESTSIZE;
import static com.ycjt.sx.app.InterfaceConfig.HTTP;

/**
 * Created by liuchao on 2017/6/21.
 */

public class GlobalConstants {

    /**
     * 保存账户和密码
     */
    public static final String USER_NAME = "userName";
    public static final String PASS_WORD = "password";

    /**
     * 保存个人信息内容
     * JsonLogin info = new JsonLogin
     * (account, createPeople, createTime, deviceID, email,
     * id, phone, photo, position, qq,
     * sex, telephone, userId, weChatNO);
     */
    public static final String PERSONAL_ICON = "personal_icon";
    public static final String PERSONAL_NAME = "personal_name";
    public static final String PERSONAL_TEL = "personal_tel";
    public static final String PERSONAL_DEPARTMENT = "personal_department";
    public static final String PERSONAL_ACCOUNT = "account";//用户名
    public static final String PERSONAL_CREATEPEOPLE = "createPeople";//创建人
    public static final String PERSONAL_CREATETIME = "createTime";//创建时间
    public static final String PERSONAL_DEVICEID = "deviceID";//设备ID
    public static final String PERSONAL_EMAIL = "email";//邮箱
    public static final String PERSONAL_ID = "id";//编号
    public static final String PERSONAL_PHONE = "phone";//电话
    public static final String PERSONAL_PHOTO = "photo";//图像
    public static final String PERSONAL_POSITION = "position";//职位
    public static final String PERSONAL_QQ = "qq";//QQ号码
    public static final String PERSONAL_SEX = "sex";//性别
    public static final String PERSONAL_TELTPHONE = "telephone";//电话
    public static final String PERSONAL_USERID = "userId";//
    public static final String PERSONAL_WECHATNO = "weChatNO";//微信号
    public static final String PERSONAL_ORG = "OrganizeID";//组织机构
    public static final String PERSONAL_CENTER = "center";//机构
    public static final String ORG_SOME = "someOTG";//有多个机构
    /**
     * 登录路径 请求方式为GET请求,传递参数有三个或者四个,其中第四个为组织机构代码,如果需要选择就添加,默认添加为00000000-0000-0000-0000-000000000000
     */
    public static final String LOGIN_URL = HTTP + APP_IP + ":" + APP_POST + APP_LOGIN_URL;

    /**
     * 默认组织机构代码
     */
    public static final String ORG_ID = "00000000-0000-0000-0000-000000000000";

    /**
     * 登录成功,返回用户名下的所有组织机构代码用集合表示
     */
    public static final String SELECTOR_ORG = "selector_org";

    /*=============================首页请求数据默认路径==================================*/
    }
