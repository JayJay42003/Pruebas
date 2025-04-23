package com.practica1.service.dao;

import com.practica1.model.DatabaseConnection;
import com.practica1.model.VehicleClass;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class VehicleDaoTest {

    @Test
    void createObject() {
        try {
            DatabaseConnection databaseConnection=new DatabaseConnection();
            databaseConnection.initializeConnection();

            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM vehicle");

            PreparedStatement ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO vehicle(vehicleType) VALUES (?)");
            ps.setString(1,"coche");
            assertEquals(1,ps.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void readObject() {
        try {
            DatabaseConnection databaseConnection=new DatabaseConnection();
            databaseConnection.initializeConnection();

            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM vehicle");

            //Añado un objeto para leer
            PreparedStatement ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO vehicle(vehicleType,idVehicle) VALUES (?,?)");
            ps.setString(1,"coche");
            ps.setInt(2,1);

            ps = databaseConnection.getH2_Connection().prepareStatement("SELECT * FROM vehicle WHERE idVehicle=?");
            ps.setInt(1,1);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateObject() {
        try {
            DatabaseConnection databaseConnection=new DatabaseConnection();
            databaseConnection.initializeConnection();

            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM vehicle");

            //Añado un objeto para leer
            PreparedStatement ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO vehicle(vehicleType,idVehicle) VALUES (?,?)");
            ps.setString(1,"coche");
            ps.setInt(2,1);

             ps = databaseConnection.getH2_Connection().prepareStatement("UPDATE concessionaire SET vehicleType=? WHERE idVehicle=?");
            ps.setString(1,"carromato");
            ps.setInt(2,1);
            int rows=ps.executeUpdate();
            assertEquals(1,rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteObject() {
        try {
            DatabaseConnection databaseConnection=new DatabaseConnection();
            databaseConnection.initializeConnection();

            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM vehicle");

            //Añado un objeto para leer
            PreparedStatement ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO vehicle(vehicleType,idVehicle) VALUES (?,?)");
            ps.setString(1,"coche");
            ps.setInt(2,1);

            ps = databaseConnection.getH2_Connection().prepareStatement("DELETE FROM vehicle WHERE idVehicle=?");
            ps.setInt(1,1);
            assertEquals(1,ps.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}