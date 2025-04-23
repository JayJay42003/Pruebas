package com.practica1.service.dao;

import com.practica1.model.Car;
import com.practica1.model.DatabaseConnection;
import com.practica1.model.common.FuelType;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static com.practica1.model.common.FuelType.GASOLINE;
import static org.junit.jupiter.api.Assertions.*;

class CarDaoTest {

    @Test
    void createObject() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.initializeConnection();
        CarDao carDao=new CarDao(databaseConnection);

        try {
            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM car");
            stmt.executeUpdate("DELETE FROM concessionaire");

            //when
            carDao.createObject(new Car(1, 1, "5704GPN", "Toyota", "Corolla", LocalDate.of(2020,1,15), GASOLINE.name(), 4));
            //then
            PreparedStatement ps = databaseConnection.getH2_Connection().prepareStatement("SELECT * FROM car WHERE brand = 'Toyota' AND model = 'Corolla'");
            ResultSet rs=ps.executeQuery();

            assertTrue(rs.next()); // Verificamos que se insertó algo
            assertEquals("Toyota", rs.getString("brand"));
            assertEquals("Corolla", rs.getString("model"));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnection.closeConnection();
    }

    @Test
    void readObject() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.initializeConnection();
        CarDao carDao=new CarDao(databaseConnection);

        try {
            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM car");
            stmt.executeUpdate("ALTER TABLE car ALTER COLUMN idVehicle RESTART WITH 1");

            //when
            carDao.createObject(new Car(1, 1, "5704GPN", "Toyota", "Corolla", LocalDate.of(2020,1,15), GASOLINE.name(), 4));
            Car carTest=carDao.readObject(1);
            //then
            assertEquals(1,carTest.getIdVehicle());
            assertEquals("5704GPN",carTest.getLicensePlate());


        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnection.closeConnection();
    }

    @Test
    void readObjectbyLicense() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.initializeConnection();
        CarDao carDao=new CarDao(databaseConnection);

        try {
            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM car");
            stmt.executeUpdate("ALTER TABLE car ALTER COLUMN idVehicle RESTART WITH 1");

            //when
            carDao.createObject(new Car(1, 1, "5704GPN", "Toyota", "Corolla", LocalDate.of(2020,1,15), GASOLINE.name(), 4));
            Car carTest=carDao.readObjectbyLicense("5704GPN");

            //then
            assertEquals(1,carTest.getIdVehicle());
            assertEquals("5704GPN",carTest.getLicensePlate());
            assertEquals("Toyota",carTest.getBrand());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnection.closeConnection();
    }

    @Test
    void readAll() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.initializeConnection();
        CarDao carDao=new CarDao(databaseConnection);

        try {
            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM car");
            stmt.executeUpdate("ALTER TABLE car ALTER COLUMN idVehicle RESTART WITH 1");

            //when
            carDao.createObject(new Car(1, 1, "5704GPN", "Toyota", "Corolla", LocalDate.of(2020,1,15), GASOLINE.name(), 4));
            Car carTest=carDao.readObjectbyLicense("5704GPN");
            //then
            assertEquals(1,carTest.getIdVehicle());
            assertEquals("5704GPN",carTest.getLicensePlate());
            assertEquals("Toyota",carTest.getBrand());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnection.closeConnection();
    }

    @Test
    void readAllbyConcessionaire() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.initializeConnection();
        CarDao carDao=new CarDao(databaseConnection);
        ConcessionaireDao concessionaireDao=new ConcessionaireDao(databaseConnection);

        try {
            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM car");
            stmt.executeUpdate("DELETE FROM concessionaire");
            stmt.executeUpdate("ALTER TABLE car ALTER COLUMN idVehicle RESTART WITH 1");
            stmt.executeUpdate("ALTER TABLE concessionaire ALTER COLUMN idConcessionaire RESTART WITH 1");

            //when
            concessionaireDao.createObject("c1",2);
            carDao.createObject(new Car(1, 1, "5704GPN", "Toyota", "Corolla", LocalDate.of(2020,1,15), GASOLINE.name(), 4));
            carDao.createObject(new Car(2, 1, "6000AAA", "Fiat", "500", LocalDate.of(2020,1,15), GASOLINE.name(), 4));
            List<Car> carTest=carDao.readAllbyConcessionaire("c1");
            //then
            assertEquals(1,carTest.get(0).getIdVehicle());
            assertEquals("5704GPN",carTest.get(0).getLicensePlate());
            assertEquals("Toyota",carTest.get(0).getBrand());

            assertEquals("Fiat",carTest.get(1).getBrand());
            assertEquals("6000AAA",carTest.get(1).getLicensePlate());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnection.closeConnection();
    }

    @Test
    void updateObject() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.initializeConnection();
        CarDao carDao=new CarDao(databaseConnection);

        try {
            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM car");
            stmt.executeUpdate("ALTER TABLE car ALTER COLUMN idVehicle RESTART WITH 1");

            //when
            concessionaireDao.createObject("c1",2);
            carDao.createObject(new Car(1, 1, "5704GPN", "Toyota", "Corolla", LocalDate.of(2020,1,15), GASOLINE.name(), 4));
            List<Car> carTest=carDao.updateObject(1,"c1");
            //then
            assertEquals(1,carTest.get(0).getIdVehicle());
            assertEquals("5704GPN",carTest.get(0).getLicensePlate());
            assertEquals("Toyota",carTest.get(0).getBrand());


        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnection.closeConnection();
    }

    @Test
    void updateObjectbyLicense() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.initializeConnection();
        CarDao carDao=new CarDao(databaseConnection);

         try {
             //Elimino los datos anteriores
             Statement stmt = databaseConnection.getH2_Connection().createStatement();
             stmt.executeUpdate("DELETE FROM car");
             stmt.executeUpdate("ALTER TABLE car ALTER COLUMN idVehicle RESTART WITH 1");

             PreparedStatement ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO concessionaire(name, numVehicles) VALUES ('conc1',3)");
             ps.executeUpdate();
             ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO car(idVehicle,idConcessionaire, licensePlate,brand," +
                     "model,yearCreated,typeFuel,doorsNum) VALUES (1, 1, '5704GPN', 'Toyota', 'Corolla', '2020-1-15', 'GASOLINE', 4)");
             ps.executeUpdate();

             ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO car(idVehicle,idConcessionaire, licensePlate,brand," +
                     "model,yearCreated,typeFuel,doorsNum) VALUES (2, 1, '5704FPF', 'Toyota', 'Corolla', '2006-1-15', 'GASOLINE', 3)");
             ps.executeUpdate();

              ps = databaseConnection.getH2_Connection().prepareStatement("UPDATE car SET licensePlate=?,brand=?,model=?, yearCreated=?, typeFuel=?, doorsNum=? WHERE idVehicle=?");
            ps.setString(1, "6500NES");
            ps.setString(2, "Mercedes");
            ps.setString(3, "Corolla");
            ps.setDate(4, Date.valueOf(LocalDate.of(2020, 1, 15)));
            ps.setString(5, GASOLINE.name());
            ps.setInt(6, 4);
            ps.setInt(7, 1);
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
        CarDao carDao=new CarDao(databaseConnection);

        try {
            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM car");
            stmt.executeUpdate("ALTER TABLE car ALTER COLUMN idVehicle RESTART WITH 1");

            PreparedStatement ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO concessionaire(name, numVehicles) VALUES ('conc1',3)");
            ps.executeUpdate();
            ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO car(idVehicle,idConcessionaire, licensePlate,brand," +
                    "model,yearCreated,typeFuel,doorsNum) VALUES (1, 1, '5704GPN', 'Toyota', 'Corolla', '2020-1-15', 'GASOLINE', 4)");
            ps.executeUpdate();

            ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO car(idVehicle,idConcessionaire, licensePlate,brand," +
                    "model,yearCreated,typeFuel,doorsNum) VALUES (2, 1, '5704FPF', 'Toyota', 'Corolla', '2006-1-15', 'GASOLINE', 3)");
            ps.executeUpdate();

             ps = databaseConnection.getH2_Connection().prepareStatement("UPDATE car SET licensePlate=?,brand=?,model=?, yearCreated=?, typeFuel=?, doorsNum=? WHERE idVehicle=?");
            ps.setString(1, "6500NES");
            ps.setString(2, "Mercedes");
            ps.setString(3, "Corolla");
            ps.setDate(4, Date.valueOf(LocalDate.of(2020, 1, 15)));
            ps.setString(5, GASOLINE.name());
            ps.setInt(6, 4);
            ps.setInt(7, 1);
            int rows = ps.executeUpdate();

            assertEquals(1, rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnection.closeConnection();
    }

    @Test
    void deleteObjectbyLicense() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.initializeConnection();
        CarDao carDao=new CarDao(databaseConnection);

        try {
            //Elimino los datos anteriores
            Statement stmt = databaseConnection.getH2_Connection().createStatement();
            stmt.executeUpdate("DELETE FROM car");
            stmt.executeUpdate("ALTER TABLE car ALTER COLUMN idVehicle RESTART WITH 1");

            PreparedStatement ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO concessionaire(name, numVehicles) VALUES ('conc1',3)");
            ps.executeUpdate();
            ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO car(idVehicle,idConcessionaire, licensePlate,brand," +
                    "model,yearCreated,typeFuel,doorsNum) VALUES (1, 1, '5704GPN', 'Toyota', 'Corolla', '2020-1-15', 'GASOLINE', 4)");
            ps.executeUpdate();

            ps=databaseConnection.getH2_Connection().prepareStatement("INSERT INTO car(idVehicle,idConcessionaire, licensePlate,brand," +
                    "model,yearCreated,typeFuel,doorsNum) VALUES (2, 1, '5704FPF', 'Toyota', 'Corolla', '2006-1-15', 'GASOLINE', 3)");
            ps.executeUpdate();
            ps = databaseConnection.getH2_Connection().prepareStatement("DELETE FROM car WHERE licensePlate=?");
            ps.setString(1, "5704GPN");

            int rows = ps.executeUpdate();
            assertEquals(1, rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnection.closeConnection();
    }
}