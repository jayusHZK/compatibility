package com.jayus.mapstruct.test;

public class test {

    public static void main(String[] args) {
        Car car = new Car("Morris", 5, Car.CarType.SEDAN);
        //CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);
        //System.out.println(carDto);
    }

}
