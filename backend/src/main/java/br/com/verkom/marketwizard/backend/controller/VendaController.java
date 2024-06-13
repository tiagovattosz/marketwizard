package br.com.verkom.marketwizard.backend.controller;

import br.com.verkom.marketwizard.backend.dto.PlataformaVendaDTO;
import br.com.verkom.marketwizard.backend.dto.VendasPeriodoDTO;
import br.com.verkom.marketwizard.backend.model.Venda;
import br.com.verkom.marketwizard.backend.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping
    public ResponseEntity<List<Venda>> getAllVendas() {
        List<Venda> vendas = vendaService.getAllVendas();
        return ResponseEntity.ok().body(vendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> getVendaById(@PathVariable Long id) {
        Venda venda = vendaService.getVendaById(id);
        return ResponseEntity.ok().body(venda);
    }

    @PostMapping
    public ResponseEntity<Venda> createVenda(@Valid @RequestBody Venda venda) {
        Venda createdVenda = vendaService.createVenda(venda);
        return ResponseEntity.ok().body(createdVenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> updateVenda(@PathVariable Long id, @Valid @RequestBody Venda vendaDetails) {
        Venda updatedVenda = vendaService.updateVenda(id, vendaDetails);
        return ResponseEntity.ok().body(updatedVenda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompra(@PathVariable Long id) {
        vendaService.deleteVenda(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/relatorio")
    public ResponseEntity<VendasPeriodoDTO> getVendasPorProduto(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        VendasPeriodoDTO vendasPeriodoDTO = vendaService.getVendasPorProduto(startDate, endDate);
        return ResponseEntity.ok(vendasPeriodoDTO);
    }

    @GetMapping("/relatorio-plataforma/{plataformaId}")
    public ResponseEntity<PlataformaVendaDTO> getVendasPorPlataforma(
            @PathVariable("plataformaId") Long plataformaId,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        PlataformaVendaDTO plataforma = vendaService.getVendasPorPlataforma(plataformaId, startDate, endDate);
        return ResponseEntity.ok(plataforma);
    }

}
