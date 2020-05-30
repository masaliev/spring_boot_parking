package com.github.masaliev.parking.repositories;

import com.github.masaliev.parking.models.entity.ParkingPlaceEntity;
import com.github.masaliev.parking.models.enums.ParkingPlaceStatus;
import org.springframework.data.repository.CrudRepository;

public interface ParkingPlaceRepository extends CrudRepository<ParkingPlaceEntity, Long> {
    long getAvailablePlaceCountByStatus(ParkingPlaceStatus status);

    Iterable<ParkingPlaceEntity> findAllByStatus(ParkingPlaceStatus status);
}
