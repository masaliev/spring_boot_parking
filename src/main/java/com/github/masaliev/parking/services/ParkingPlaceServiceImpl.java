package com.github.masaliev.parking.services;

import com.github.masaliev.parking.models.dto.ParkingPlace;
import com.github.masaliev.parking.models.enums.ParkingPlaceStatus;
import com.github.masaliev.parking.persistence.ParkingPlaceStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ParkingPlaceServiceImpl implements ParkingPlaceService {
    private final ParkingPlaceStorage storage;

    @Override
    public void changeStatus(long id, ParkingPlaceStatus status) {
        storage.changeStatus(id, status);
    }

    @Override
    public List<ParkingPlace> getAvailablePlaces() {
        return storage.getAvailablePlaces();
    }

    @Override
    public boolean hasAvailablePlace() {
        return storage.hasAvailablePlace();
    }
}
