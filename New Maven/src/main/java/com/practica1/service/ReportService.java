package com.practica1.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private DataSource dataSource; // si usas JDBC

    public byte[] exportReportToPdf() throws JRException, SQLException, IOException {
        // Ruta dentro de resources
        String jasperPath = "C:\\Users\\joel.jimenez\\JaspersoftWorkspace\\MyReports\\Vehicles.jasper";
        // Carga el .jasper
        InputStream reportStream = new ClassPathResource(jasperPath).getInputStream();

        // Llenar el reporte con datos
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                reportStream,
                Collections.emptyMap(),
                dataSource.getConnection()
        );

        // Exportar a PDF y devolver bytes
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
