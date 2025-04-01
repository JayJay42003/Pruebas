package com.practica1.model;

import java.time.LocalDate;

public class Motorcycle extends Vehicle {
    private int cylinderCapacity;

    //Constructor de moto
    public Motorcycle(String brand, String model, LocalDate year, String typeFuel, int cylinderCapacity) {
        super(brand, model, year, typeFuel);
        this.cylinderCapacity = cylinderCapacity;
    }

    //Getter cilindrada
    public int getCylinderCapacity() {
        return cylinderCapacity;
    }

    //Mostrar la informacion de las variables de este objeto
    @Override
    public void showInfo() {
        System.out.println("Marca:"+ getBrand()+" Modelo:"+ getModel()+" Año:"+ getYear()+
                " Tipo Combustible:"+ getTypeFuel().name()+" Cilindrada:"+ getCylinderCapacity());
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
}
