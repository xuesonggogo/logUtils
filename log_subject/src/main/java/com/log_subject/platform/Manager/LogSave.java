package com.log_subject.platform.Manager;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class LogSave {
    private static final String TAG = "_log_Save_";
    private static final boolean DEBUG = true;
    private static String fileName = "";
    private static String filepath = "";

    /**
     * 将日志打印到文件中
     */
    public static void f(String moduleName, String str) {
        if (fileName.equals("")) {
            fileName = "log_subject_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date())+".txt";//log_subject
            filepath = Environment.getExternalStorageDirectory().toString() + "/log_save";
        }

        File file = new File(filepath);
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (Exception e) {
                Log.i(TAG, "f: mkdir Exception " + e.toString());
                return;
            }

        }

        filepath = filepath + "/"+fileName;
        Log.i(TAG, "sLogFilePath=" + filepath);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault());
        String date = sDateFormat.format(new java.util.Date());
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String strLog = date + " " + makeLogDetailInfoString(moduleName, str, ste) + "\r\n";
        // Log.i(TAG,"strLog="+strLog);
        FileWriter writer;
        Log.i(TAG, "errorsave try fileName=" + fileName + ",filepath=" + filepath);
        File mFile = new File(filepath);
        if (!mFile.exists()) {
            try {
                //创建文件
                mFile.createNewFile();
            } catch (IOException e) {
                Log.i(TAG, "errorsave createNewFile=" + e.toString());
                return;
            }
        }
        try {
            writer = new FileWriter(filepath, true);
            writer.write(strLog);
            writer.flush();
            writer.close();
            Log.i(TAG, "save close");
        } catch (IOException e) {
            Log.i(TAG, "errorsave=" + e.toString());
        }
    }

    /**
     * 制作打log位置的文件名与文件行号详细信息
     *
     * @param moduleName 模块类型
     * @param str
     * @param ste
     * @return
     */
    private static String makeLogDetailInfoString(String moduleName, String str, StackTraceElement ste) {

        String strLog = "[" + moduleName + "]-" + ste.getFileName() + "(" + ste.getLineNumber() + "): ";
        strLog += str;
        return strLog;
    }


    private static int println(int priority, String tag, String msg) {
        if (!DEBUG && priority < Log.ERROR) {    // release环境，只显示error级别的log
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
}
