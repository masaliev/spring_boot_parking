package com.github.masaliev.parking.models.dto;


import com.github.masaliev.parking.models.enums.ParkingPlaceStatus;
import com.github.masaliev.parking.utils.ValueOfEnum;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class ChangeParkingPlaceStatusRequest {

    @NotNull(message = "id cannot be empty")
    @Getter
    private Long id;

    @NotNull(message = "status cannot be empty")
    @ValueOfEnum(enumClass = ParkingPlaceStatus.class, message = "status must be one of [\"AVAILABLE\", \"BUSY\"]")
    private String status;

    public ParkingPlaceStatus getStatus() {
        return ParkingPlaceStatus.valueOf(this.status);
    }
}
