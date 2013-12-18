package me.zzp.jco;

/**
 * Thrown to indicate that the specified option is not supported.
 * @author redraiment.
 */
public class UnsupportedOptionException extends Exception {
    /**
     * Constructs an UnsupportedOptionException with the specified option.
     * @param option name of unsupported option.
     */
    public UnsupportedOptionException(String option) {
        super(String.format("option `%s' is unsupported", option));
    }
}
