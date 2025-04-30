package com.practica1.service.dao;

import com.practica1.model.Car;
import com.practica1.model.DatabaseConnection;
import com.practica1.model.Motorcycle;
import com.practica1.model.Vehicle;
import com.practica1.model.common.FuelType;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MotorcycleDao {
    private DatabaseConnection databaseConnection;

    public MotorcycleDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    //(idVehicle int, idConcessionaire int, licensePlate varchar2(50), brand varchar2(50),"  +
    //  " model varchar2(50), year date, typeFuel varchar2(50), cylinderCapacity int
    public String createObject(Motorcycle motorcycle) {
        try {
            PreparedStatement ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO motorcycle(idConcessionaire, licensePlate,brand," +
                    "model,yearCreated,typeFuel,cylinderCapacity) VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1,motorcycle.getIdConcessionaire());
            ps.setString(2,motorcycle.getLicensePlate());
            ps.setString(3,motorcycle.getBrand());
            ps.setString(4,motorcycle.getModel());
            ps.setDate(5, Date.valueOf(motorcycle.getYear()));
            ps.setString(6,motorcycle.getTypeFuel().name());
            ps.setInt(7,motorcycle.getCylinderCapacity());
            if(ps.executeUpdate()!=0){
                return "Datos insertados en tabla moto";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Motorcycle readObject(int id) {
        Motorcycle motorcycle=new Motorcycle();
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("SELECT * FROM motorcycle WHERE id=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while ((rs.next())) {
                motorcycle = new Motorcycle(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6).toLocalDate(),rs.getString(7),rs.getInt(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return motorcycle;
    }

    public Motorcycle readObjectByLicense(String license) {
        Motorcycle motorcycle=new Motorcycle();
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("SELECT * FROM motorcycle WHERE licensePlate=?");
            ps.setString(1,license);
            ResultSet rs = ps.executeQuery();
            while ((rs.next())) {
                motorcycle = new Motorcycle(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6).toLocalDate(),rs.getString(7),rs.getInt(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return motorcycle;
    }

    public List<Motorcycle> readAll() {
        ArrayList<Motorcycle> motorcycles=new ArrayList<>();
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("SELECT * FROM motorcycle");
            ResultSet rs = ps.executeQuery();
            while ((rs.next())) {
                motorcycles.add(new Motorcycle(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6).toLocalDate(),rs.getString(7),rs.getInt(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return motorcycles;
    }

    public String updateObject(Motorcycle motorcycle) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("UPDATE motorcycle SET licensePlate=?,brand=?,model=?, yearCreated=?, typeFuel=?, cylinderCapacity=? WHERE idVehicle=?");
            ps.setString(1,motorcycle.getLicensePlate());
            ps.setString(2,motorcycle.getBrand());
            ps.setString(3,motorcycle.getModel());
            ps.setDate(4,Date.valueOf(motorcycle.getYear()));
            ps.setString(5,motorcycle.getTypeFuel().name());
            ps.setInt(6,motorcycle.getCylinderCapacity());
            ps.setInt(7,motorcycle.getIdVehicle());
            int rows=ps.executeUpdate();
            if(rows>0){
                return "Moto con id="+motorcycle.getIdVehicle()+" actualizada";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String deleteObject(int id) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("DELETE FROM motorcycle WHERE idVehicle=?");
            ps.setInt(1,id);
            int rows=ps.executeUpdate();
            if(rows>0){
                return "Moto con id="+id+" borrado";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    public List<Motorcycle> filter(Motorcycle vehicle){
        List<Motorcycle> vehicles = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM motorcycle WHERE 1=1");
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
                Motorcycle v = new Motorcycle();
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
