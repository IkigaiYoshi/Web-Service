package com.siemens.service;

import com.siemens.model.SensorWeatherData;
import com.siemens.repository.SensorWeatherRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SensorSensorWeatherImp implements SensorWeatherService {
    @Autowired
    SensorWeatherRep sensorWeatherRep;

    @Override
    public SensorWeatherData getById(Long id) {
        return sensorWeatherRep.findOne(id);
    }

    @Override
    public void save(SensorWeatherData sensorWeatherData) { sensorWeatherRep.save(sensorWeatherData);}

    @Override
    public void delete(Long id) {
        sensorWeatherRep.delete(id);
    }

    @Override
    public List<SensorWeatherData> getAll() {
        return sensorWeatherRep.findAll();
    }
}
