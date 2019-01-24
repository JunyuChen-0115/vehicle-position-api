package org.jychen.vehicle.position.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.jychen.vehicle.position.api.domain.VehiclePosition;
import org.jychen.vehicle.position.api.integration.dto.VehiclePositionDTO;
import org.jychen.vehicle.position.api.service.VehiclePositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(VehiclePositionController.BASE_URL)
public class VehiclePositionController {

    public static final String BASE_URL = "/positions";

    private static final Logger logger = LoggerFactory.getLogger(VehiclePositionController.class);

    private ObjectMapper objectMapper;

    private VehiclePositionService vehiclePositionService;

    @Autowired
    public VehiclePositionController(VehiclePositionService vehiclePositionService) {
        this.vehiclePositionService = vehiclePositionService;
        objectMapper = new ObjectMapper();
    }

    @RequestMapping(method = RequestMethod.POST)
    public VehiclePositionDTO save(@RequestBody VehiclePositionDTO vehiclePositionDTO) {
        logger.info("Received request to save position record. id: {}, vehicleName: {}", vehiclePositionDTO.getId(), vehiclePositionDTO.getVehicleName());
        VehiclePosition vehiclePosition = objectMapper.convertValue(vehiclePositionDTO, VehiclePosition.class);
        VehiclePosition savedEntity = vehiclePositionService.save(vehiclePosition);
        return objectMapper.convertValue(savedEntity, VehiclePositionDTO.class);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<VehiclePositionDTO> findByCriteria(@RequestParam String vehicleName, @RequestParam(required = false) String limit) {
        logger.info("Received request to find position records by criteria: vehicleName {}, limit {}", vehicleName, limit);
        List<VehiclePosition> vehiclePositionList;
        if (StringUtils.isEmpty(limit)) {
            vehiclePositionList = vehiclePositionService.findAllByVehicleNameOrderByTsDesc(vehicleName);
        } else {
            VehiclePosition vehiclePosition = vehiclePositionService.getTopByVehicleNameOrderByTsDesc(vehicleName);
            vehiclePositionList = Collections.emptyList();
            if (vehiclePosition != null) {
                vehiclePositionList = Collections.singletonList(vehiclePosition);
            }
        }

        logger.info("{} number of results are found.", vehiclePositionList.size());
        return objectMapper.convertValue(vehiclePositionList, new TypeReference<List<VehiclePositionDTO>>(){});
    }
}
