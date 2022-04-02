package online.dingod.dinglibrary.util;

import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DingStorageUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);

    public static String getCurrentTime() {
        String currentTime = sdf.format(System.currentTimeMillis());
        return currentTime;
    }

    @Deprecated
    public static String getLogPath() {

        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String logPath = absolutePath + "/ding_log/" + getCurrentTime() + ".log";
        return logPath;
    }

    public static String getLogPath(String absolutePath) {

        return absolutePath + "/ding_log/" + getCurrentTime() + ".log";
    }
}
