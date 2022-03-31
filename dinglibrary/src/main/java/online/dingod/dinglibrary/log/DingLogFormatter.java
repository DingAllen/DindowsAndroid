package online.dingod.dinglibrary.log;

public interface DingLogFormatter<T> {
    String format(T data);
}
