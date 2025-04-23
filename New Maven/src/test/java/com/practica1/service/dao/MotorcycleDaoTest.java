package com.practica1.service.dao;

import com.practica1.model.DatabaseConnection;
import com.practica1.model.common.FuelType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MotorcycleDaoTest {

    @Test
    void createObject() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.initializeConnection();

        try {
            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM motorcycle");
            stmt.executeUpdate("DELETE FROM concessionaire");

            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("INSERT INTO motorcycle(idConcessionaire, licensePlate,brand," +
            "model,yearCreated,typeFuel,cylinderCapacity) VALUES (1, '5704GPO', 'Yamaha', 'MT-07', '2019-3-20', 'GASOLINE', 689)");
            assertEquals(1, ps.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnection.closeConnection();
    }

    @Test
    void readObject() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.initializeConnection();

        try {
            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM motorcycle");
            stmt.executeUpdate("DELETE FROM concessionaire");

            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("INSERT INTO motorcycle(idConcessionaire, licensePlate,brand," +
                    "model,yearCreated,typeFuel,cylinderCapacity) VALUES (1, '5704GPO', 'Yamaha', 'MT-07', '2019-3-20', 'GASOLINE', 689)");
            ps.executeUpdate();

            ps = databaseConnection.getH2_Connection().prepareStatement("SELECT * FROM motorcycle WHERE licensePlate=?");
            ps.setString(1, "5704GPO");
            ResultSet rs = ps.executeQuery();

            assertTrue(rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnection.closeConnection();

    }

    @Test
    void updateObject() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.initializeConnection();

        try {
            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM motorcycle");
            stmt.executeUpdate("DELETE FROM concessionaire");

            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("INSERT INTO concessionaire(name, numVehicles) VALUES ('conc1',3)");
            ps.executeUpdate();

             ps = databaseConnection.getH2_Connection().prepareStatement("INSERT INTO motorcycle(idConcessionaire, licensePlate,brand," +
                    "model,yearCreated,typeFuel,cylinderCapacity) VALUES (1, '5704GPO', 'Yamaha', 'MT-07', '2019-3-20', 'GASOLINE', 689)");
            ps.executeUpdate();

            ps = databaseConnection.getH2_Connection().prepareStatement("UPDATE motorcycle SET licensePlate=?,brand=?,model=?, yearCreated=?, typeFuel=?, cylinderCapacity=? WHERE licensePlate=?");
            ps.setString(1, "6500NES");
            ps.setString(2, "Mercedes");
            ps.setString(3, "Corolla");
            ps.setDate(4, Date.valueOf(LocalDate.of(2020, 1, 15)));
            ps.setString(5, FuelType.GASOLINE.name());
            ps.setInt(6, 250);
            ps.setString(7, "5704GPO");
            int rows = ps.executeUpdate();

            assertEquals(1, rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnection.closeConnection();
    }

    @Test
    void deleteObject() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.initializeConnection();

        try {
            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM motorcycle");
            stmt.executeUpdate("DELETE FROM concessionaire");


           PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("INSERT INTO motorcycle(idVehicle,idConcessionaire, licensePlate,brand," +
                    "model,yearCreated,typeFuel,cylinderCapacity) VALUES (1,1, '5704GPO', 'Yamaha', 'MT-07', '2019-3-20', 'GASOLINE', 689)");
            ps.executeUpdate();

             ps = databaseConnection.getH2_Connection().prepareStatement("DELETE FROM motorcycle WHERE idVehicle=?");
            ps.setInt(1,1);
            int rows=ps.executeUpdate();

            assertEquals(1, rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnection.closeConnection();
    }
}