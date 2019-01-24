package service;

import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Select;
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

import java.util.Date;
import java.util.List;
import java.util.UUID;
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
    public void getTopByVehicleNameOrderByTsDesc_WhenInputIsValid_Expect_GetLatestPositionForGivenVehicle() {
        String vehicleName = "city_truck";

        new Expectations() {{
            vehiclePositionRepository.getTopByVehicleNameOrderByTsDesc(vehicleName);
        }};

        VehiclePosition actual = serviceUnderTest.getTopByVehicleNameOrderByTsDesc(vehicleName);
        Assert.assertTrue(actual instanceof VehiclePosition);
    }

    @Test
    public void findAllByVehicleNameOrderByTsDesc_WhenInputIsValid_Expect_FindAllRecordsForGivenVehicle() {
        String vehicleName = "city_truck";

        new Expectations() {{
            vehiclePositionRepository.findAllByVehicleNameOrderByTsDesc(vehicleName);
        }};

        List<VehiclePosition> actual = serviceUnderTest.findAllByVehicleNameOrderByTsDesc(vehicleName);
        Assert.assertTrue(actual instanceof List);
    }

    @Test
    public void findByVehicleNameAndTsGreaterThanEqualOrderByTsDesc_WhenInputIsValid_Expect_FindRecordsForGivenVehicleSinceGivenTime() {
        String vehicleName = "city_truck";
        Date ts = new Date();

        new Expectations() {{
            vehiclePositionRepository.findByVehicleNameAndTsGreaterThanEqualOrderByTsDesc(vehicleName, ts);
        }};

        List<VehiclePosition> actual = serviceUnderTest.findByVehicleNameAndTsGreaterThanEqualOrderByTsDesc(vehicleName, ts);
        Assert.assertTrue(actual instanceof List);
    }

    @Test
    public void findAllByTsGreaterThanEqualOrderByTsDesc_WhenInputIsValid_Expect_FindAllRecordsSinceGivenTime() {
        Date ts = new Date();

        new Expectations() {{
            vehiclePositionRepository.findAllByTsGreaterThanEqualOrderByTsDesc(ts);
        }};

        List<VehiclePosition> actual = serviceUnderTest.findAllByTsGreaterThanEqualOrderByTsDesc(ts);
        Assert.assertTrue(actual instanceof List);
    }

    @Test
    public void findByCriteria_When_OnlyVehicleNameIsGiven_Expect_FindAllRecordsForGivenVehicle() {

        VehiclePositionSearchCriteria searchRequest = new VehiclePositionSearchCriteria();
        searchRequest.setVehicleName(VEHICLE_NAME);
        searchRequest.setTs(new Date());
        searchRequest.setLimit(3);

        new Expectations() {{
            cassandraTemplate.select((Statement) any, VehiclePosition.class);
        }};

        List<VehiclePosition> actual = serviceUnderTest.findByCriteria(searchRequest);
        Assert.assertTrue(actual instanceof List);
    }
}
