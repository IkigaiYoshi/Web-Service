package com.siemens.sensor;

import java.util.Map;

public class Sensore {


    public static void main(String[] args) throws Exception {

        while (true) {
            ConnectionOfSensore connection = new ConnectionOfSensore();
            Map coordinate = connection.getCoordinate();
            String temperature = connection.getTemperature((String) coordinate.get("lat"), (String) coordinate.get("lon"));
            connection.sendData(temperature, (String) coordinate.get("lat"), (String) coordinate.get("lon"));
            Thread.sleep(1000000);
        }
    }
}

