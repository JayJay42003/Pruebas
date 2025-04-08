package com.practica1.model;

import java.time.LocalDate;

public abstract class Vehicle implements Comparable<Vehicle>{
    private String brand, model,licensePlate;
    private LocalDate year;
    private FuelType typeFuel;

    //Constructor base
    public Vehicle() {
    }

    public Vehicle(String licensePlate, String brand, String model, LocalDate year, String typeFuel) {
        this.licensePlate=licensePlate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.typeFuel = FuelType.valueOf(typeFuel);
    }


    //Getters variables base
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getYear() {
        return year;
    }

    public FuelType getTypeFuel() {
        return typeFuel;
    }
    /////

    public void accelerate(){
        System.out.println("El vehiculo esta acelerando");
    }

    public void brake(){
        System.out.println("El vehiculo esta frenando");
    }

    //Enseñar atributos de la clase.
    public abstract void showInfo();

}


