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

    public boolean requestNewData() {
        return false;
    }

    public double getAirTemperature() {
        return airTemperature;
    }


    public double getAirHumidity() {
        return airHumidity;
    }


    public double getGroundHumidity() {
        return groundHumidity;
    }


    public double getLuminosity() {
        return luminosity;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }
}
