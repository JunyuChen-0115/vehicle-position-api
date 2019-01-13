package org.jychen.vehicle.position.api.service;

import org.jychen.vehicle.position.api.domain.VehiclePosition;
import org.jychen.vehicle.position.api.repository.VehiclePositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiclePositionServiceImp implements VehiclePositionService {

    private static final Logger logger = LoggerFactory.getLogger(VehiclePositionServiceImp.class);

    private VehiclePositionRepository vehiclePositionRepository;

    @Autowired
    public VehiclePositionServiceImp(VehiclePositionRepository vehiclePositionRepository) {
        this.vehiclePositionRepository = vehiclePositionRepository;
    }

    @Override
    public VehiclePosition save(VehiclePosition vehiclePosition) {
        logger.info("Saving vehicle position with id: {} and name: {}", vehiclePosition.getId(), vehiclePosition.getVehicleName());
        return vehiclePositionRepository.save(vehiclePosition);
    }
}
