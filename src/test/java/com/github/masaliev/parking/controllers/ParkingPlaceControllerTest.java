package com.github.masaliev.parking.controllers;

import com.github.masaliev.parking.exceptions.InvalidPlaceIdException;
import com.github.masaliev.parking.models.dto.ParkingPlace;
import com.github.masaliev.parking.models.enums.ParkingPlaceStatus;
import com.github.masaliev.parking.services.ParkingPlaceService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ParkingPlaceController.class)
class ParkingPlaceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ParkingPlaceService service;

    @Test
    void givenEmptyPostBody_whenChangeStatus_thenReturnsBadRequest() throws Exception {
        mvc.perform(post("/api/v1/parking-place/change-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.id", is("id cannot be empty")))
                .andExpect(jsonPath("$.status", is("status cannot be empty")));
    }

    @Test
    void givenEmptyId_whenChangeStatus_thenReturnsBadRequest() throws Exception {
        mvc.perform(post("/api/v1/parking-place/change-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"status\": \"AVAILABLE\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.id", is("id cannot be empty")));
    }

    @Test
    void givenEmptyStatus_whenChangeStatus_thenReturnsBadRequest() throws Exception {
        mvc.perform(post("/api/v1/parking-place/change-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"1\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("status cannot be empty")));
    }

    @Test
    void givenInvalidStatus_whenChangeStatus_thenReturnsBadRequest() throws Exception {
        mvc.perform(post("/api/v1/parking-place/change-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"1\", \"status\": \"INVALID_STATUS\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("status must be one of [\"AVAILABLE\", \"BUSY\"]")));
    }


    @Test
    void givenInvalidId_whenChangeStatus_thenReturnsBadRequest() throws Exception {
        doThrow(new InvalidPlaceIdException()).when(service).changeStatus(anyLong(), any());
        mvc.perform(post("/api/v1/parking-place/change-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"100\", \"status\": \"BUSY\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid place id"));
    }

    @Test
    void givenCorrectParams_whenChangeStatus_thenReturnsOkResult() throws Exception {
        doNothing().when(service).changeStatus(anyLong(), any());
        mvc.perform(post("/api/v1/parking-place/change-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"1\", \"status\": \"BUSY\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("success")));
    }

    @Test
    void givenNoAvailablePlaces_whenGetAvailablePlaces_thenReturnsEmptyList() throws Exception {
        when(service.getAvailablePlaces()).thenReturn(Collections.emptyList());
        mvc.perform(get("/api/v1/parking-place/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void givenOneAvailablePlace_whenGetAvailablePlaces_thenReturnsListWithOneElement() throws Exception {
        ParkingPlace parkingPlace = new ParkingPlace(1, "A-1", ParkingPlaceStatus.AVAILABLE);
        when(service.getAvailablePlaces()).thenReturn(Collections.singletonList(parkingPlace));
        mvc.perform(get("/api/v1/parking-place/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void givenMoreAvailablePlaces_whenGetAvailablePlaces_thenReturnsList() throws Exception {
        List<ParkingPlace> result = Arrays.asList(new ParkingPlace(1, "A-2", ParkingPlaceStatus.AVAILABLE),
                new ParkingPlace(2, "A-2", ParkingPlaceStatus.AVAILABLE),
                new ParkingPlace(3, "A-3", ParkingPlaceStatus.AVAILABLE));

        when(service.getAvailablePlaces()).thenReturn(result);

        mvc.perform(get("/api/v1/parking-place/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void givenHasAvailablePlace_whenGetAvailablePlaces_thenReturnsCorrectObjectList() throws Exception {
        ParkingPlace parkingPlace = new ParkingPlace(1, "A-1", ParkingPlaceStatus.AVAILABLE);
        when(service.getAvailablePlaces()).thenReturn(Collections.singletonList(parkingPlace));
        mvc.perform(get("/api/v1/parking-place/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("A-1")))
                .andExpect(jsonPath("$[0].status", is("AVAILABLE")));
    }

    @Test
    void givenNoAvailablePlaces_whenHasAvailablePlace_thenReturnsFalse() throws Exception {
        when(service.hasAvailablePlace()).thenReturn(false);
        mvc.perform(get("/api/v1/parking-place/has-available/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    void givenHasAvailablePlace_whenHasAvailablePlace_thenReturnsTrue() throws Exception {
        when(service.hasAvailablePlace()).thenReturn(true);
        mvc.perform(get("/api/v1/parking-place/has-available/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}