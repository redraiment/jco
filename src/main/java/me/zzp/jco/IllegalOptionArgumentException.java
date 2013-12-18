package me.zzp.jco;

/**
 * Thrown to indicate that a option has been passed an illegal or inappropriate argument.
 * @author redraiment
 */
public class IllegalOptionArgumentException extends Exception {
    /**
     * Constructs an IllegalOptionArgumentException with the specified value.
     * @param value illegal or inappropriate argument.
     */
    public IllegalOptionArgumentException(String value) {
        super(String.format("Cannot convert `%s' to target type", value));
    }
}
