package com.practica1.model;

import java.util.*;

public class Concesionario {
    private ArrayList<Vehicle> vehiclesArrayList;
    private HashMap<String,Vehicle> vehiclesHashMap;
    private Set<String> brandSet;

    public Concesionario() {
        this.vehiclesArrayList = new ArrayList<>();
        this.vehiclesHashMap = new HashMap<>();
        this.brandSet = new HashSet<>();
    }

    //Añade el vehículo en las 3 colecciones diferentes de ArrayList,HashMap y Set
    public void addVehicle(Vehicle vehicle){
        vehiclesArrayList.add(vehicle);
        vehiclesHashMap.put(vehicle.getLicensePlate(),vehicle);
        brandSet.add(vehicle.getBrand());

        System.out.println("Vehiculo añadido al concesionario");
    }

    //Busca un vehículo por su matricula
    public Vehicle findVehicleByLicensePlate(String licensePlate){
        return vehiclesHashMap.get(licensePlate);
    }

    //Da una lista sin duplicados de las matriculas, sin acceder directamente a la misma
    public Set<String> getAllLicensePlates(){
        return new HashSet<>(brandSet);
    }

    //Busca un conjunto de vehiculos de la clase dada
    public List<Vehicle> filterByType(Class<?> tipo){
        List<Vehicle> vehiclesReturned=new ArrayList<>();

        for (Vehicle v:vehiclesArrayList){
            if (tipo.equals(Motorcycle.class) && v instanceof Motorcycle){
                vehiclesReturned.add(v);
            }else if(tipo.equals(Car.class) && v instanceof Car){
                vehiclesReturned.add(v);
            }
        }

        return vehiclesReturned;
    }

    //Borra las instancias del vehículo que tiene esa matrícula
    public void deleteByLicensePlate(String licensePlate){
        Vehicle v=vehiclesHashMap.remove(licensePlate);
        vehiclesArrayList.remove(v);
        brandSet.remove(v.getLicensePlate());
    }
}
