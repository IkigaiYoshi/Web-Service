package com.siemens.service;

import com.siemens.model.SensorWeatherData;

import java.util.List;

public interface SensorWeatherService {
    SensorWeatherData getById(Long id);

    void save(SensorWeatherData sensorWeatherData);

    void delete(Long id);

    List<SensorWeatherData> getAll();
}
