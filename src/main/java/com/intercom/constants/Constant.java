package com.intercom.constants;

import com.intercom.models.Location;

/**
 * A class that contains all the constant values.
 */
public class Constant {
    /**
     * Location of Dublin office
     */
    public final static Location DUBLIN_OFFICE = new Location(53.339428, -6.257664);

    /**
     * Latitude attribute key
     */
    public final static String LATITUDE_KEY = "latitude";

    /**
     * Longitude attribute key
     */
    public final static String LONGITUDE_KEY = "longitude";

    /**
     * user id attribute key
     */
    public final static String USER_ID_KEY = "user_id";

    /**
     * name attribute key
     */
    public final static String NAME_KEY = "name";

    Constant() {
        throw new RuntimeException("Utility classes shouldn't be instantiated");
    }
}
