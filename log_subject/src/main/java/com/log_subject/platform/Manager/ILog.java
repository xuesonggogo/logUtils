package com.log_subject.platform.Manager;

public abstract class ILog {
    abstract int v(String msg);

    abstract int v(String tag, String msg);

    abstract int v(String tag, String msg, Throwable tr);

    abstract int d(String msg);

    abstract int d(String tag, String msg);

    abstract int d(String tag, String msg, Throwable tr);

    abstract int d(String tag, String format, Object... args);

    abstract int i(String msg);

    abstract int i(String tag, String msg);

    abstract int i(String tag, String msg, Throwable tr);

    abstract int w(String msg);

    abstract int w(String tag, String msg);

    abstract int w(String tag, String msg, Throwable tr);

    abstract int w(Throwable tr);

    abstract int w(String tag, Throwable tr);

    abstract int e(String msg);

    abstract int e(String tag, String msg);

    abstract int e(String tag, String msg, Throwable tr);

    abstract int e(String tag, Throwable tr);

    abstract int user_info(String tag, Throwable tr);
}
