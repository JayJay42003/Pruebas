package com.practica1.model.dao;

import com.practica1.model.Concessionaire;
import com.practica1.model.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConcessionaireDAO {
    private DatabaseConnection databaseConnection;

    public ConcessionaireDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    //(idConcessionaire int, name varchar2(50), numVehicles int)
    public void createObject(String name,int numVehicles) {
        try {
            PreparedStatement ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO concessionaire(name, numVehicles) VALUES (?,?)");
            ps.setString(1,name);
            ps.setInt(2,numVehicles);
            if(ps.executeUpdate()!=0){
                System.out.println("Datos insertados en tabla concesionario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Concessionaire readObject(int id) {
        Concessionaire concessionaire = new Concessionaire();
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("SELECT * FROM concessionaire WHERE idConcessionaire=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while ((rs.next())) {
                concessionaire = new Concessionaire(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return concessionaire;
    }

    public void updateObject(int id,String name,int numVehicles) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("UPDATE concessionaire SET name=?,numVehicles=? WHERE idConcessionaire=?");
            ps.setString(1,name);
            ps.setInt(2,numVehicles);
            ps.setInt(3,id);
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Concesionario con id="+id+" actualizado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteObject(int id) {
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("DELETE FROM concessionaire WHERE idConcessionaire=?");
            ps.setInt(1,id);
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Concesionario con id="+id+" borrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
