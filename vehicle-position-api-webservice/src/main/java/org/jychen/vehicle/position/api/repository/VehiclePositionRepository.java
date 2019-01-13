package org.jychen.vehicle.position.api.repository;

import org.jychen.vehicle.position.api.domain.VehiclePosition;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehiclePositionRepository extends CrudRepository<VehiclePosition, Integer> {
    List<VehiclePosition> findByVehicleName(String vehicleName);
}
