package com.practica1.service.dao;

import com.practica1.model.DatabaseConnection;
import com.practica1.model.VehicleClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VehicleDaoTest {

    @Test
    void createObject() {
        try {
            DatabaseConnection databaseConnection=new DatabaseConnection();
            databaseConnection.initializeConnection();

            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM vehicle");
            stmt.executeUpdate("ALTER TABLE car ALTER COLUMN idVehicle RESTART WITH 1");

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
            stmt.executeUpdate("ALTER TABLE car ALTER COLUMN idVehicle RESTART WITH 1");

            //Añado un objeto para leer
            PreparedStatement ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO vehicle(vehicleType,idVehicle) VALUES (?,?)");
            ps.setString(1,"coche");
            ps.setInt(2,1);
            ps.executeUpdate();

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
            stmt.executeUpdate("ALTER TABLE car ALTER COLUMN idVehicle RESTART WITH 1");

            //Añado un objeto para leer
            PreparedStatement ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO vehicle(vehicleType,idVehicle) VALUES (?,?)");
            ps.setString(1,"coche");
            ps.setInt(2,1);
            ps.executeUpdate();

             ps = databaseConnection.getH2_Connection().prepareStatement("UPDATE vehicle SET vehicleType=? WHERE idVehicle=?");
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
            stmt.executeUpdate("ALTER TABLE car ALTER COLUMN idVehicle RESTART WITH 1");

            //Añado un objeto para leer
            PreparedStatement ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO vehicle(vehicleType,idVehicle) VALUES (?,?)");
            ps.setString(1,"coche");
            ps.setInt(2,1);
            ps.executeUpdate();

            ps = databaseConnection.getH2_Connection().prepareStatement("DELETE FROM vehicle WHERE idVehicle=?");
            ps.setInt(1,1);
            int r=ps.executeUpdate();
            assertEquals(1,r);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}