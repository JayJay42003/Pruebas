package com.practica1.model;

import com.practica1.model.common.FuelType;

import java.time.LocalDate;

public abstract class Vehicle implements Comparable<Vehicle>{
    private String brand, model,licensePlate;
    private LocalDate year;
    private FuelType typeFuel;
    private int idVehicle, idConcessionaire;

    //Constructor base
    public Vehicle() {
    }

    public Vehicle(int idVehicle,int idConcessionaire,String licensePlate, String brand, String model, LocalDate year, String typeFuel) {
        this.idVehicle=idVehicle;
        this.idConcessionaire=idConcessionaire;
        this.licensePlate=licensePlate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.typeFuel = FuelType.valueOf(typeFuel);
    }

    public Vehicle(String licensePlate, String brand, String model, LocalDate year, String typeFuel) {
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public void setTypeFuel(FuelType typeFuel) {
        this.typeFuel = typeFuel;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public void setIdConcessionaire(int idConcessionaire) {
        this.idConcessionaire = idConcessionaire;
    }



    //Getters variables base

    public int getIdVehicle() {
        return idVehicle;
    }

    public int getIdConcessionaire() {
        return idConcessionaire;
    }

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


