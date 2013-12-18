package me.zzp.jco;

/**
 * Thrown to indicate that the type of #{Option} annotation indicated option
 * does not have a constructor with String.
 * @author redraiment.
 */
public class UnsupportedOptionArgumentTypeException extends Exception {
    /**
     * Constructs an UnsupportedOptionArgumentTypeException with the specified type.
     * @param type class of unsupported type.
     */
    public UnsupportedOptionArgumentTypeException(Class<?> type) {
        super(String.format("Type `%s' is unsupported, need a constructor with java.lang.String", type.getName()));
    }
}
