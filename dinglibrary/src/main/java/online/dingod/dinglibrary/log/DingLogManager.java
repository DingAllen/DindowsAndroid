package online.dingod.dinglibrary.log;

import androidx.annotation.NonNull;

public class DingLogManager {

    private DingLogConfig config;
    private static DingLogManager instance;

    private DingLogManager(DingLogConfig config) {
        this.config = config;
    }

    public static DingLogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull DingLogConfig config) {
        instance = new DingLogManager(config);
    }

    public DingLogConfig getConfig() {
        return config;
    }
}
