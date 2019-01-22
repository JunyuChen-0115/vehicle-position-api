package org.jychen.vehicle.position.api.service;

import org.jychen.vehicle.position.api.domain.VehiclePosition;

import java.sql.Timestamp;
import java.util.List;

public interface VehiclePositionService {
    VehiclePosition save(VehiclePosition vehiclePosition);
    VehiclePosition getTopByVehicleNameOrderByTsDesc(String vehicleName);
    List<VehiclePosition> findAllByVehicleNameOrderByTsDesc(String vehicleName);
    List<VehiclePosition> findByVehicleNameAndTsGreaterThanEqualOrderByTsDesc(String vehicleName, Timestamp ts);
    List<VehiclePosition> findAllByTsGreaterThanEqualOrderByTsDesc(Timestamp ts);
}
