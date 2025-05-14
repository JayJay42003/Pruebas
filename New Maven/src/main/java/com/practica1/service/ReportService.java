package com.practica1.service;

import com.practica1.model.DatabaseConnection;
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
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private DatabaseConnection dataSource;

    public byte[] exportReportToPdf() throws JRException, SQLException, IOException {
        ClassPathResource jasperResource = new ClassPathResource("Vehicles.jasper");
        try (InputStream reportStream = jasperResource.getInputStream()) {

            if(reportStream==null){
                System.out.println("Stream vacio");
            }

            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    reportStream,
                    parameters,
                    dataSource.getConnection()
            );


            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
    }
}
