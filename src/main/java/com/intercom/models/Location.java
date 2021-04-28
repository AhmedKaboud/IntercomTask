package com.intercom.models;

import lombok.Data;

/**
 * A class that represent the location on the map.
 */
@Data
public class Location {
    final double longitude;
    final double latitude;

    public Location(double latitude, double longitude) {
        this.longitude = Math.toRadians(longitude);
        this.latitude = Math.toRadians(latitude);
    }
}
