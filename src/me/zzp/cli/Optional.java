package me.zzp.cli;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies whether the Option optional or not.
 * Use default value if the defaulValue is given and option does not specified.
 * @author redraiment
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Optional {
    /**
     * The default value if option does not specified.
     * @return default value of option.
     */
    String defaultValue() default "";
}
