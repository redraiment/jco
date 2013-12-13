package me.zzp.cli;

/**
 * Thrown to indicate that the specified option is required but not specified.
 * @author redraiment.
 */
public class MissingRequiredOptionException extends Exception {
    /**
     * Constructs an MissingRequiredOptionException with the specified option.
     * @param option name of required option.
     */
    public MissingRequiredOptionException(String option) {
        super(option.concat(" is required"));
    }
}
