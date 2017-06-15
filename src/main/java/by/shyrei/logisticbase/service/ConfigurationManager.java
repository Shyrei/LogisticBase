package by.shyrei.logisticbase.service;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Project LogisticBase
 * Created on 14.06.2017.
 * author Shyrei Uladzimir
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("configuration");

    private ConfigurationManager() {
    }

    public static Integer getProperty(String key) {
        int value;
        try {
            value = Integer.parseInt(resourceBundle.getString(key));
        } catch (MissingResourceException e) {
            // фатальная запись в лог
            throw new RuntimeException(e.getMessage());
        }
        return value;
    }
}
