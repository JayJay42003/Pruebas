package com.practica1.model;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DatabaseConnection {
    private static final String URL = "jdbc:h2:~/concesionario_db"; // Modo archivo (persistente)
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private Connection H2_Connection;

    public  Connection getH2_Connection() {
        return H2_Connection;
    }

    public void setH2_Connection(Connection h2_Connection) {
        H2_Connection = h2_Connection;
    }

    @PostConstruct
    public void initializeConnection(){
        try {
            H2_Connection= DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Conexión realizada");

            //String licensePlate,String brand, String model, LocalDate year, String typeFuel, int doorsNum
            // int cylinderCapacity
            //private ArrayList<Vehicle> vehiclesArrayList,private HashMap<String,Vehicle> vehiclesHashMap,private Set<String> brandSet;
            //Crear vehiculo
            H2_Connection.createStatement().execute("CREATE TABLE IF NOT EXISTS vehicle (idVehicle int NOT NULL AUTO_INCREMENT, vehicleType varchar(50) NOT NULL)");
            //Crear coche
            H2_Connection.createStatement().execute("CREATE TABLE IF NOT EXISTS car (idVehicle int NOT NULL AUTO_INCREMENT, idConcessionaire int NOT NULL, licensePlate varchar(50), brand varchar(50)," +
                    " model varchar(50), yearCreated date, typeFuel varchar(50), doorsNum int)");
            //Crear moto
            H2_Connection.createStatement().execute("CREATE TABLE IF NOT EXISTS motorcycle (idVehicle int NOT NULL AUTO_INCREMENT, idConcessionaire int NOT NULL, licensePlate varchar(50), brand varchar(50),"  +
                    " model varchar(50), yearCreated date, typeFuel varchar(50), cylinderCapacity int )");
            //Crear concesionario
            H2_Connection.createStatement().execute("CREATE TABLE IF NOT EXISTS concessionaire (idConcessionaire int NOT NULL AUTO_INCREMENT, name varchar(50), numVehicles int)");

        } catch (SQLException e) {
           e.printStackTrace();
        }

    }

    public void closeConnection(){
        try {
            H2_Connection.close();
            if(H2_Connection.isClosed()){
                System.out.println("Conexion cerrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertInfo(){
        //Insercion de datos si no existe
        try {
            H2_Connection.createStatement().execute("INSERT INTO VEHICLE VALUES (1,'car'),(2,'motorcycle')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
