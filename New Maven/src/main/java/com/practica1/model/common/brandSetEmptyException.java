package com.practica1.model.common;

public class brandSetEmptyException extends Exception {
    public brandSetEmptyException(){
        super("No hay marcas registradas en la coleccion");
    }
}
