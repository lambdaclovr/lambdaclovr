package lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration;

import java.util.Map;

/**
 * Configuration Helper
 */

/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer: Streaming Acquisition</h2>
 * <h3>Package Name: lambdaclovr.dsl.data.acquisition.streaming.operations.client.configuration</h3>
 * <h3>Class Name: ConfigHelp</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 * @author Numan Khan
 * 
 * @version 1.0
 * @since 2024-27-11
 * 
 * */
public final class ConfigHelp {

    private ConfigHelp() {

    }

    /**
     * Get a property value as int. If it does not exists, it returns the default value
     *
     * @param configuration Map of properties
     * @param property name to be searched
     * @return property value as int
     * @throws IllegalArgumentException when the value is not a number
     *
     */
    public static int getOrDefaultIntProperty(final Map<String, String> configuration,
                                              final PropertyNames property) {
        try {
            return Integer.parseInt(configuration.getOrDefault(property.getPropertyName(), property.getDefaultValue()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(property.getPropertyName() + " invalid property value. " + e.getMessage());
        }
    }

    /**
     * Get a property value as String. If it does not exists, it throws an exception
     *
     * @param configuration Map of properties
     * @param property name to be searched
     * @return property value as String
     * @throws IllegalArgumentException if the property does not exists
     */
    public static String getRequiredStringProperty(final Map<String, String> configuration,
                                                   final PropertyNames property) {

        if (!configuration.containsKey(property.getPropertyName())) {
            throw new IllegalArgumentException(property.getPropertyName() + " property is missing");
        }
        return configuration.get(property.getPropertyName());

    }
}
