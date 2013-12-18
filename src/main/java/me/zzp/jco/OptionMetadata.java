package me.zzp.jco;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Describes a single command-line option information.
 * It maintains information regarding the internal-name of the option,
 * a flag indicating if the option is must be specify,
 * a flag indicating if an argument is required for this option,
 * 
 * @author redraiment
 */
final class OptionMetadata {
    private final Field field;

    String name;
    Class<?> type;
    private final boolean multiValue; // If the target type is an Array
    boolean required;                 // If the option must be specifiy
    boolean specified;                // If the value has setted.
    boolean hasArgument;              // switch or option?

    OptionMetadata(Field field) {
        this.field = field;
        this.field.setAccessible(true);
        
        name = field.getName();
        type = field.getType();
        multiValue = type.isArray();
        if (multiValue) {
            type = type.getComponentType();
        }
        hasArgument = !Boolean.class.equals(type);
        required = field.getAnnotation(Required.class) != null;
        specified = false;
    }
    
    /**
     * Parses the string value as a target type.
     * @param value a String containing the target type representation to be parsed.
     * @return the target type represented by the argument.
     * @throws UnsupportedOptionArgumentTypeException
     * if the target type does not have a constructor with String argument.
     * @throws IllegalOptionArgumentException 
     * if the string does not contain a parsable data.
     */
    private Object cast(String value) throws UnsupportedOptionArgumentTypeException, IllegalOptionArgumentException {
        Constructor<?> m = null;
        try {
            m = type.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            throw new UnsupportedOptionArgumentTypeException(type);
        }

        try {
            return m.newInstance(value);
        } catch (Exception e) {
            throw new IllegalOptionArgumentException(value);
        }
    }

    /**
     * Sets the field represented by this Option object on the specified object
     * argument to the specified new value. The new value is automatically
     * parsed and unwrapped.
     * @param o the object whose field should be modified
     * @param value a String containing the target type representation to be parsed.
     * @throws UnsupportedOptionArgumentTypeException
     * if the target type does not have a constructor with String argument.
     * @throws IllegalOptionArgumentException
     * if the string does not contain a parsable data.
     */
    void setValue(Object o, String value) throws UnsupportedOptionArgumentTypeException, IllegalOptionArgumentException {
        Object data = cast(value);
        try {
            if (multiValue) {
                Object array = field.get(o);
                if (array == null) {
                    array = Array.newInstance(type, 0);
                }
                int len = Array.getLength(array);
                
                Object newArray = Array.newInstance(type, len + 1);
                System.arraycopy(array, 0, newArray, 0, len);
                Array.set(newArray, len, data);
                
                field.set(o, newArray);
            } else {
                field.set(o, data);
            }
        } catch (Exception e) {
            throw new IllegalOptionArgumentException(value);
        }

        specified = true;
    }
}
