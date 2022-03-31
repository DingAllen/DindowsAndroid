package online.dingod.dinglibrary.log;

import androidx.annotation.NonNull;

public interface DingLogPrinter {

    void print(@NonNull DingLogConfig config, int level, String tag, @NonNull String printString);
}
