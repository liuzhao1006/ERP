package com.ycjt.sx.app;

/**
 * Created by liuchao on 2017/6/26.
 * 接口配置类
 */

public class InterfaceConfig {

    /*=============================服务器地址===================================*/

    /**
     * 请求头
     */
    public static final String HTTP = "http://";

    /**
     * APP的IP地址
     */
    public static final String APP_IP = "192.168.103.247";
    /**
     * APP端口号
     */
    public static final String APP_POST = "8011";
//http://192.168.103.247:8019/Service1.svc/WareInfo/Get/ss

    /**
     * 服务器访问地址
     * <p>
     * URL_ADDRESS = http://192.168.103.121:8011
     */

    public static final String URL_ADDRESS = HTTP + APP_IP + ":" + APP_POST;

    /*===================================登录相关=============================*/

    /**
     * 登录指定地址
     */
    public static final String APP_LOGIN_URL = "/Service1.svc/Login/";


    /**
     * 保存的orgID
     */
    public static final String APP_ORG_ID = "OrganizeData";




    /*===============================首页参数获取===============================*/

    /**
     * 首页地址
     */
    public static final String HOME_PAGE = "/Service1.svc/GetHomePage/";

    /**
     * 首页请求状态,目前分为代办, 已办, 有我发起,此状态表示返回所有类型数据
     */
    public static final int HOME_PAGE_ORDERSTATE = 0;


    /**
     * 首页请求数据,值表示默认第一次请求数据时的参数.
     */
    public static final int HOME_PAGE_CURRENT_SIZE = 0;

    /**
     * 请求数据,默认一次请求20条数据
     */
    public static final String HOME_PAGE_QUESTSIZE = "20";

    /*============流程审批功能===========*/
    public static final String HOME_PAGE_FLOW = "/Service1.svc/GetWorkFlowTaseByQuery/";


    /*============修改个人信息功能===========*/

    public static final String PERSONAL_UPLOAD_INFO = "/Service1.svc/EditUserInfo/";

    /*============组织机构功能===========*/

    public static final String MAILLIST_ORG = "/Service1.svc/GetOrganizeUser/";


    /*============图片地址===========*/
    //http://192.168.103.121:8011/Image/Photo/admin.jpg

    public static final String PHOTO = "/Image/Photo/";

    public static final String UPLOADIMAGE = URL_ADDRESS + "/Service1.svc/Upload/photo";

    /*============销售报表===========*/
    public static final String GET_SELL_BILL_SALE = "/Service1.svc/GetSellBillManage/";

    /*============采购报表===========*/
    public static final String GET_SELL_BILL_SHOP = "/Service1.svc/GetOrderHead/";

    /*============个人信息查询===========*/
    public static final String QUERY_USER_INFO = "/Service1.svc/GetUserInfo/";

    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=*/
    //http://192.168.103.121:8011/Service1.svc/
    /**
     * 登录URL
     */
    public static final String LOGIN_URL = URL_ADDRESS + "/Service1.svc/Login/";
    /**
     * 消息接收URL
     */
    public static final String MESSAGE_RECEIVER_URL = "";

    /**
     * 待办,已办,有我发起
     */
    public static final String WAIT_URL = URL_ADDRESS + "/Service1.svc/GetHomePage/";

    /**
     * 首页搜索查询
     */
    public static final String SEARCH_URL = URL_ADDRESS + "/Service1.svc/GetHomePageByQuery/";

    /**
     * 销售报表
     */
    public static final String WORKBENCH_SELL_URL = URL_ADDRESS + "/Service1.svc/GetSellBillManage/";

    /**
     * 采购报表
     */
    public static final String WORKBENCH_ORDER_URL = URL_ADDRESS + "/Service1.svc/GetOrderHead/";

    public static final String MAILLIST_ORGANIZATIONAL = URL_ADDRESS + MAILLIST_ORG;

}
