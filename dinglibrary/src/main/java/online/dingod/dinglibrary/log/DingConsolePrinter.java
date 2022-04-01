package online.dingod.dinglibrary.log;

import static online.dingod.dinglibrary.log.DingLogConfig.MAX_LEN;

import android.util.Log;

import androidx.annotation.NonNull;

public class DingConsolePrinter implements DingLogPrinter{
    @Override
    public void print(@NonNull DingLogConfig config, int level, String tag, @NonNull String printString) {

        int len = printString.length();
        int countOfSub = len / MAX_LEN;
        if (countOfSub >= 0) {
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                Log.println(level, tag, printString.substring(index, index + MAX_LEN));
                index += MAX_LEN;
            }
            if (index != len) {
                Log.println(level, tag, printString.substring(index, len));
            }
        }
    }
}
