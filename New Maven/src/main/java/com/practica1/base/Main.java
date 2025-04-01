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

        //Prueba del coche
        car.accelerate();
        car.brake();
        car.showInfo();

        //Prueba de la moto
        motorcycle.accelerate();
        motorcycle.brake();
        motorcycle.showInfo();

        //Coleccion de vehiculos con su informacion
        List<Vehicle> vehicles=new ArrayList<>();
        vehicles.add(car);
        vehicles.add(motorcycle);
        for (Vehicle v:vehicles){
            v.showInfo();
        };
    }
}