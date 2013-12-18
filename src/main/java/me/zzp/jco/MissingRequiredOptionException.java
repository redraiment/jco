package me.zzp.jco;

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
        super(String.format("Option `%s' is required", option));
    }
}
