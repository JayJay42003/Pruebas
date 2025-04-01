package com.practica1.base;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hola Mundo");
        
        try {
            FileWriter fw=new FileWriter("nuevo.txt");
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}