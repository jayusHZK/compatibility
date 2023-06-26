package com.jayus.mapstruct.test;

import lombok.Data;

@Data
public class Car {

    private String make;

    private int numberOfSeats;

    private CarType type;

    public Car(String make, int numberOfSeats, CarType type) {
        this.make = make;
        this.numberOfSeats = numberOfSeats;
        this.type = type;
    }

    static enum CarType {
        SEDAN
    }

}
