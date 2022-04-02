package online.dingod.dinglibrary.log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import online.dingod.dinglibrary.util.DingStorageUtil;

public class DingFilePrinter implements DingLogPrinter {

    private String path;
    private File file;
    private FileOutputStream fileOutputStream;

    public DingFilePrinter(String absolutePath) {
        path = DingStorageUtil.getLogPath(absolutePath);
        file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileOutputStream = new FileOutputStream(path, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(String content) {
        try {
            fileOutputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void print(@NonNull DingLogConfig config, int level, String tag, @NonNull String printString) {
        DingLogMo logMo = new DingLogMo(System.currentTimeMillis(), level, tag, printString);
        writeFile("日志级别：" + level + "\n");
        writeFile(logMo.flattenedLog());
        writeFile("\n");
        writeFile("\n");
    }
}
