package org.jychen.vehicle.position.api.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class VehiclePositionConsumer {

    private static final Logger logger = LoggerFactory.getLogger(VehiclePositionConsumer.class);

    @KafkaListener(topics = "first-topic", groupId = "one")
    public void receiveMessage(String message) {
        logger.info("Received message: {}", message);
    }
}
