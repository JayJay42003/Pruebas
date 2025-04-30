package com.practica1.service.dao;

import com.practica1.model.Car;
import com.practica1.model.DatabaseConnection;
import com.practica1.model.Vehicle;
import com.practica1.model.common.FuelType;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarDao {
    private DatabaseConnection databaseConnection;

    public CarDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    //idConcessionaire int NOT NULL,String licensePlate, String brand, String model, LocalDate year, String typeFuel, int doorsNum
    public String createObject(Car car) {
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
                return "Datos insertados en tabla coche";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
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

    public String updateObject(Car car) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("UPDATE car SET licensePlate=?,brand=?,model=?, yearCreated=?, typeFuel=?, doorsNum=? WHERE idVehicle=?");
            ps.setString(1,car.getLicensePlate());
            ps.setString(2,car.getBrand());
            ps.setString(3,car.getModel());
            ps.setDate(4,Date.valueOf(car.getYear()));
            ps.setString(5,car.getTypeFuel().name());
            ps.setInt(6,car.getDoorsNum());
            ps.setInt(7,car.getIdVehicle());
            int rows=ps.executeUpdate();
            if(rows>0){
               return "Coche con id="+car.getIdVehicle()+" actualizado";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String updateObjectbyLicense(String license,Car car) {
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
                return "Coche con matricula="+license+" actualizado";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String deleteObject(int id) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("DELETE FROM car WHERE idVehicle=?");
            ps.setInt(1,id);
            int rows=ps.executeUpdate();
            if(rows>0){
                return "Coche con id="+id+" borrado";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String deleteObjectbyLicense(String license) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("DELETE FROM car WHERE licensePlate=?");
            ps.setString(1,license);
            int rows=ps.executeUpdate();
            if(rows>0){
                return "Coche con matricula="+license+" borrado";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<Car> filter(Car vehicle){
        List<Car> vehicles = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM car WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (vehicle.getLicensePlate() != null) {
            sql.append(" AND licensePlate = ?");
            params.add(vehicle.getLicensePlate());
        }
        if (vehicle.getBrand() != null) {
            sql.append(" AND brand = ?");
            params.add(vehicle.getBrand());
        }
        if (vehicle.getIdVehicle() != 0) {
            sql.append(" AND idVehicle = ?");
            params.add(vehicle.getIdVehicle());
        }
        if (vehicle.getIdConcessionaire() != 0) {
            sql.append(" AND idConcessionaire = ?");
            params.add(vehicle.getIdConcessionaire());
        }
        if (vehicle.getModel() != null) {
            sql.append(" AND model = ?");
            params.add(vehicle.getModel());
        }
        if (vehicle.getYear() != null) {
            sql.append(" AND yearCreated = ?");
            params.add(vehicle.getYear());
        }
        if (vehicle.getTypeFuel() != null) {
            sql.append(" AND typeFuel = ?");
            params.add(vehicle.getTypeFuel());
        }

        try (PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Car v = new Car();
                v.setIdVehicle(rs.getInt("idVehicle"));
                v.setIdConcessionaire(rs.getInt("idConcessionaire"));
                v.setLicensePlate(rs.getString("licensePlate"));
                v.setBrand(rs.getString("brand"));
                v.setModel(rs.getString("model"));
                v.setYear(rs.getDate("yearCreated").toLocalDate());
                v.setTypeFuel(FuelType.valueOf(rs.getString("typeFuel")));

                vehicles.add(v);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;
    }
}
