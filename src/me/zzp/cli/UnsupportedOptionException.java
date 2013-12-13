package me.zzp.cli;

public class UnsupportedOptionException extends Exception {
    public UnsupportedOptionException(String option) {
        super(String.format("option %s is unsupported", option));
    }
}
