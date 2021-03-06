package online.dingod.dinglibrary.log;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

public class DingLog {

    private static final String DING_LOG_PACKAGE;

    static {
        String className = DingLog.class.getName();
        DING_LOG_PACKAGE = className.substring(0, className.lastIndexOf('.') + 1);
    }

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

        if (config.includeThread()) {
            String threadInfo = DingLogConfig.threadFormatter.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }

        if (config.stackTraceDepth() > 0) {
            String stackTraceInfo = DingLogConfig.stackTraceFormatter.format(DingStackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace(), DING_LOG_PACKAGE, config.stackTraceDepth()));
            sb.append(stackTraceInfo).append("\n");
        }

        String body = parseBody(contents, config);
        sb.append(body);
        List<DingLogPrinter> printers = config.printers() != null ? Arrays.asList(config.printers()) : DingLogManager.getInstance().getPrinters();
        if (printers == null) {
            return;
        }
        for (DingLogPrinter printer : printers) {
            printer.print(config, type, tag, sb.toString());
        }
    }

    private static String parseBody(@NonNull Object[] contents, @NonNull DingLogConfig config) {

        if (config.injectJsonParser() != null) {
            return config.injectJsonParser().toJson(contents);
        }

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
