package com.eu.habbo;

import java.util.Properties;

public class BuildProperties {

    public static final String commit;
    public static final String version;

    static {
        try {
            final Properties buildProperties = new Properties();

            buildProperties.load(BuildProperties.class.getResourceAsStream("/build.properties"));

            commit = buildProperties.getProperty("build.commit");
            version = buildProperties.getProperty("build.version");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
