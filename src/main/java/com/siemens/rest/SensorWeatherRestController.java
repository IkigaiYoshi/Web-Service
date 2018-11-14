package com.siemens.rest;

import com.siemens.model.SensorWeatherData;
import com.siemens.service.SensorWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * REST controller for {@link SensorWeatherData} connected requests.
 *
 */
@RestController
@RequestMapping("/")
public class SensorWeatherRestController {

    @Autowired
    private SensorWeatherService sensorWeatherService;


    @RequestMapping(value = "/service", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<SensorWeatherData> getAllWeatherCord() {
        if(this.sensorWeatherService.getAll().size() < 10){
            return this.sensorWeatherService.getAll();
        }
        else {
            return this.sensorWeatherService.getAll().subList(this.sensorWeatherService.getAll().size() - 10, this.sensorWeatherService.getAll().size());
        }
    }

    @RequestMapping(value = "/service", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SensorWeatherData> save(@RequestBody @Valid SensorWeatherData sensorWeatherData) {
        String formatCoordinate = "-?[1-9][0-9]*(\\.[0-9]+)?:\\s*-?[1-9][0-9]*(\\.[0-9]+)?";
        if (sensorWeatherData == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else if(Integer.parseInt(sensorWeatherData.getTemperature()) < -40 || Integer.parseInt(sensorWeatherData.getTemperature()) > 40){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else if(!sensorWeatherData.getCoordinate().matches(formatCoordinate)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.sensorWeatherService.save(sensorWeatherData);
        return new ResponseEntity<>(sensorWeatherData, HttpStatus.CREATED);
    }
}
