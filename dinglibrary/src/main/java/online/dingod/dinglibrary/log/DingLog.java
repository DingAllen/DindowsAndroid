package online.dingod.dinglibrary.log;

import android.util.Log;

import androidx.annotation.NonNull;

public class DingLog {

    public static void v(Object... contents) {
        log(DingLogType.V, contents);
    }

    public static void tagv(String tag, Object... contents) {
        log(DingLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(DingLogType.D, contents);
    }

    public static void tagd(String tag, Object... contents) {
        log(DingLogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(DingLogType.I, contents);
    }

    public static void tagi(String tag, Object... contents) {
        log(DingLogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(DingLogType.W, contents);
    }

    public static void tagw(String tag, Object... contents) {
        log(DingLogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(DingLogType.E, contents);
    }

    public static void tage(String tag, Object... contents) {
        log(DingLogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(DingLogType.A, contents);
    }

    public static void taga(String tag, Object... contents) {
        log(DingLogType.A, tag, contents);
    }

    public static void log(@DingLogType.TYPE int type, Object... contents) {
        log(type, DingLogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    public static void log(@DingLogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(DingLogManager.getInstance().getConfig(), type, tag, contents);
    }

    public static void log(@NonNull DingLogConfig config, @DingLogType.TYPE int type, @NonNull String tag, Object... contents) {

        if (!config.enable()) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        String body = parseBody(contents);
        sb.append(body);
        Log.println(type, tag, body);
    }

    private static String parseBody(@NonNull Object[] contents) {
        StringBuilder sb = new StringBuilder();
        for (Object o : contents) {
            sb.append(o.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
