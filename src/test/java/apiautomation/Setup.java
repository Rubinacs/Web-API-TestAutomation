package apiautomation;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.Properties;

public class Setup {

    private static Properties properties;

    /**
     *Method to load the properties file
     */
    public static void loadProp() {
        if (properties == null) {
            properties = new Properties();

            try {
                properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            } catch (Exception ex) {
                System.err.println("Could not retrieve value from the application properties file");
                ex.printStackTrace();
                System.exit(-1);
            }
        }
    }

    /**
     * Method to pass the key as propName and get a value for the passed key
     * @param propName property key name
     * @return value of the given propName
     */
    static String getValueFromProp( String propName) {
        String value = "";
        Setup.loadProp();
        value = properties.getProperty(propName);

        if (Objects.equals(propName, "gistTokenWithOutScope") || Objects.equals(propName, "gistTokenWithScope")) {
            return new String(Base64.getDecoder().decode(value.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        }
        return value;
    }
}
