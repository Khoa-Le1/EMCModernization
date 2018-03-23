/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ehealthsask.emc.demo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 * Upon initialization, will check to see if a properties file already exists in
 * the same location as the jar file. If so, that properties file will be used.
 * If not, the default properties file will be used.
 *
 * @author rfelicia
 */
@Slf4j
public final class Config {

    private static final Properties properties = new Properties();

    public static final String ENVIRONMENT;
    public static final String NETWORK_CONFIG_PATH;

    static {
        ENVIRONMENT = get("environment");
        NETWORK_CONFIG_PATH = get("networkPath");
    }

    /**
     * Check first to see if there's a config file under the jar's parent
     * directory. If there is, use that configuration file. If not, use the
     * default from the jar.
     *
     */
    public static void initProperties() {
        String root = "./";
        String propertiesName = "emcDemo.properties";
        try {
            Optional<Path> prsConfigFile = Files.list(Paths.get(root))
                    .filter(file -> propertiesName.equals(file.getFileName().toString()))
                    .findFirst();
            if (prsConfigFile.isPresent()) {
                System.out.println("Custom properties outside of project");
                System.out.println(Paths.get(root, propertiesName).toAbsolutePath().normalize().toString());
                try (InputStream stream = new FileInputStream(Paths.get(root, propertiesName).toString())) {
                    properties.load(stream);
                }
            } else {
                System.out.println("Default jar config properties");
                String config = "application.properties";
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                try (InputStream stream = loader.getResourceAsStream(config)) {
                    properties.load(stream);
                } catch (IOException ex) {
                    log.warn("Error retrieving properties");
                }
            }
        } catch (IOException ex) {
            log.warn("Problem occurred in listing files.: ", ex);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String get(String key, String defaultVal) {
        return properties.getProperty(key, defaultVal);
    }

    public static String getString(String key) {
        return properties.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }
}
