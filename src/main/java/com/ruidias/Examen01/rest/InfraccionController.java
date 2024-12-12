package com.ruidias.Examen01.rest;

import com.ruidias.Examen01.dto.UsuarioDto;
import com.ruidias.Examen01.entity.Infracciones;
import com.ruidias.Examen01.service.InfraccionService;
import com.ruidias.Examen01.service.PdfService;
import com.ruidias.Examen01.util.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/v1/infraccion")
public class InfraccionController {
    @Autowired
    private InfraccionService infraccionService;
    @Autowired
    private PdfService pdfService;

    @GetMapping
    public ResponseEntity<List<Infracciones>> findAll() {
        List<Infracciones> request = infraccionService.findAll();
        return new WrapperResponse(true, "success", request).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Infracciones> create (@RequestBody Infracciones infracciones) {
        Infracciones request = infraccionService.save(infracciones);

        return new WrapperResponse(true, "success", request).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Infracciones> update (@PathVariable("id") int id, @RequestBody Infracciones infracciones) {
        Infracciones request = infraccionService.save(infracciones);
        return new WrapperResponse(true, "success", request).createResponse(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        infraccionService.delete(id);
        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Infracciones> findById (@PathVariable("id") int id) {
       Infracciones request = infraccionService.findById(id);

        return new WrapperResponse(true, "success",request).createResponse(HttpStatus.OK);
    }
    @GetMapping("/report")
    public ResponseEntity<byte[]> generateReport() {
        List<Infracciones> request = infraccionService.findAll();

        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHora = fecha.format(formato);

        // crear el contexto de Thymeleaf con los datos
        Context context = new Context();
        context.setVariable("registros", request);
        context.setVariable("fecha", fechaHora);

        // Llamar al servicio PdfService para generar el PDF
        byte[] pdfBytes = pdfService.createPdf("infraccionReporte", context);

        // Configurar los encabezados de la respuesta HTTP para devolver el PDF
        // import org.springframework.http.HttpHeaders;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "reporte_infraccion.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}
