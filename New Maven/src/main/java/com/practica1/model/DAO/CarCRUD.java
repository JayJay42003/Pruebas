package com.practica1.model.DAO;

import com.practica1.model.Car;
import com.practica1.model.DatabaseConnection;
import com.practica1.model.Motorcycle;
import com.practica1.model.Vehicle;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarCRUD  {
    private DatabaseConnection databaseConnection;

    public CarCRUD(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    //idConcessionaire int NOT NULL,String licensePlate, String brand, String model, LocalDate year, String typeFuel, int doorsNum
    public void createObject(Car car) {
        try {
            PreparedStatement ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO car(idConcessionaire, licensePlate,brand," +
                    "model,yearCreated,typeFuel,doorsNum) VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1,car.getIdConcessionaire());
            ps.setString(2,car.getLicensePlate());
            ps.setString(3,car.getBrand());
            ps.setString(4,car.getModel());
            ps.setDate(5, Date.valueOf(car.getYear()));
            ps.setString(6,car.getTypeFuel().name());
            ps.setInt(7,car.getDoorsNum());
            if(ps.executeUpdate()!=0){
                System.out.println("Datos insertados en tabla coche");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Car readObject(int id) {
        Car car=null;
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("SELECT * FROM car WHERE idVehicle=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while ((rs.next())) {
                car = new Car(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6).toLocalDate(),rs.getString(7),rs.getInt(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    public Car readObjectbyLicense(String license) {
        Car car=new Car();
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("SELECT * FROM car WHERE licensePlate=?");
            ps.setString(1,license);
            ResultSet rs = ps.executeQuery();
            while ((rs.next())) {
                car = new Car(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6).toLocalDate(),rs.getString(7),rs.getInt(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    public List<Car> readAll() {
        ArrayList<Car> cars=new ArrayList<>();
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("SELECT * FROM car");
            ResultSet rs = ps.executeQuery();
            while ((rs.next())) {
                cars.add(new Car(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6).toLocalDate(),rs.getString(7),rs.getInt(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public List<Car> readAllbyConcessionaire(String concName) {
        ArrayList<Car> cars=new ArrayList<>();
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("SELECT * FROM car where idConcessionaire=(select idConcessionaire from concessionaire where name=?)");
            ps.setString(1,concName);
            ResultSet rs = ps.executeQuery();
            while ((rs.next())) {
                cars.add(new Car(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6).toLocalDate(),rs.getString(7),rs.getInt(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public void updateObject(int id,Car car) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("UPDATE car SET licensePlate=?,brand=?,model=?, yearCreated=?, typeFuel=?, doorsNum=? WHERE idVehicle=?");
            ps.setString(1,car.getLicensePlate());
            ps.setString(2,car.getBrand());
            ps.setString(3,car.getModel());
            ps.setDate(4,Date.valueOf(car.getYear()));
            ps.setString(5,car.getTypeFuel().name());
            ps.setInt(6,car.getDoorsNum());
            ps.setInt(7,id);
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Coche con id="+id+" actualizado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateObjectbyLicense(String license,Car car) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("UPDATE car SET licensePlate=?,brand=?,model=?, yearCreated=?, typeFuel=?, doorsNum=? WHERE licensePlate=?");
            ps.setString(1,car.getLicensePlate());
            ps.setString(2,car.getBrand());
            ps.setString(3,car.getModel());
            ps.setDate(4,Date.valueOf(car.getYear()));
            ps.setString(5,car.getTypeFuel().name());
            ps.setInt(6,car.getDoorsNum());
            ps.setString(7,license);
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Coche con matricula="+license+" actualizado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteObject(int id) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("DELETE FROM car WHERE idVehicle=?");
            ps.setInt(1,id);
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Coche con id="+id+" borrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteObjectbyLicense(String license) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("DELETE FROM car WHERE licensePlate=?");
            ps.setString(1,license);
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Coche con matricula="+license+" borrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
