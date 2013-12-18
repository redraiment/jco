package me.zzp.jco;

/**
 * Thrown to indicate that the specified option requires an argument
 * but not specified.
 * @author redraiment.
 */
public class MissingOptionArgumentException extends Exception {
    /**
     * Constructs an MissingOptionArgumentException with the specified option.
     * @param option name of required option.
     * @param type the type of argument.
     */
    public MissingOptionArgumentException(String option, Class<?> type) {
        super(String.format("Option `%s' need a `%s' type argument", option, type.getName()));
    }
}
