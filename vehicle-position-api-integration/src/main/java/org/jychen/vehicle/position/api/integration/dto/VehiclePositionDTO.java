package org.jychen.vehicle.position.api.integration.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class VehiclePositionDTO implements Serializable {

    private static final long serialVersionUID = 7325441071841728930L;

    private Integer id;
    private String vehicleName;
    private Double latitude;
    private Double longitude;
    private Timestamp timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}