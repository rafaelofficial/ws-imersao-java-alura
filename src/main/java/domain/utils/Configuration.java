package domain.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    public static Properties getProps() {
        Properties properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/main/resources/properties/config.properties");
            properties.load(file);
        } catch (IOException e) {
            System.out.println("Not found file " + e.getMessage());
        }
        return properties;
    }
}
