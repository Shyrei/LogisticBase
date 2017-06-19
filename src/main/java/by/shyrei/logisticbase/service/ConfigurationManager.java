package by.shyrei.logisticbase.service;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Project LogisticBase
 * Created on 14.06.2017.
 * author Shyrei Uladzimir
 */
public class ConfigurationManager {
    
    private final static Logger logger = LogManager.getLogger(ConfigurationManager.class);
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("configuration");

    private ConfigurationManager() {
    }

    public static Integer getProperty(String key) {
        int value;
        try {
            value = Integer.parseInt(resourceBundle.getString(key));
        } catch (MissingResourceException e) {
            logger.log(Level.FATAL, e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return value;
    }
}
