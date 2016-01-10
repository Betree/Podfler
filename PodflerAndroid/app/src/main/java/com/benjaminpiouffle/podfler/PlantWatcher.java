package com.benjaminpiouffle.podfler;

import java.io.Serializable;

/**
 * Created by piouffb on 08/01/16.
 */
public class PlantWatcher implements Serializable {
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
