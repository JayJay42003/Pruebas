package com.practica1.base;

import com.practica1.model.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        /*Car car = new Car("Toyota", "Corolla", LocalDate.of(2020, 5, 10), "GASOLINE",4);
        //Motorcycle motorcycle = new Motorcycle("Harley-Davidson", "Sportster", LocalDate.of(2018, 8, 15), "GASOLINE",150);

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
        };*/

        //En el main, usar los métodos para añadir vehiculos de distinto tipo.
        //
        //Mostrar la informacion de los vehiculos utilizando los metodos implementados en el punto anterior.
        //
        //Usar TreeSet para ordenar por marca y volver a mostrar todos los vehiculos ordenados.

        Concesionario concesionario1=new Concesionario();
        concesionario1.addVehicle(new Car("123456XYZ","Mercedes","CLA Coupé",LocalDate.of(2004,6,23), FuelType.HYBRID.name(), 4));
        concesionario1.addVehicle(new Motorcycle("22361BVC","KTM","125 Duke",LocalDate.of(2012,10,3),FuelType.GASOLINE.name(),125));
        concesionario1.addVehicle(new Motorcycle("22363BVC", "Yamaha", "R1", LocalDate.of(2020, 3, 5), FuelType.HYBRID.name(), 998));
        concesionario1.addVehicle(new Motorcycle("22364BVC", "Suzuki", "GSX-R1000", LocalDate.of(2019, 8, 18), FuelType.GASOLINE.name(), 999));
        concesionario1.addVehicle(new Car("1617181920PQR", "Volkswagen", "Golf", LocalDate.of(2018, 9, 9), FuelType.ELECTRIC.name(), 5));

        /*
        System.out.println("Encontrar vehiculo por matricula:");
        concesionario1.findVehicleByLicensePlate("123456XYZ").showInfo();
        System.out.println("Encontrar vehiculos por tipo:");
        for (Vehicle v:concesionario1.filterByType(Car.class)){
            v.showInfo();
        }
        System.out.println("Enseñar todas las matriculas:");
        for (String str:concesionario1.getAllLicensePlates()){
            System.out.println(str);
        }
        */


        TreeSet<Vehicle> vehicleTreeSet=new TreeSet<>();
        vehicleTreeSet.addAll(concesionario1.filterByType(Car.class));
        vehicleTreeSet.addAll(concesionario1.filterByType(Motorcycle.class));

        for(Vehicle v:vehicleTreeSet){
            v.showInfo();
        }
    }
}