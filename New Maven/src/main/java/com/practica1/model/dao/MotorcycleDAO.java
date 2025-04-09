package com.practica1.model.dao;

import com.practica1.model.DatabaseConnection;
import com.practica1.model.Motorcycle;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MotorcycleDAO {
    private DatabaseConnection databaseConnection;

    public MotorcycleDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    //(idVehicle int, idConcessionaire int, licensePlate varchar2(50), brand varchar2(50),"  +
    //  " model varchar2(50), year date, typeFuel varchar2(50), cylinderCapacity int
    public void createObject(Motorcycle motorcycle) {
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
                System.out.println("Datos insertados en tabla moto");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Motorcycle readObject(String license) {
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

    public void updateObject(int id,Motorcycle motorcycle) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("UPDATE motorcycle SET licensePlate=?,brand=?,model=?, yearCreated=?, typeFuel=?, cylinderCapacity=? WHERE idVehicle=?");
            ps.setString(1,motorcycle.getLicensePlate());
            ps.setString(2,motorcycle.getBrand());
            ps.setString(3,motorcycle.getModel());
            ps.setDate(4,Date.valueOf(motorcycle.getYear()));
            ps.setString(5,motorcycle.getTypeFuel().name());
            ps.setInt(6,motorcycle.getCylinderCapacity());
            ps.setInt(7,id);
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Moto con id="+id+" actualizada");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteObject(int id) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("DELETE FROM motorcycle WHERE idVehicle=?");
            ps.setInt(1,id);
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Moto con id="+id+" borrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
