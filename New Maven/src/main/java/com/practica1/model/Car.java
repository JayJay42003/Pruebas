package com.practica1.model;

import java.time.LocalDate;

public class Car extends Vehicle{
    private int doorsNum;

    //Constructor de coche
    public Car(String brand, String model, LocalDate year, String typeFuel, int doorsNum) {
        super(brand, model, year, typeFuel);
        this.doorsNum = doorsNum;
    }

    //Getter numero de puertas
    public int getDoorsNum() {
        return doorsNum;
    }

    //Mostrar la informacion de las variables de este objeto
    @Override
    public void showInfo() {
        System.out.println("Marca:"+ getBrand()+" Modelo:"+ getModel()+" Año:"+ getYear()+
                " Tipo Combustible:"+ getTypeFuel().name()+" Numero Puertas:"+ getDoorsNum());
    }

    //Imprimir un mensaje que indica que la coche acelera
    @Override
    public void accelerate() {
        System.out.println("El coche esta acelerando");
    }

    //Imprimir un mensaje que indica que la coche frena
    @Override
    public void brake() {
        System.out.println("El coche esta frenando");
    }
}
