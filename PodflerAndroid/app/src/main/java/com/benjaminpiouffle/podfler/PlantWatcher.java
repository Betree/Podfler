package com.benjaminpiouffle.podfler;

import java.io.Serializable;

/**
 * Created by piouffb on 08/01/16.
 */
public class PlantWatcher implements Serializable {
    private final String name;
    private final String ip;
    private double airTemperature;
    private double airHumidity;
    private double groundHumidity;
    private double luminosity;

    public PlantWatcher(String name, String ip) {
        this.name = name;
        this.ip = ip;
        this.airTemperature = 0.0;
        this.airHumidity = 0.0;
        this.groundHumidity = 0.0;
        this.luminosity = 0.0;
    }

    public double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public double getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(double airHumidity) {
        this.airHumidity = airHumidity;
    }

    public double getGroundHumidity() {
        return groundHumidity;
    }

    public void setGroundHumidity(double groundHumidity) {
        this.groundHumidity = groundHumidity;
    }

    public double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(double luminosity) {
        this.luminosity = luminosity;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }
}
