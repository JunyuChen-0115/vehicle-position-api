package org.jychen.vehicle.position.api.service;

import org.jychen.vehicle.position.api.domain.VehiclePosition;

import java.util.Date;
import java.util.List;
import org.jychen.vehicle.position.api.integration.searchcriteria.VehiclePositionSearchCriteria;

public interface VehiclePositionService {
    VehiclePosition save(VehiclePosition vehiclePosition);
    VehiclePosition getTopByVehicleNameOrderByTsDesc(String vehicleName);
    List<VehiclePosition> findAllByVehicleNameOrderByTsDesc(String vehicleName);
    List<VehiclePosition> findByVehicleNameAndTsGreaterThanEqualOrderByTsDesc(String vehicleName, Date ts);
    List<VehiclePosition> findAllByTsGreaterThanEqualOrderByTsDesc(Date ts);
    List<VehiclePosition> findByCriteria(VehiclePositionSearchCriteria searchCriteria);
}
