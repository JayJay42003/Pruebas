package com.practica1.service.dao;

import com.practica1.model.DatabaseConnection;
import com.practica1.model.VehicleClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDao {
    private DatabaseConnection databaseConnection;

    public VehicleDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    //(idVehicle int NOT NULL AUTO_INCREMENT, vehicleType varchar2(50) NOT NULL
    public void createObject(String vehicleType) {
        try {
            PreparedStatement ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO vehicle(vehicleType) VALUES (?)");
            ps.setString(1,vehicleType);
            if(ps.executeUpdate()!=0){
                System.out.println("Datos insertados en tabla vehiculo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public VehicleClass readObject(int id) {
        VehicleClass vehicle=null;
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("SELECT * FROM vehicle WHERE idVehicle=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while ((rs.next())) {
                vehicle = new VehicleClass(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    public void updateObject(int id,String vehicleType) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("UPDATE concessionaire SET vehicleType=? WHERE idVehicle=?");
            ps.setString(1,vehicleType);
            ps.setInt(2,id);
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Vehiculo con id="+id+" actualizado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteObject(int id) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("DELETE FROM vehicle WHERE idVehicle=?");
            ps.setInt(1,id);
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Vehiculo con id="+id+" borrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
