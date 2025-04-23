package com.practica1.service.dao;

import com.practica1.model.Concessionaire;
import com.practica1.model.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConcessionaireDaoTest {

    @Test
    void createObject() {
        DatabaseConnection databaseConnection=new DatabaseConnection();
        databaseConnection.initializeConnection();
        PreparedStatement ps= null;
        try {
            ps = databaseConnection.getH2_Connection().prepareStatement("INSERT INTO concessionaire(name, numVehicles) VALUES (?,?)");
            ps.setString(1,"name");
            ps.setInt(2,3);
            assertEquals(1,ps.executeUpdate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void readObject() {
        DatabaseConnection databaseConnection=new DatabaseConnection();
        databaseConnection.initializeConnection();
        PreparedStatement ps= null;
        try {
            ps = databaseConnection.getH2_Connection().prepareStatement("INSERT INTO concessionaire(idConcessionaire,name, numVehicles) VALUES (1, 'name', 3)");
            ps.executeUpdate();

            ps = databaseConnection.getH2_Connection().prepareStatement("SELECT * FROM concessionaire WHERE idConcessionaire=?");
            ps.setInt(1,1);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateObject() {
        DatabaseConnection databaseConnection=new DatabaseConnection();
        databaseConnection.initializeConnection();
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("UPDATE concessionaire SET name=?,numVehicles=? WHERE idConcessionaire=?");
            ps.setString(1,"name33");
            ps.setInt(2,9);
            ps.setInt(3,1);

            assertEquals(1, ps.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteObject() {
        DatabaseConnection databaseConnection=new DatabaseConnection();
        databaseConnection.initializeConnection();
        try {
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("DELETE FROM concessionaire WHERE idConcessionaire=?");
            ps.setInt(1,1);
            int rows=ps.executeUpdate();
            assertEquals(1,rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}