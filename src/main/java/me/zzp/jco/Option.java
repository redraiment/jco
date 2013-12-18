package me.zzp.jco;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Describes a field as a single command-line option.
 * @author redraiment
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Option {
    
    /**
     * Short representation of the option.
     * Normally only one letter. Just ignore if not specify.
     * @return short name of the option.
     */
    String shortName() default "";
    
    /**
     * The long representation of the option.
     * Use the field name if this value not specify.
     * @return long name of the option.
     */
    String name() default "";
}
