package br.com.verkom.marketwizard.backend.controller;

import br.com.verkom.marketwizard.backend.dto.ComprasPeriodoDTO;
import br.com.verkom.marketwizard.backend.dto.FornecedorCompraDTO;
import br.com.verkom.marketwizard.backend.model.Compra;
import br.com.verkom.marketwizard.backend.service.CompraService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/compra")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public ResponseEntity<List<Compra>> getAllCompras() {
        List<Compra> compras = compraService.getAllCompras();
        return ResponseEntity.ok().body(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> getCompraById(@PathVariable Long id) {
        Compra compra = compraService.getCompraById(id);
        return ResponseEntity.ok().body(compra);
    }

    @GetMapping("/chegaram")
    public List<Compra> listarComprasQueChegaram() {
        return compraService.listarComprasQueChegaram();
    }

    @GetMapping("/nao-chegaram")
    public List<Compra> listarComprasQueNaoChegaram() {
        return compraService.listarComprasQueNaoChegaram();
    }

    @PostMapping
    public ResponseEntity<Compra> createCompra(@Valid @RequestBody Compra compra) {
        Compra createdCompra = compraService.createCompra(compra);
        return ResponseEntity.ok().body(createdCompra);
    }

    @PostMapping("/{id}/confirmar")
    public Compra confirmarRecebimento(@PathVariable Long id) {
        return compraService.confirmarRecebimento(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> updateCompra(@PathVariable Long id, @Valid @RequestBody Compra compraDetails) {
        Compra updatedCompra = compraService.updateCompra(id, compraDetails);
        return ResponseEntity.ok().body(updatedCompra);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompra(@PathVariable Long id) {
        compraService.deleteCompra(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/relatorio")
    public ResponseEntity<ComprasPeriodoDTO> getComprasPorProduto(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ComprasPeriodoDTO comprasPeriodoDTO = compraService.getComprasPorProduto(startDate, endDate);
        return ResponseEntity.ok(comprasPeriodoDTO);
    }

    @GetMapping("/relatorio-fornecedor/{fornecedorId}")
    public ResponseEntity<FornecedorCompraDTO> getComprasPorFornecedor(
            @PathVariable("fornecedorId") Long fornecedorId,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        FornecedorCompraDTO fornecedor = compraService.getComprasPorFornecedor(fornecedorId, startDate, endDate);
        return ResponseEntity.ok(fornecedor);
    }

}
