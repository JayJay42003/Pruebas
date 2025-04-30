package com.practica1.model;

import java.time.LocalDate;

public class Car extends Vehicle{
    private int doorsNum;

    //Constructor de coche
    public Car() {
    }

    public Car(int idVehicle, int idConcessionaire, String licensePlate, String brand, String model, LocalDate year, String typeFuel, int doorsNum) {
        super(idVehicle,idConcessionaire,licensePlate, brand, model, year, typeFuel);
        this.doorsNum = doorsNum;
    }

    public Car(String licensePlate, String brand, String model, LocalDate year, String typeFuel, int doorsNum) {
        super(licensePlate, brand, model, year, typeFuel);
        this.doorsNum = doorsNum;
    }

    //Getter numero de puertas
    public int getDoorsNum() {
        return doorsNum;
    }



    //Mostrar la informacion de las variables de este objeto
    @Override
    public void showInfo() {
        try {
            System.out.println("Matricula:" + getLicensePlate() + " Marca:" + getBrand() + " Modelo:" + getModel() + " Año:" + getYear() +
                    " Tipo Combustible:" + getTypeFuel().name() + " Numero Puertas:" + getDoorsNum());
        }catch(NullPointerException nullP){
            System.out.println("El objeto es nulo");
        }
    }

    //Imprimir un mensaje que indica que el coche acelera
    @Override
    public void accelerate() {
        System.out.println("El coche esta acelerando");
    }

    //Imprimir un mensaje que indica que el coche frena
    @Override
    public void brake() {
        System.out.println("El coche esta frenando");
    }

    @Override
    public int compareTo(Vehicle o) {
        return this.getBrand().compareTo(o.getBrand());
    }
}
