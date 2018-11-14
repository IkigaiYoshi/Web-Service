package com.siemens.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Simple JavaBean domain object that represents SensorWeatherData.
 *
 */

@Entity
@Table(name = "service")
@ToString(of = {"temperature", "coordinate"})
@EqualsAndHashCode(of={"id"})
public class SensorWeatherData extends ModelData{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "temperature")
    private String temperature;

    @Column(name = "coordinate")
    private String coordinate;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
