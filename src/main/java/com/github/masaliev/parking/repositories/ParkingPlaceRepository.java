package com.github.masaliev.parking.repositories;

import com.github.masaliev.parking.models.entity.ParkingPlaceEntity;
import org.springframework.data.repository.CrudRepository;

public interface ParkingPlaceRepository extends CrudRepository<ParkingPlaceEntity, Long> {
}
