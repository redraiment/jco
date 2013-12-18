package me.zzp.jco;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Main entry-point into the library. 
 * It may flexibly parse long and short options. Additionally, it may parse
 * multi-values as an Array to an option.
 * @author redraiment.
 */
public final class CommandLineOption {
    private final Map<String, String> optionName;
    private final Map<String, OptionMetadata> optionMetadata;
    private final Object target;
    
    /**
     * Constructs an CommandLineOption with the object  whose field should be modified.
     * @param type class of unsupported type.
     */
    private CommandLineOption(Object target) {
        this.target = target;
        optionName = new HashMap<String, String>();
        optionMetadata = new HashMap<String, OptionMetadata>();

        parseFields();
    }

    private void parseFields() {
        Class<?> c = target.getClass();
        for (Field field : c.getDeclaredFields()) {
            String fieldName = field.getName();

            Option option = field.getAnnotation(Option.class);
            if (option == null) {
                // Not a command line option
                continue;
            }
            optionMetadata.put(fieldName, new OptionMetadata(field));

            String shortName = option.shortName().trim();
            if (shortName.length() > 0) {
                optionName.put(shortName, fieldName);
            }

            String longName = option.name().trim();
            if (longName.length() == 0) {
                // CamelCase to camel-case
                longName = fieldName.replaceAll("(?=[A-Z])", "-").toLowerCase();
            }
            optionName.put(longName, fieldName);
        }
    }

    private String[] bind(String[] args) throws
            MissingOptionArgumentException,
            UnsupportedOptionArgumentTypeException,
            IllegalOptionArgumentException,
            MissingRequiredOptionException,
            UnsupportedOptionException
    {
        List<String> rest = new LinkedList<String>();
        for (int i = 0; i < args.length; i++) {
            String option = args[i];

            String name;
            if (option.startsWith("--")) {
                // option with long name
                name = optionName.get(option.substring(2));
            } else if (option.startsWith("-")) {
                // option with short name
                name = optionName.get(option.substring(1));
            } else {
                // not a option
                rest.add(option);
                continue;
            }

            OptionMetadata meta = optionMetadata.get(name);
            if (meta == null) {
                throw new UnsupportedOptionException(option);
            }

            String value = "true"; // default value for non-argument option(boolean)
            if (meta.hasArgument) {
                if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                    value = args[++i];
                } else {
                    throw new MissingOptionArgumentException(option, meta.type);
                }
            }
            meta.setValue(target, value);
        }

        for (OptionMetadata arg : optionMetadata.values()) {
            if (arg.required && ! arg.specified) {
                throw new MissingRequiredOptionException(arg.name);
            }
        }
        return rest.toArray(new String[0]);
    }

    /**
     * Parse the arguments according to the specified options.
     * @param o the target object that options' values will bind to.
     * @param args the command line arguments
     * @return the rest arguments (not an option and not an argument of option).
     * @throws MissingOptionArgumentException
     * if an option need an argument but not specified.
     * @throws UnsupportedOptionArgumentTypeException
     * if the target type does not have a constructor with String argument.
     * @throws IllegalOptionArgumentException
     * if the string does not contain a parsable data.
     * @throws MissingRequiredOptionException
     * if the required option is not specified.
     * @throws UnsupportedOptionException 
     * no such option.
     */
    public static String[] assign(Object o, String[] args) throws
            MissingOptionArgumentException,
            UnsupportedOptionArgumentTypeException,
            IllegalOptionArgumentException,
            MissingRequiredOptionException,
            UnsupportedOptionException
    {
        return new CommandLineOption(o).bind(args);
    }
}
