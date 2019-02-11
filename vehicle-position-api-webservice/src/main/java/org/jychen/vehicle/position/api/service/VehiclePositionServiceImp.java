package org.jychen.vehicle.position.api.service;

import com.datastax.driver.core.querybuilder.Clause;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jychen.vehicle.position.api.constant.Constants;
import org.jychen.vehicle.position.api.domain.VehiclePosition;
import org.jychen.vehicle.position.api.integration.searchcriteria.VehiclePositionSearchCriteria;
import org.jychen.vehicle.position.api.repository.VehiclePositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Service;

@Service
public class VehiclePositionServiceImp implements VehiclePositionService {

    private static final Logger logger = LoggerFactory.getLogger(VehiclePositionServiceImp.class);

    private VehiclePositionRepository vehiclePositionRepository;

    private CassandraTemplate cassandraTemplate;

    @Autowired
    public VehiclePositionServiceImp(VehiclePositionRepository vehiclePositionRepository,
            CassandraTemplate cassandraTemplate) {
        this.vehiclePositionRepository = vehiclePositionRepository;
        this.cassandraTemplate = cassandraTemplate;
    }

    @Override
    public VehiclePosition save(VehiclePosition vehiclePosition) {
        logger.info("Saving vehicle position with id: {} and name: {}", vehiclePosition.getId(),
                vehiclePosition.getVehicleName());
        return vehiclePositionRepository.save(vehiclePosition);
    }

    @Override
    public List<VehiclePosition> findByCriteria(VehiclePositionSearchCriteria searchCriteria) {
        logger.info("Attempting to search vehicle position records by criteria: {}", searchCriteria);
        List<Clause> whereClauses = new ArrayList<>();
        Select selectQuery = QueryBuilder.select().from(Constants.TABLE_NAME);
        if (searchCriteria.getId() != null) {
            whereClauses.add(QueryBuilder.eq(Constants.COLUMN_NAME_ID, searchCriteria.getId()));
        }

        if (StringUtils.isNotEmpty(searchCriteria.getVehicleName())) {
            whereClauses.add(QueryBuilder.eq(Constants.COLUMN_NAME_VEHICLE_NAME, searchCriteria.getVehicleName()));
        }

        if (searchCriteria.getTs() != null) {
            whereClauses.add(QueryBuilder.gte(Constants.COLUMN_NAME_TIMESTAMP, searchCriteria.getTs()));
        }

        for (Clause clause : whereClauses) {
            selectQuery.where(clause);
        }

        if (searchCriteria.getLimit() != null) {
            selectQuery.limit(searchCriteria.getLimit());
        }

        logger.info("Generated query: {}", selectQuery.toString());
        return cassandraTemplate.select(selectQuery, VehiclePosition.class);
    }
}
