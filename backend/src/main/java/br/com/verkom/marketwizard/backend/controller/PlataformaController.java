package br.com.verkom.marketwizard.backend.controller;

import br.com.verkom.marketwizard.backend.model.Plataforma;
import br.com.verkom.marketwizard.backend.service.PlataformaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plataforma")
public class PlataformaController {

    private final PlataformaService plataformaService;

    public PlataformaController(PlataformaService plataformaService) {
        this.plataformaService = plataformaService;
    }

    @GetMapping
    public ResponseEntity<List<Plataforma>> getAllPlataformas() {
        List<Plataforma> plataformas = plataformaService.getAllPlataformas();
        return ResponseEntity.ok().body(plataformas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plataforma> getPlataformaById(@PathVariable Long id) {
        Plataforma plataforma = plataformaService.getPlataformaById(id);
        return ResponseEntity.ok().body(plataforma);
    }

    @PostMapping
    public ResponseEntity<Plataforma> createPlataforma(@Valid @RequestBody Plataforma plataforma) {
        Plataforma createdPlataforma = plataformaService.createPlataforma(plataforma);
        return ResponseEntity.ok().body(createdPlataforma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plataforma> updatePlataforma(@PathVariable Long id, @Valid @RequestBody Plataforma plaformaDetails) {
        Plataforma updatedPlataforma = plataformaService.updatePlataforma(id, plaformaDetails);
        return ResponseEntity.ok().body(updatedPlataforma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlataforma(@PathVariable Long id) {
        plataformaService.deletePlataforma(id);
        return ResponseEntity.ok().build();
    }

}
