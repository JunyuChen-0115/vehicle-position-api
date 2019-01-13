package service;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jychen.vehicle.position.api.domain.VehiclePosition;
import org.jychen.vehicle.position.api.repository.VehiclePositionRepository;
import org.jychen.vehicle.position.api.service.VehiclePositionService;
import org.jychen.vehicle.position.api.service.VehiclePositionServiceImp;

@RunWith(JMockit.class)
public class VehiclePositionServiceTest {

    @Tested
    private VehiclePositionService serviceUnderTest = new VehiclePositionServiceImp(null);

    @Injectable
    private VehiclePositionRepository vehiclePositionRepository;


    @Test
    public void save_WhenInputIsValid_Expect_SaveEntry() {
        VehiclePosition position = new VehiclePosition();
        position.setId(1);
        position.setVehicleName("first_truck");

        new Expectations() {{
            vehiclePositionRepository.save(position);
        }};

        VehiclePosition savedEntry = serviceUnderTest.save(position);
        Assert.assertTrue(savedEntry instanceof VehiclePosition);
    }
}
