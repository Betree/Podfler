//
// Created by Benjamin Piouffle on 12/21/15.
//

#ifndef PLANTWATCHER_PLANTSENSORS_H
#define PLANTWATCHER_PLANTSENSORS_H

#include "aJSON.h"

class PlantSensors
{
    PlantSensors(void);

    bool refreshData(void);             // Refresh data by reading all sensors
    aJsonObject* toJSON(void) const;    // Create a JSON object representation
    bool save();                        // Locally save data
    bool load();                        // Locally loads data

    // Getters
    double getBrightness(void) const;
    double getTemperature(void) const;
    double getAirHumidity(void) const;
    double getGroundHumidity(void) const;

private:
    double _brightness;
    double _temperature;
    double _airHumidity;
    double _groundHumidity;
};


#endif //PLANTWATCHER_PLANTSENSORS_H
