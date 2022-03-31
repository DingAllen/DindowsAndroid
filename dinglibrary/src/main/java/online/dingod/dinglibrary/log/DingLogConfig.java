package online.dingod.dinglibrary.log;

public abstract class DingLogConfig {

    public static int MAX_LEN = 512;

    public static DingStackTraceFormatter stackTraceFormatter = new DingStackTraceFormatter();
    public static DingThreadFormatter threadFormatter = new DingThreadFormatter();

    public JsonParser injectJsonParser() {
        return null;
    }

    public String getGlobalTag() {
        return "DingLog";
    }

    public boolean enable() {
        return true;
    }

    public boolean includeThread() {
        return false;
    }

    public int stackTraceDepth() {
        return 5;
    }

    public DingLogPrinter[] printers() {
        return null;
    }

    public interface JsonParser{
        String toJson(Object src);
    }
}
