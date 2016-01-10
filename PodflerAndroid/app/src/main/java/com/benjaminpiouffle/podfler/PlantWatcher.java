package com.benjaminpiouffle.podfler;

/**
 * Created by piouffb on 08/01/16.
 */
public class PlantWatcher {
    private String name;
    private String ip;

    public PlantWatcher(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }
}
