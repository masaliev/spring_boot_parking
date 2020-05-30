package com.github.masaliev.parking.persistence;

import com.github.masaliev.parking.exceptions.InvalidPlaceIdException;
import com.github.masaliev.parking.models.converters.ParkingPlaceConverter;
import com.github.masaliev.parking.models.dto.ParkingPlace;
import com.github.masaliev.parking.models.entity.ParkingPlaceEntity;
import com.github.masaliev.parking.models.enums.ParkingPlaceStatus;
import com.github.masaliev.parking.repositories.ParkingPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class ParkingPlaceStorageImpl implements ParkingPlaceStorage {
    private final ParkingPlaceRepository repository;
    private final ParkingPlaceConverter converter;

    @Override
    public void changeStatus(long id, ParkingPlaceStatus status) {
        ParkingPlaceEntity parkingPlaceEntity = repository.findById(id).orElseThrow(InvalidPlaceIdException::new);
        parkingPlaceEntity.setStatus(status);
        repository.save(parkingPlaceEntity);
    }

    @Override
    public List<ParkingPlace> getAvailablePlaces() {
        return StreamSupport.stream(repository.findAllByStatus(ParkingPlaceStatus.AVAILABLE).spliterator(), false)
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasAvailablePlace() {
        return repository.getAvailablePlaceCountByStatus(ParkingPlaceStatus.AVAILABLE) > 0;
    }
}
