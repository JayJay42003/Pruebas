package com.practica1.service.dao;

import com.practica1.model.Concessionaire;
import com.practica1.model.DatabaseConnection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConcessionaireDaoTest {

    @Mock
    DatabaseConnection databaseConnectionMock;
    @Mock
    Connection connectionMock;
    @Mock
    PreparedStatement preparedStatementMock;
    @Mock
    ResultSet rsMock;
    @InjectMocks
    ConcessionaireDao concessionaireDaoMock;

    @Test
    void createObject() {
        try {
            when(databaseConnectionMock.getH2_Connection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
            when(preparedStatementMock.executeUpdate()).thenReturn(1); // simula que 1 fila fue actualizada

            // Act
            concessionaireDaoMock.createObject("name1",5);

            // Assert
            verify(databaseConnectionMock).getH2_Connection();
            verify(connectionMock).prepareStatement(anyString());
            verify(preparedStatementMock).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readObject() {
        try {
            when(databaseConnectionMock.getH2_Connection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
            when(preparedStatementMock.executeQuery()).thenReturn(rsMock); // simula que 1 fila fue actualizada
            when(rsMock.next()).thenReturn(true,false);
            when(rsMock.getInt(1)).thenReturn(1);
            when(rsMock.getString(2)).thenReturn("con1");
            when(rsMock.getInt(3)).thenReturn(3);

            // Creo el DAO real con la conexión mock
            ConcessionaireDao concessionaireDao = new ConcessionaireDao(databaseConnectionMock);
            // Act
            Concessionaire con=concessionaireDao.readObject(1);

            // Assert
            verify(databaseConnectionMock).getH2_Connection();
            verify(connectionMock).prepareStatement(anyString());
            verify(preparedStatementMock).executeQuery();
            assertEquals(3,con.getNumVehicles());
            assertEquals("con1",con.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateObject() {
        try {
            when(databaseConnectionMock.getH2_Connection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
            when(preparedStatementMock.executeUpdate()).thenReturn(1); // simula que 1 fila fue actualizada

            // Act
            concessionaireDaoMock.updateObject(1,"conc2",10);

            // Assert
            verify(databaseConnectionMock).getH2_Connection();
            verify(connectionMock).prepareStatement(anyString());
            verify(preparedStatementMock).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteObject() {
        try {
            when(databaseConnectionMock.getH2_Connection()).thenReturn(connectionMock);
            when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
            when(preparedStatementMock.executeUpdate()).thenReturn(1); // simula que 1 fila fue actualizada

            // Act
            concessionaireDaoMock.deleteObject(1);

            // Assert
            verify(databaseConnectionMock).getH2_Connection();
            verify(connectionMock).prepareStatement(anyString());
            verify(preparedStatementMock).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}