package com.log_subject.platform.Manager;

import android.util.Log;

/**
 * 输出日志
 * Created by lixuesong on 18/2/15.
 */
public class LogView extends ILog {
    private static final String TAG = "LogView";

    protected LogView() {

    }

    public int v(String msg) {
        return v(null, msg);
    }

    public int v(String tag, String msg) {
        return println(Log.VERBOSE, tag, msg);
    }

    public int v(String tag, String msg, Throwable tr) {
        return println(Log.VERBOSE, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int d(String msg) {
        return d(null, msg);
    }

    public int d(String tag, String msg) {
        return println(Log.DEBUG, tag, msg);
    }

    public int d(String tag, String msg, Throwable tr) {
        return println(Log.DEBUG, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int d(String tag, String format, Object... args) {
        return println(Log.DEBUG, tag, String.format(format, args));
    }

    public int i(String msg) {
        return i(null, msg);
    }

    public int i(String tag, String msg) {
        return println(Log.INFO, tag, msg);
    }

    public int i(String tag, String msg, Throwable tr) {
        return println(Log.INFO, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int w(String msg) {
        return w(null, msg);
    }

    public int w(String tag, String msg) {
        return println(Log.WARN, tag, msg);
    }

    public int w(String tag, String msg, Throwable tr) {
        return println(Log.WARN, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int w(Throwable tr) {
        return w(null, getStackTraceString(tr));
    }

    public int w(String tag, Throwable tr) {
        return println(Log.WARN, tag, getStackTraceString(tr));
    }

    public int e(String msg) {
        return e(null, msg);
    }

    public int e(String tag, String msg) {
        return println(Log.ERROR, tag, msg);
    }

    public int e(String tag, String msg, Throwable tr) {
        return println(Log.ERROR, tag, msg + '\n' + getStackTraceString(tr));
    }

    public int e(String tag, Throwable tr) {
        return println(Log.ERROR, tag, getStackTraceString(tr));
    }

    @Override
        /*
         * 强制显示么？
         * */
    int user_info(String tag, Throwable tr) {
        if (LogUtils.isLogNecessity()) {
            println(Log.ERROR, tag, getStackTraceString(tr), true);
        }
        return 0;
    }


    public static String getStackTraceString(Throwable tr) {
        return Log.getStackTraceString(tr);
    }

    private static int println(int priority, String tag, String msg) {
        //  return println(priority, tag, msg, true);

        if (LogUtils.isLogSaveRank()) {
            LogSave.f(tag, msg);
        }

        if (!LogUtils.isLogDebug() && priority < Log.ERROR) {    // release环境，只显示error级别的log
            return 0;
        }

        try {
            StackTraceElement e = (new Throwable()).getStackTrace()[2];
            String fileName = e.getFileName();
            String methodName = e.getMethodName();
            int lineNumber = e.getLineNumber();
            if (fileName != null && fileName.contains(".java")) {
                fileName = fileName.replace(".java", "");
            }
            tag = tag == null ? TAG : String.format("%s_%s", TAG, tag);
            msg = String.format("[%s.%s(): %d] %s", fileName, methodName, lineNumber, msg);
            return Log.println(priority, tag, msg);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static void println(int priority, String tag, String msg, boolean isSave) {
        if (isSave) {
            LogSave.f(tag, msg);
        }
    }
}
