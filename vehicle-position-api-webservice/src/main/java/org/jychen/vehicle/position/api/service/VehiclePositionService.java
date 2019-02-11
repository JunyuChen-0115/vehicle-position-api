package org.jychen.vehicle.position.api.service;

import java.util.List;
import org.jychen.vehicle.position.api.domain.VehiclePosition;
import org.jychen.vehicle.position.api.integration.searchcriteria.VehiclePositionSearchCriteria;

public interface VehiclePositionService {

    VehiclePosition save(VehiclePosition vehiclePosition);

    List<VehiclePosition> findByCriteria(VehiclePositionSearchCriteria searchCriteria);
}
