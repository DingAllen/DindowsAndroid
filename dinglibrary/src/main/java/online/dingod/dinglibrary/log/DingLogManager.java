package online.dingod.dinglibrary.log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DingLogManager {

    private DingLogConfig config;
    private static DingLogManager instance;
    private List<DingLogPrinter> printers = new ArrayList<>();

    private DingLogManager(DingLogConfig config, DingLogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static DingLogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull DingLogConfig config, DingLogPrinter... printers) {
        instance = new DingLogManager(config, printers);
    }

    public DingLogConfig getConfig() {
        return config;
    }

    public List<DingLogPrinter> getPrinters() {
        return printers;
    }

    public void addPrinter(DingLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(DingLogPrinter printer) {
        if (printers != null) {
            printers.remove(printer);
        }
    }
}
