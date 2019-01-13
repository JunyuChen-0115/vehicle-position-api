package org.jychen.vehicle.position.api.repository;

import org.jychen.vehicle.position.api.domain.VehiclePosition;
import org.springframework.data.repository.CrudRepository;

public interface VehiclePositionRepository extends CrudRepository<VehiclePosition, Integer> {
}
