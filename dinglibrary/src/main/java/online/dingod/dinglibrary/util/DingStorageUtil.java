package online.dingod.dinglibrary.util;

import android.os.Environment;

public class DingStorageUtil {

    public static String getPath(String shortPath) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + '/' + shortPath;
        return path;
    }
}
