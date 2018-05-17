package com.ycjt.sx.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;

import com.apkfuns.logutils.LogUtils;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.task.LocalImageLoader;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.URLConnectionNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;
import com.ycjt.sx.db.config.DBConfig;
import com.ycjt.sx.db.gen.DaoMaster;
import com.ycjt.sx.db.gen.DaoSession;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


/**
 * Created by liuchao on 2017/6/17.
 * <p>
 * 项目中所引用的一个log日志库文件 文件网络地址为
 * https://github.com/pengwei1024/LogUtils
 */

public class YCJTApplication extends Application {

    private static Context context;
    public static List<Activity> activityList = new LinkedList<Activity>();
    public static Boolean isFristSpeechSynthesizer;
    public static boolean flag;
    public static boolean isEnough;
    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    /**
     * 屏幕高度
     */
    public static int screenHeight;
    /**
     * 屏幕密度
     */
    public static float screenDensity;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initNoHttp();
        initLog();
        initScreenSize();
        // Thread.setDefaultUncaughtExceptionHandler(new MyHandler());
    }

    /**
     * 初始化当前设备屏幕宽高
     */
    private void initScreenSize() {
        DisplayMetrics curMetrics = getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }

    /**
     * 初始化调试log
     */
    private void initLog() {
        LogUtils.d("项目启动了。");
        Logger.setDebug(true);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("NoHttpSample");// 设置NoHttp打印Log的tag。
    }

    /**
     * 配置NoHttp信息
     */
    private void initNoHttp() {

        NoHttp.initialize(context, new NoHttp.Config()
                        // 设置全局连接超时时间，单位毫秒
                        .setConnectTimeout(30 * 1000)
                        // 设置全局服务器响应超时时间，单位毫秒
                        .setReadTimeout(30 * 1000)
                        .setCacheStore(
                                // 保存到数据库
                                new DBCacheStore(context).setEnable(true) // 如果不使用缓存，设置false禁用。
                                // 或者保存到SD卡：new DiskCacheStore(this)
                        )
                        // 默认保存数据库DBCookieStore，开发者也可以自己实现CookieStore接口。
                        .setCookieStore(
                                new DBCookieStore(context).setEnable(false) // 如果不维护cookie，设置false禁用。
                        )

                        // 使用HttpURLConnection
                        .setNetworkExecutor(new URLConnectionNetworkExecutor())
                // 或者使用OkHttp
                // .setNetworkExecutor(new OkHttpNetworkExecutor())


        );
        //图片上传
        Album.initialize(new AlbumConfig.Build()
                .setImageLoader(new LocalImageLoader())
                .setLocale(Locale.getDefault())
                .build()
        );
    }

    public static Context getContext() {
        return context;
    }

    public static void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    public static boolean contains(Activity activity) {
        return activityList.contains(activity);
    }

    public static int activityListSize() {
        return activityList.size();
    }

    public static void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        activityList.clear();

    }

    class MyHandler implements UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            ex.printStackTrace();
            try {
                PrintWriter writer = new PrintWriter(Environment
                        .getExternalStorageDirectory().getAbsolutePath()
                        + "/err06.log");
                ex.printStackTrace(writer);
                writer.flush();
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            android.os.Process.killProcess(android.os.Process.myPid());
        }

    }

    /**
     * 设置greenDao
     */
//    private void setDatabase() {
//        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
//        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
//        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
//        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
//        mHelper = new DaoMaster.DevOpenHelper(this, DBConfig.DB_NAME, null);
//        db = mHelper.getWritableDatabase();
//        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
//        mDaoMaster = new DaoMaster(db);
//        mDaoSession = mDaoMaster.newSession();
//    }
//
//    public DaoSession getDaoSession() {
//        return mDaoSession;
//    }
//
//    public SQLiteDatabase getDb() {
//        return db;
//    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}





