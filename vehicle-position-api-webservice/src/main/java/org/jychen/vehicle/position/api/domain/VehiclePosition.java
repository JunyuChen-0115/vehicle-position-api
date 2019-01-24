package org.jychen.vehicle.position.api.domain;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("vehicle_position")
public class VehiclePosition {

    @Column
    private UUID id;

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, name = "vehicle_name")
    private String vehicleName;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    private Date ts;

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

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return String.format("{ @id = %1$s, vehicleName = %2$s, latitude = %3$s, longitude = %4$s, ts = %5$s }",
                getId(), getVehicleName(), getLatitude(), getLongitude(), getTs());
    }
}
