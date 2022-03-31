package online.dingod.dinglibrary.log;

public class DingThreadFormatter implements DingLogFormatter<Thread>{

    @Override
    public String format(Thread data) {
        return "Thread:" + data.getName();
    }
}
