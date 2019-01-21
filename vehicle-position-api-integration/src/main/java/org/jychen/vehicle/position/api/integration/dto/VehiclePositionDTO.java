package org.jychen.vehicle.position.api.integration.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

public class VehiclePositionDTO implements Serializable {

    private static final long serialVersionUID = 7972603774835586735L;

    private UUID id;
    private String vehicleName;
    private Double latitude;
    private Double longitude;
    private Timestamp ts;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }
}