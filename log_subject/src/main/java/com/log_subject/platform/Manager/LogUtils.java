package com.log_subject.platform.Manager;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class LogUtils extends ILog {
    private static LogUtils instance = null;

    private String address = "";//保存路径
    private static int LogViewRank = 0;//影响显示级别
    private static boolean LogSaveRank = true;//影响保存级别
    private static boolean LOG_DEBUG = true;//显示日志级别
    private static boolean LOG_NECESSITY = true;//其他保存日志
    private ILog logView;

    public static boolean isLogDebug() {
        return LOG_DEBUG;
    }

    public static LogUtils setLogDebug(boolean logDebug) {
        LOG_DEBUG = logDebug;
        return instance;
    }

    public static boolean isLogNecessity() {
        return LOG_NECESSITY;
    }

    public static void setLogNecessity(boolean logNecessity) {
        LOG_NECESSITY = logNecessity;
    }

    public String getAddress() {
        return address;
    }

    public LogUtils setAddress(String address) {
        this.address = address;
        return instance;
    }

    public int getLogViewRank() {
        return LogViewRank;
    }

    public LogUtils setLogViewRank(int logViewRank) {
        LogViewRank = logViewRank;
        return instance;
    }

    public static boolean isLogSaveRank() {
        return LogSaveRank;
    }

    public LogUtils setLogSaveRank(boolean logSaveRank) {
        LogSaveRank = logSaveRank;
        return instance;
    }

    private LogUtils() {
        if (logView == null) {
            logView = new LogView();
        }
    }

    public static LogUtils getInstance() {
        if (instance == null) {
            synchronized (LogUtils.class) {
                if (instance == null) {
                    instance = new LogUtils();
                }
            }
        }
        return instance;
    }


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};


    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public int v(String msg) {
        return logView.v(msg);
    }

    @Override
    public int v(String tag, String msg) {
        return logView.v(tag, msg);
    }

    @Override
    public int v(String tag, String msg, Throwable tr) {
        return logView.v(tag, msg, tr);
    }

    @Override
    public int d(String msg) {
        return logView.d(msg);
    }

    @Override
    public int d(String tag, String msg) {
        return logView.d(tag, msg);
    }

    @Override
    int d(String tag, String msg, Throwable tr) {
        return logView.d(tag, msg, tr);
    }

    @Override
    public int d(String tag, String format, Object... args) {
        return logView.d(tag, format, args);
    }

    @Override
    public int i(String msg) {
        return logView.i(msg);
    }

    @Override
    int i(String tag, String msg) {
        return logView.i(tag, msg);
    }

    @Override
    public int i(String tag, String msg, Throwable tr) {
        return logView.i(tag, msg, tr);
    }

    @Override
    public int w(String msg) {
        return logView.w(msg);
    }

    @Override
    public int w(String tag, String msg) {
        return logView.w(tag, msg);
    }

    @Override
    public int w(String tag, String msg, Throwable tr) {
        return logView.w(tag, msg, tr);
    }

    @Override
    public int w(Throwable tr) {
        return logView.w(tr);
    }

    @Override
    public int w(String tag, Throwable tr) {
        return logView.w(tag, tr);
    }

    @Override
    public int e(String msg) {
        return logView.e(msg);
    }

    @Override
    public int e(String tag, String msg) {
        return logView.e(tag, msg);
    }

    @Override
    public int e(String tag, String msg, Throwable tr) {
        return logView.e(tag, msg, tr);
    }

    @Override
    public int e(String tag, Throwable tr) {
        return logView.e(tag, tr);
    }

    @Override
    int user_info(String tag, Throwable tr) {
        return logView.user_info(tag, tr);
    }
}
