package com.github.masaliev.parking.models.dto;

import com.github.masaliev.parking.models.enums.ParkingPlaceStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ParkingPlace {
    private final long id;

    private final String name;

    private final ParkingPlaceStatus status;
}
