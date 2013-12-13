package me.zzp.cli;

public class IllegalOptionArgumentException extends Exception {
    public IllegalOptionArgumentException(String value) {
        super(String.format("Cannot convert %s to target type", value));
    }
}
