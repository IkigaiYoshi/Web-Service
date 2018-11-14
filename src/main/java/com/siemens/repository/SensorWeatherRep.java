package com.siemens.repository;

import com.siemens.model.SensorWeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link SensorWeatherData} class
 */
public interface SensorWeatherRep extends JpaRepository<SensorWeatherData, Long> {
}
