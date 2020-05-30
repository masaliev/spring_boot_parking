package com.github.masaliev.parking.controllers;

import com.github.masaliev.parking.exceptions.ValidationException;
import com.github.masaliev.parking.models.dto.ChangeParkingPlaceStatusRequest;
import com.github.masaliev.parking.models.dto.ParkingPlace;
import com.github.masaliev.parking.services.ParkingPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/parking-place")
public class ParkingPlaceController {
    private final ParkingPlaceService service;

    @PostMapping("/change-status")
    public Map<String, String> changeStatus(@RequestBody @Valid ChangeParkingPlaceStatusRequest changeParkingPlaceStatusRequest,
                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        service.changeStatus(changeParkingPlaceStatusRequest.getId(), changeParkingPlaceStatusRequest.getStatus());

        return Collections.singletonMap("status", "success");
    }

    @GetMapping("/")
    public List<ParkingPlace> getAvailablePlaces() {
        return service.getAvailablePlaces();
    }

    @GetMapping("has-available")
    public boolean hasAvailablePlace() {
        return service.hasAvailablePlace();
    }
}
