package org.jychen.vehicle.position.api.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jychen.vehicle.position.api.domain.VehiclePosition;
import org.jychen.vehicle.position.api.integration.dto.VehiclePositionDTO;
import org.jychen.vehicle.position.api.service.VehiclePositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class VehiclePositionConsumer {

    private static Logger logger = LoggerFactory.getLogger(VehiclePositionConsumer.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    private VehiclePositionService vehiclePositionService;

    @Autowired
    public VehiclePositionConsumer(VehiclePositionService vehiclePositionService) {
        this.vehiclePositionService = vehiclePositionService;
    }

    @KafkaListener(topics = "first-topic", groupId = "one")
    public void receiveMessage(VehiclePositionDTO vehiclePositionDTO) {
        logger.info("Received message: id: {}, name: {}", vehiclePositionDTO.getId(), vehiclePositionDTO.getVehicleName());
        VehiclePosition vehiclePosition = objectMapper.convertValue(vehiclePositionDTO, VehiclePosition.class);
        vehiclePositionService.save(vehiclePosition);
    }
}
