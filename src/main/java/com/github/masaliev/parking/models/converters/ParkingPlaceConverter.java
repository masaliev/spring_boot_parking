package com.github.masaliev.parking.models.converters;

import com.github.masaliev.parking.models.dto.ParkingPlace;
import com.github.masaliev.parking.models.entity.ParkingPlaceEntity;
import org.springframework.core.convert.converter.Converter;

public class ParkingPlaceConverter implements Converter<ParkingPlaceEntity, ParkingPlace> {
    @Override
    public ParkingPlace convert(ParkingPlaceEntity source) {
        return new ParkingPlace(source.getId(), source.getName(), source.getStatus());
    }
}
