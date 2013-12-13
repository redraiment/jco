package me.zzp.cli;

public class MissingRequiredOptionException extends Exception {
    public MissingRequiredOptionException(String fieldName) {
        super(fieldName.concat(" is required"));
    }
}
