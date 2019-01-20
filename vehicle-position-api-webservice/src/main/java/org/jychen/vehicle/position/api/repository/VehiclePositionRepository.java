package org.jychen.vehicle.position.api.repository;

import org.jychen.vehicle.position.api.domain.VehiclePosition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiclePositionRepository extends CrudRepository<VehiclePosition, Integer> {
    List<VehiclePosition> findByVehicleName(String vehicleName);
}
