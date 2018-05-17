package com.ycjt.sx.db.help;

import android.content.Context;

import com.ycjt.sx.db.config.DBConfig;
import com.ycjt.sx.db.gen.DaoMaster;
import com.ycjt.sx.db.gen.DaoSession;

/**
 * Created by liuchao on 2017/7/17.
 */

public class DBHelp {
    private static DBHelp instance;
    private static Context mContext;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    /**
     * 取得DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,
                    DBConfig.DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    private DBHelp() {
    }

    public static void init(Context context) {
        mContext = context;
        instance = new DBHelp();
        // 数据库对象
        DaoSession daoSession = getDaoSession(mContext);

    }

    public static DBHelp getInstance() {
        return instance;
    }

}
