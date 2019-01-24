package org.jychen.vehicle.position.api.integration.searchcriteria;

import java.util.Date;
import java.util.UUID;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class VehiclePositionSearchCriteria {
    private static final int MAX_RESULT_SIZE = 500;
    private UUID id;
    private String vehicleName;
    private Date ts;
    private Integer limit = MAX_RESULT_SIZE;

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

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
