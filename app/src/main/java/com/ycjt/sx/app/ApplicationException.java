package com.ycjt.sx.app;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by liuchao on 2017/6/17.
 */

public class ApplicationException implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /** 存储设备信息和异常信息 **/
    private Map<String, String> mInfos = new HashMap<String, String>();

    /** 程序出错提示信息 **/
    private String mDRTipMsg = "抱歉，程序异常，3s后退出！";

    /** 设置crash文件位置 **/
//    private String mDRCrashFilePath = DRConstants.CRASH_FILE_PATH;

    /** 生成的log文件 **/
    private File logFile;

    /** 生成的crash文件 **/
    private File crashFile;


    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {

    }
}
