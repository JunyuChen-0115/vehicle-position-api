package messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jychen.vehicle.position.api.domain.VehiclePosition;
import org.jychen.vehicle.position.api.integration.dto.VehiclePositionDTO;
import org.jychen.vehicle.position.api.messaging.VehiclePositionConsumer;
import org.jychen.vehicle.position.api.service.VehiclePositionService;


@RunWith(JMockit.class)
public class VehiclePositionConsumerTests {

    @Tested
    private VehiclePositionConsumer consumerUnderTest;

    @Injectable
    private VehiclePositionService vehiclePositionService;

    @Mocked
    private ObjectMapper mockedMapper;


    @Test
    public void receiveMessage_WhenInputIsValid_Expects_LogMessage() {
        VehiclePositionDTO dto = new VehiclePositionDTO();
        dto.setId(1);
        dto.setVehicleName("city_truck");
        dto.setLatitude(new Double(12.333));
        dto.setLongitude(new Double(12.333));

        ObjectMapper objectMapper = new ObjectMapper();
        VehiclePosition vehiclePosition = objectMapper.convertValue(dto, VehiclePosition.class);

        new Expectations() {{
            mockedMapper.convertValue(dto, VehiclePosition.class);
            result = vehiclePosition;
            vehiclePositionService.save(vehiclePosition);
        }};

        consumerUnderTest.receiveMessage(dto);

    }

}
