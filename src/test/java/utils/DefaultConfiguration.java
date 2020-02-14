package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Loads configuration from config.properties
 *
 * @author Faizan Javed
 */
public class DefaultConfiguration {
    protected static final String DEFAULT_CONFIGURATION	= "conf/config.properties";
    protected static Properties	props = new Properties();

    static{
        loadProperties(props, DEFAULT_CONFIGURATION);
    }

    protected static Properties loadProperties(Properties props, String configurationFile) {
        try {
            props.load(new FileInputStream(new File(configurationFile)));
        } catch (Exception e) {
            e.printStackTrace();
            props = null;
        }
        return props;
    }

    public static String getProperty(String name) {
        return props.getProperty(name, null);
    }

    public static String getProperty(String name, String defaultValue) {
        return props.getProperty(name, defaultValue);
    }

}
