package com.practica1.model;

public class licenseNotAddedException extends Exception {
    public licenseNotAddedException(String licensePlate) {
        super("No se ha encontrado la matricula: "+licensePlate);
    }
}
