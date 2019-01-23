package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jychen.vehicle.position.api.controller.VehiclePositionController;
import org.jychen.vehicle.position.api.domain.VehiclePosition;
import org.jychen.vehicle.position.api.integration.dto.VehiclePositionDTO;
import org.jychen.vehicle.position.api.service.VehiclePositionService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(JMockit.class)
public class VehiclePositionControllerTest {

    @Tested
    private VehiclePositionController controllerUnderTest = new VehiclePositionController(null);

    @Injectable
    private VehiclePositionService vehiclePositionService;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controllerUnderTest).build();
        mapper = new ObjectMapper();
    }


    @Test
    public void save_When_InputIsValid_Expect_InvokeVehiclePositionServiceSave() throws Exception {
        VehiclePositionDTO vehiclePositionDTO = new VehiclePositionDTO();
        UUID uuid = UUID.randomUUID();
        String vehicleName = "speeding_truck";
        vehiclePositionDTO.setId(uuid);
        vehiclePositionDTO.setVehicleName(vehicleName);

        mockMvc.perform(
                post(controllerUnderTest.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(vehiclePositionDTO)))
                .andExpect(status().isOk())
                .andReturn();

        new Verifications() {{
            VehiclePosition vehiclePosition;
            vehiclePositionService.save(vehiclePosition = withCapture());
            Assert.assertEquals(uuid, vehiclePosition.getId());
            Assert.assertEquals(vehicleName, vehiclePosition.getVehicleName());
        }};
    }

    @Test
    public void findByCriteria_When_OnlyFirstRecordIsNeeded_Expect_InvokeGetTopByVehicleNameOrderByTsDescInVehiclePositionService() throws Exception {
        String vehicleName = "speeding_truck";

        mockMvc.perform(
                get(controllerUnderTest.BASE_URL)
                .param("vehicleName", vehicleName)
                .param("limit", "1"))
                .andExpect(status().isOk());

        new Verifications() {{
            String actualVehicleName;
            vehiclePositionService.getTopByVehicleNameOrderByTsDesc(actualVehicleName = withCapture());
            Assert.assertEquals(vehicleName, actualVehicleName);
        }};
    }
}
