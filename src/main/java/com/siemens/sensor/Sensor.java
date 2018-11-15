package com.siemens.sensor;

import java.util.Map;

/**
 * Simple sensor for receiving data and sending to server.
 *
 */

public class Sensor {


    public static void main(String[] args) throws Exception {

        //create for receiving and sending data
        ConnectionOfSensor connection = new ConnectionOfSensor();

        while (true) {
            //Get coordinate from API
            Map coordinate = connection.getCoordinate();
            //Get temperature from API by sending coordinates
            String temperature = connection.getTemperature((String) coordinate.get("lat"), (String) coordinate.get("lon"));
            //Send all data on server(SensorWeatherRestController)
            connection.sendData(temperature, (String) coordinate.get("lat"), (String) coordinate.get("lon"));
            //Request timeout
            Thread.sleep(60000);//For example 60000ms - 1 minutes(Default timeout 600000ms - 10 minutes)
        }
    }
}

