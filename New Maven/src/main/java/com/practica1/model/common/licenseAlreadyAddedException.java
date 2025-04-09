package com.practica1.model.common;

public class licenseAlreadyAddedException extends Exception {
    public licenseAlreadyAddedException(String licensePlate) {
        super("La matricula "+licensePlate+" ya fue añadida");
    }
}
