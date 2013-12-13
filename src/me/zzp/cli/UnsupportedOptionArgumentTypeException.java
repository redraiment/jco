package me.zzp.cli;

public class UnsupportedOptionArgumentTypeException extends Exception {
    public UnsupportedOptionArgumentTypeException(Class<?> type) {
        super(String.format("Type %s is unsupported, need a constructor with java.lang.String", type.getName()));
    }
}
