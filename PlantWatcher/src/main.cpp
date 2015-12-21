//
// Created by piouffb on 12/19/15.
//

#include "aJSON.h"
#include "ESP8266.h"
#include "main.h"

#define SSID        "FBI_Security_Van"
#define PASSWORD    "22 Rue du 14 Juillet"

#define STR_STATUS_OK       "OK"
#define STR_STATUS_ERROR    "ERROR"

ESP8266 wifi(Serial2);

/* -------------------- Init -------------------- */

void initServer(void)
{
    Serial.begin(9600);
    Serial.print("Setup begin\r\n");

    if (!wifi.setOprToStationSoftAP()) {
        Serial.print("OPR to station failed\r\n");
    }

    if (wifi.joinAP(SSID, PASSWORD)) {
        Serial.print("IP: ");
        Serial.println(wifi.getLocalIP().c_str());
    } else {
        Serial.print("Join AP failure\r\n");
    }

    if (!wifi.enableMUX()) {
        Serial.print("Enable MUX failed\r\n");
    }

    if (wifi.startTCPServer(8090)) {
        Serial.print("TCP server started\r\n");
    } else {
        Serial.print("TCP server failed to start\r\n");
    }

    if (!wifi.setTCPServerTimeout(10)) {
        Serial.print("Set timeout failed\r\n");
    }
}

void setup(void)
{
    initServer();
}

/* -------------------- Loop -------------------- */

void buildJsonResponse(aJsonObject* response, const char* status, aJsonObject* body = NULL, const char* message = "")
{
    aJson.addStringToObject(response, "status", status);
    aJson.addStringToObject(response, "message", message);
    if (body != NULL) {
        aJson.addItemToObject(response, "body", body);
    }
}

aJsonObject* processCommand(String command)
{
    aJsonObject* response = aJson.createObject();

    if (command.compareTo("get_status_current")) {
        buildJsonResponse(response, STR_STATUS_OK, NULL, "It works !");
    }
//    else if (command.compareTo("get_status_history")) {
//        //TODO (add dateFrom to request)
//    }
    else
        buildJsonResponse(response, STR_STATUS_ERROR, NULL, "Unknown command");
    return response;
}

void loop(void)
{
    uint8_t buffer[128] = {0};
    uint8_t muxId;

    // Wait for a request
    uint32_t requestLen = wifi.recv(&muxId, buffer, sizeof(buffer));

    if (requestLen > 0) {
        Serial.print("Got something intresting\n");

        // Convert buffer to char[]
        char charBuffer[128];
        for (uint32_t i = 0; i < requestLen; i++) {
            charBuffer[i] = (char)(buffer[i]);
        }

        // Get response
        aJsonObject* responseJson = processCommand(charBuffer);
        //TODO: Delete JSON Object
        String response = aJson.print(responseJson);
        Serial.println(response);

        // Put response in buffer
        for (uint32_t i = 0; i < response.length(); i++) {
            buffer[i] = (uint8_t)(response[i]);
        }

        // Send response
        if (!wifi.send(muxId, buffer, response.length())) {
            Serial.print("Send response failed\r\n");
        }

        // Release TCP connection
        if (!wifi.releaseTCP(muxId)) {
            Serial.print("Release tcp failed for :");
            Serial.print(muxId);
        }
    }
}


