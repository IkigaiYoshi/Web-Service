package com.siemens.sensor;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.json.JSONException;
import org.json.JSONObject;

public class ConnectionOfSensor {


    ConnectionOfSensor() {

    }

    public Map getCoordinate() throws JSONException {
        String addressCoordinateAPI = "http://api.sypexgeo.net/";
        String str = getJSON(addressCoordinateAPI, "Accept-Charset", "UTF-8", 10000);
        JSONObject json = new JSONObject(str);
        JSONObject jsonObject = (JSONObject) json.get("city");
        Map coordinate = new HashMap<String, String>();
        coordinate.put("lat", jsonObject.getString("lat"));
        coordinate.put("lon", jsonObject.getString("lon"));
        return coordinate;
    }


    public String getTemperature(String lat, String lon) throws JSONException {
        String addressWeatherAPI = "https://api.weather.yandex.ru/v1/forecast";
        String str = getJSON(addressWeatherAPI + "?lat=" + lat + "&lon=" + lon, "X-Yandex-API-Key", "0ca30491-c43d-47e2-92f2-5ca87927cbd8", 10000);
        JSONObject json = new JSONObject(str);
        json = (JSONObject) json.get("fact");

        return json.get("temp").toString();
    }

    private String getJSON(String url, String header, String valueOfHeader, int timeout) {
        HttpURLConnection conection = null;
        try {
            URL u = new URL(url);
            conection = (HttpURLConnection) u.openConnection();
            conection.setRequestMethod("GET");
            conection.setRequestProperty(header, valueOfHeader);
            conection.setUseCaches(false);
            conection.setAllowUserInteraction(false);
            conection.setConnectTimeout(timeout);
            conection.setReadTimeout(timeout);
            conection.connect();
            int status = conection.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conection != null) {
                try {
                    conection.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    private JSONObject setJSON(String temperature, String lat, String lon) throws JSONException {
        JSONObject postJson = new JSONObject();
        postJson.put("coordinate", lat + ":" + lon);
        postJson.put("temperature", temperature);
        return postJson;
    }

    public void sendData(String temp, String lat, String lon) {
        String addressServiceAPI = "http://localhost:8080/service";
        try {
            URL url = new URL(addressServiceAPI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");

            JSONObject jsonObject = setJSON(temp,lat,lon);
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(jsonObject.toString());
            wr.flush();
            wr.close();
            connection.connect();
            connection.getResponseCode();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}
