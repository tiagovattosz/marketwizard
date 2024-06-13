package br.com.verkom.marketwizard.backend.controller;

import br.com.verkom.marketwizard.backend.model.Fornecedor;
import br.com.verkom.marketwizard.backend.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> getAllFornecedores() {
        List<Fornecedor> fornecedores = fornecedorService.getAllFornecedores();
        return ResponseEntity.ok().body(fornecedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> getFornecedorById(@PathVariable Long id) {
        Fornecedor fornecedor = fornecedorService.getFornecedorById(id);
        return ResponseEntity.ok().body(fornecedor);
    }

    @PostMapping
    public ResponseEntity<Fornecedor> createFornecedor(@Valid @RequestBody Fornecedor fornecedor) {
        Fornecedor createdFornecedor = fornecedorService.createFornecedor(fornecedor);
        return ResponseEntity.ok().body(createdFornecedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable Long id, @Valid @RequestBody Fornecedor fornecedorDetails) {
        Fornecedor updatedFornecedor = fornecedorService.updateFornecedor(id, fornecedorDetails);
        return ResponseEntity.ok().body(updatedFornecedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable Long id) {
        fornecedorService.deleteFornecedor(id);
        return ResponseEntity.ok().build();
    }

}
