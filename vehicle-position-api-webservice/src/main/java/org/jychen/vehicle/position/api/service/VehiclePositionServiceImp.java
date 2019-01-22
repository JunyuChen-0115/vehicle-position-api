package org.jychen.vehicle.position.api.service;

import org.jychen.vehicle.position.api.domain.VehiclePosition;
import org.jychen.vehicle.position.api.repository.VehiclePositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

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

    @Override
    public VehiclePosition getTopByVehicleNameOrderByTsDesc(String vehicleName) {
        logger.info("Attempting to get the latest position for vehicle {}.", vehicleName);
        return vehiclePositionRepository.getTopByVehicleNameOrderByTsDesc(vehicleName);
    }

    @Override
    public List<VehiclePosition> findAllByVehicleNameOrderByTsDesc(String vehicleName) {
        logger.info("Attempting to find all position records for vehicle {}.", vehicleName);
        return vehiclePositionRepository.findAllByVehicleNameOrderByTsDesc(vehicleName);
    }

    @Override
    public List<VehiclePosition> findByVehicleNameAndTsGreaterThanEqualOrderByTsDesc(String vehicleName, Timestamp ts) {
        logger.info("Attempting to find position records for vehicle {} since {}.", vehicleName, ts);
        return vehiclePositionRepository.findByVehicleNameAndTsGreaterThanEqualOrderByTsDesc(vehicleName, ts);
    }

    @Override
    public List<VehiclePosition> findAllByTsGreaterThanEqualOrderByTsDesc(Timestamp ts) {
        logger.info("Attempting to find all position records for all vehicle since {}.", ts);
        return vehiclePositionRepository.findAllByTsGreaterThanEqualOrderByTsDesc(ts);
    }
}
