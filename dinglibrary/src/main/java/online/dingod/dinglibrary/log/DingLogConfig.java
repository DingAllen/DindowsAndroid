package online.dingod.dinglibrary.log;

public abstract class DingLogConfig {
    public String getGlobalTag() {
        return "DingLog";
    }

    public boolean enable() {
        return true;
    }
}
