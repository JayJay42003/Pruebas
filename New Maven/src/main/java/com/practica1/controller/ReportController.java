package com.practica1.controller;

import com.practica1.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * Endpoint para obtener el reporte en PDF sin parámetros.
     *
     * @return ResponseEntity con PDF inline
     */

    @GetMapping(value = "/vehiculos", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getReport() {

        try {
            byte[] pdf = reportService.exportReportToPdf();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "vehiculos.pdf");

            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } catch (JRException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Error generando reporte: " + e.getMessage()).getBytes());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}