package me.zzp.cli;

public class MissingOptionArgumentException extends Exception {
    public MissingOptionArgumentException(String fieldName, Class<?> type) {
        super(String.format("Option %s need a %s type argument", fieldName, type.getName()));
    }
}
