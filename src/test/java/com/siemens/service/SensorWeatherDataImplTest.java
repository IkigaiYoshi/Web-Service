package com.siemens.service;

import com.siemens.model.SensorWeatherData;
import com.siemens.repository.SensorWeatherRep;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorWeatherDataImplTest {
    @Autowired
    private SensorSensorWeatherImp service;
    @MockBean
    private SensorWeatherRep sensorWeatherRep;

    @Test
    public void save() {
        SensorWeatherData sensorWeatherData = new SensorWeatherData();
        service.save(sensorWeatherData);
        Mockito.verify(sensorWeatherRep, Mockito.times(1)).save(sensorWeatherData);
    }

    @Test
    public void getAll(){
        List<SensorWeatherData> listWeather = service.getAll();
        Assert.assertNotNull(listWeather);
    }
}