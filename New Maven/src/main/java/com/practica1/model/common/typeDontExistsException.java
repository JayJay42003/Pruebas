package com.practica1.model.common;

public class typeDontExistsException extends Exception {
    public typeDontExistsException(Class<?> tipo){
        super("Este tipo "+tipo.getName()+" no es valido");
    }
}
