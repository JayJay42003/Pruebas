package com.practica1.service.dao;

import com.practica1.model.Car;
import com.practica1.model.DatabaseConnection;
import com.practica1.model.common.FuelType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static com.practica1.model.common.FuelType.DIESEL;
import static com.practica1.model.common.FuelType.GASOLINE;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarDaoTest {
    @InjectMocks
    CarDao mockCarDao;
    @Mock
    Car mockCar;
    @Mock
    DatabaseConnection mockDatabaseConnection;
    @Mock
    PreparedStatement mockPreparedStatement;
    @Mock
    Connection mockConnection;

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
            List<Car> carTest=carDao.readAll();
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
            carDao.createObject(new Car(1, 1, "5704GPN", "Toyota", "Corolla", LocalDate.of(2020,1,15), GASOLINE.name(), 4));
            carDao.updateObject(1,new Car(1, 1, "9999", "Toyota", "Prueba", LocalDate.of(1990,1,3), DIESEL.name(), 4));
            Car c=carDao.readObjectbyLicense("9999");
            //then
            assertEquals(DIESEL,c.getTypeFuel());
            assertEquals("9999",c.getLicensePlate());
            assertEquals("Prueba",c.getModel());


        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnection.closeConnection();
    }

    @Test
    void updateObjectbyLicense() {
        try {
            // Arrange
            Car car = new Car(1, 1, "9999ZZZ", "Toyota", "Yaris",
                    LocalDate.of(2022, 5, 10), GASOLINE.name(), 5);

            when(mockDatabaseConnection.getH2_Connection()).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeUpdate()).thenReturn(1); // simula que 1 fila fue actualizada

            // Act
            mockCarDao.updateObjectbyLicense("5704GPN", car);

            // Assert
            verify(mockDatabaseConnection).getH2_Connection();
            verify(mockConnection).prepareStatement(anyString());
            verify(mockPreparedStatement).executeUpdate();

        }catch (SQLException sql){
            sql.printStackTrace();
        }
    }

    @Test
    void deleteObject() {
        try {
            when(mockDatabaseConnection.getH2_Connection()).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeUpdate()).thenReturn(1); // simula que 1 fila fue actualizada

            // Act
            mockCarDao.deleteObject(1);

            // Assert
            verify(mockDatabaseConnection).getH2_Connection();
            verify(mockConnection).prepareStatement(anyString());
            verify(mockPreparedStatement).executeUpdate();

        }catch (SQLException sql){
            sql.printStackTrace();
        }
    }

    @Test
    void deleteObjectbyLicense() {
        try {

            when(mockDatabaseConnection.getH2_Connection()).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeUpdate()).thenReturn(1); // simula que 1 fila fue actualizada

            // Act
            mockCarDao.deleteObjectbyLicense("5704GPN");

            // Assert
            verify(mockDatabaseConnection).getH2_Connection();
            verify(mockConnection).prepareStatement(anyString());
            verify(mockPreparedStatement).executeUpdate();

        }catch (SQLException sql){
            sql.printStackTrace();
        }
    }
}