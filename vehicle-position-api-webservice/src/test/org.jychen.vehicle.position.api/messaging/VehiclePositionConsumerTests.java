package messaging;

import org.junit.Before;
import org.junit.Test;
import org.jychen.vehicle.position.api.integration.dto.VehiclePositionDTO;
import org.jychen.vehicle.position.api.messaging.VehiclePositionConsumer;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;

import java.sql.Timestamp;
import java.util.Date;

public class VehiclePositionConsumerTests {


    @Mock
    private Logger logger;

    private VehiclePositionConsumer consumerUnderTest;

    @Before
    public void setup() {
        consumerUnderTest = Mockito.mock(VehiclePositionConsumer.class);
    }

    @Test
    public void receiveMessage_WhenInputIsValid_Expects_LogMessage() {
        VehiclePositionDTO dto = new VehiclePositionDTO();
        dto.setVehicleName("city_truck");
        dto.setLatitude(new Double(12.333));
        dto.setLongitude(new Double(12.333));

        consumerUnderTest.receiveMessage("hello");

        Mockito.verify(logger, Mockito.times(1)).info("hello");
    }

}
