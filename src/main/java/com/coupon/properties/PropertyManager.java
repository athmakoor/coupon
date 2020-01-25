package com.coupon.properties;

import java.util.Properties;

/**
 * @author customfurnish.com
 */
public final class PropertyManager {
    private static Properties properties = null;

    /**
     * Its a private constructor.
     */
    private PropertyManager() {

    }

    private static String getProperty(final String name) {
        try {
            if (properties == null) {
                properties = new PropertyLoader().getProperties();
            }
            if (properties.containsKey(name)) {
                return properties.getProperty(name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static Long getTokenExpirationMsec() {
        return Long.parseLong(getProperty("auth.tokenExpirationMsec"));
    }

    public static String getTokenSecret() {
        return getProperty("auth.tokenSecret");
    }
}
