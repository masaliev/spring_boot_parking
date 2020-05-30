package com.github.masaliev.parking.persistence;

import com.github.masaliev.parking.models.dto.ParkingPlace;
import com.github.masaliev.parking.models.enums.ParkingPlaceStatus;

import java.util.List;

public interface ParkingPlaceStorage {
    void changeStatus(long id, ParkingPlaceStatus status);

    List<ParkingPlace> getAvailablePlaces();

    boolean hasAvailablePlace();
}
