package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Collections;
import mockit.Expectations;
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
import org.jychen.vehicle.position.api.integration.searchcriteria.VehiclePositionSearchCriteria;
import org.jychen.vehicle.position.api.service.VehiclePositionService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(JMockit.class)
public class VehiclePositionControllerTest {

    private static final String vehicleName = "speeding_truck";

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
    public void findByCriteria_When_GetTopRecord_Expect_InvokeFindByCriteriaInVehiclePositionService() throws Exception {

        new Expectations() {{
            vehiclePositionService.findByCriteria((VehiclePositionSearchCriteria) any);
            result = getMockVehiclePosition(vehicleName);
        }};

        MvcResult mvcResult = mockMvc.perform(
                get(controllerUnderTest.BASE_URL)
                .param("vehicleName", vehicleName)
                .param("limit", "1"))
                .andExpect(status().isOk())
                .andReturn();

        new Verifications() {{
            VehiclePositionSearchCriteria searchCriteria;
            vehiclePositionService.findByCriteria(searchCriteria = withCapture());
            Assert.assertEquals(vehicleName, searchCriteria.getVehicleName());
            Assert.assertEquals(Integer.valueOf(1), searchCriteria.getLimit());
        }};

        List<VehiclePositionDTO> actualResponse = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<VehiclePositionDTO>>(){});
        Assert.assertEquals(1, actualResponse.size());
    }

    @Test
    public void findByCriteria_When_FindAllRecordsForGivenVehicle_Expect_InvokeFindByCriteriaInVehiclePositionService() throws Exception {
        List<VehiclePosition> vehiclePositionList = Arrays.asList(getMockVehiclePosition(vehicleName),getMockVehiclePosition(vehicleName));

        new Expectations() {{
            vehiclePositionService.findByCriteria((VehiclePositionSearchCriteria) any);
            result = vehiclePositionList;
        }};

        MvcResult mvcResult = mockMvc.perform(
                get(controllerUnderTest.BASE_URL)
                        .param("vehicleName", vehicleName))
                .andExpect(status().isOk())
                .andReturn();

        new Verifications() {{
            VehiclePositionSearchCriteria searchCriteria;
            vehiclePositionService.findByCriteria(searchCriteria = withCapture());
            Assert.assertEquals(vehicleName, searchCriteria.getVehicleName());
            Assert.assertEquals(Integer.valueOf(500), searchCriteria.getLimit());
        }};

        List<VehiclePositionDTO> actualResponse = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<VehiclePositionDTO>>(){});
        Assert.assertEquals(2, actualResponse.size());
    }

    private VehiclePosition getMockVehiclePosition(String vehicleName) {
        VehiclePosition vehiclePosition = new VehiclePosition();
        vehiclePosition.setId(UUID.randomUUID());
        vehiclePosition.setVehicleName(vehicleName);
        vehiclePosition.setTs(new Date());
        return vehiclePosition;
    }
}
