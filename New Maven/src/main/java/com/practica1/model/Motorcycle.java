package com.practica1.model;

import java.time.LocalDate;

public class Motorcycle extends Vehicle {
    private int cylinderCapacity;


    //Constructor de moto
    public Motorcycle() {
    }

    public Motorcycle(int idVehicle, int idConcessionaire, String licensePlate, String brand, String model, LocalDate year, String typeFuel, int cylinderCapacity) {
        super(idVehicle,idConcessionaire,licensePlate, brand, model, year, typeFuel);
        this.cylinderCapacity = cylinderCapacity;
    }

    public Motorcycle(String licensePlate, String brand, String model, LocalDate year, String typeFuel, int cylinderCapacity) {
        super(licensePlate, brand, model, year, typeFuel);
        this.cylinderCapacity = cylinderCapacity;
    }

    //Getter cilindrada
    public int getCylinderCapacity() {
        return cylinderCapacity;
    }

    //Mostrar la informacion de las variables de este objeto
    @Override
    public void showInfo() {
        try {
            System.out.println("Matricula:" + getLicensePlate() + " Marca:" + getBrand() + " Modelo:" + getModel() + " Año:" + getYear() +
                    " Tipo Combustible:" + getTypeFuel().name() + " Cilindrada:" + getCylinderCapacity());
        } catch (NullPointerException nullP) {
            System.out.println("El objeto es nulo");
        }

    }

    //Imprimir un mensaje que indica que la moto acelera
    @Override
    public void accelerate() {
        System.out.println("La moto esta acelerando");
    }

    //Imprimir un mensaje que indica que la moto frena
    @Override
    public void brake() {
        System.out.println("La moto esta frenando");
    }

    @Override
    public int compareTo(Vehicle o) {
        return this.getBrand().compareTo(o.getBrand());
    }
}
