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
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private DataSource dataSource;

    public byte[] exportReportToPdf() throws JRException, SQLException, IOException {
        ClassPathResource jasperResource = new ClassPathResource("Vehicles.jasper");
        System.out.println("/////////////"+jasperResource.exists());
        try (InputStream reportStream = jasperResource.getInputStream()) {

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    reportStream,
                    Collections.emptyMap(),
                    dataSource.getConnection()
            );

            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
            System.out.println("///////PDF generado, tamaño (bytes): " + pdfBytes.length);

            return pdfBytes;
        }
    }
}
