package com.github.masaliev.parking.models.entity;

import com.github.masaliev.parking.models.enums.ParkingPlaceStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ParkingPlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ParkingPlaceStatus status;
}
