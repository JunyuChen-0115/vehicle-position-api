package org.jychen.vehicle.position.api.repository;

import org.jychen.vehicle.position.api.domain.VehiclePosition;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface VehiclePositionRepository extends CassandraRepository<VehiclePosition, UUID> {
    VehiclePosition getTopByVehicleNameOrderByTsDesc(String vehicleName);
    List<VehiclePosition> findAllByVehicleNameOrderByTsDesc(String vehicleName);
    List<VehiclePosition> findByVehicleNameAndTsGreaterThanEqualOrderByTsDesc(String vehicleName, Timestamp ts);
    List<VehiclePosition> findAllByTsGreaterThanEqualOrderByTsDesc(Timestamp ts);
}
