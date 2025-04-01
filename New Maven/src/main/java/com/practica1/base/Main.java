package com.practica1.base;

import com.practica1.model.Car;
import com.practica1.model.Motorcycle;
import com.practica1.model.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Corolla", LocalDate.of(2020, 5, 10), "GASOLINE",4);
        Motorcycle motorcycle = new Motorcycle("Harley-Davidson", "Sportster", LocalDate.of(2018, 8, 15), "GASOLINE",150);

        car.accelerate();
        car.brake();
        car.showInfo();

        motorcycle.accelerate();
        motorcycle.brake();
        motorcycle.showInfo();

        List<Vehicle> vehiculos=new ArrayList<>();
        vehiculos.add(car);
        vehiculos.add(motorcycle);
        for (Vehicle v:vehiculos){
            v.showInfo();
        };
    }
}