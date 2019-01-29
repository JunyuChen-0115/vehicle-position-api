package service;

import com.datastax.driver.core.Statement;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jychen.vehicle.position.api.domain.VehiclePosition;
import org.jychen.vehicle.position.api.integration.searchcriteria.VehiclePositionSearchCriteria;
import org.jychen.vehicle.position.api.repository.VehiclePositionRepository;
import org.jychen.vehicle.position.api.service.VehiclePositionService;
import org.jychen.vehicle.position.api.service.VehiclePositionServiceImp;
import org.springframework.data.cassandra.core.CassandraTemplate;

@RunWith(JMockit.class)
public class VehiclePositionServiceTest {

    @Tested
    private VehiclePositionService serviceUnderTest = new VehiclePositionServiceImp(null, null);

    @Injectable
    private VehiclePositionRepository vehiclePositionRepository;

    @Injectable
    private CassandraTemplate cassandraTemplate;

    private static final String VEHICLE_NAME = "city_truck";

    @Test
    public void save_WhenInputIsValid_Expect_SaveEntry() {
        VehiclePosition position = new VehiclePosition();
        position.setId(UUID.randomUUID());
        position.setVehicleName("first_truck");

        new Expectations() {{
            vehiclePositionRepository.save(position);
        }};

        VehiclePosition savedEntry = serviceUnderTest.save(position);
        Assert.assertTrue(savedEntry instanceof VehiclePosition);
    }

    @Test
    public void findByCriteria_When_OnlyVehicleNameIsGiven_Expect_FindAllRecordsForGivenVehicle() {

        VehiclePositionSearchCriteria searchRequest = new VehiclePositionSearchCriteria();
        searchRequest.setVehicleName(VEHICLE_NAME);
        searchRequest.setTs(System.currentTimeMillis());
        searchRequest.setLimit(3);

        new Expectations() {{
            cassandraTemplate.select((Statement) any, VehiclePosition.class);
        }};

        List<VehiclePosition> actual = serviceUnderTest.findByCriteria(searchRequest);
        Assert.assertTrue(actual instanceof List);
    }
}
