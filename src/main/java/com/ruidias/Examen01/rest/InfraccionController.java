package com.ruidias.Examen01.rest;

import com.ruidias.Examen01.entity.Infracciones;
import com.ruidias.Examen01.service.InfraccionService;
import com.ruidias.Examen01.util.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/infraccion")
public class InfraccionController {
    @Autowired
    private InfraccionService infraccionService;

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
}
