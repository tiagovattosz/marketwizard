package br.com.verkom.marketwizard.backend.controller;
import br.com.verkom.marketwizard.backend.model.Cotacao;
import br.com.verkom.marketwizard.backend.service.CotacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cotacao")
public class CotacaoController {

    private final CotacaoService cotacaoService;

    public CotacaoController(CotacaoService cotacaoService) {
        this.cotacaoService = cotacaoService;
    }

    @GetMapping
    public ResponseEntity<List<Cotacao>> getAllCotacoes() {
        List<Cotacao> cotacoes = cotacaoService.getAllCotacoes();
        return ResponseEntity.ok().body(cotacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cotacao> getCotacaoById(@PathVariable Long id) {
        Cotacao cotacoes = cotacaoService.getCotacaoById(id);
        return ResponseEntity.ok().body(cotacoes);
    }

    @PostMapping
    public ResponseEntity<Cotacao> createCotacao(@RequestBody @Valid Cotacao cotacao) {
        Cotacao createdCotacao = cotacaoService.createCotacao(cotacao);
        return ResponseEntity.ok().body(createdCotacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cotacao> updateCotacao(@PathVariable Long id, @RequestBody @Valid Cotacao cotacaoDetails) {
        Cotacao updatedCotacao = cotacaoService.updateCotacao(id, cotacaoDetails);
        return ResponseEntity.ok().body(updatedCotacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCotacao(@PathVariable Long id) {
        cotacaoService.deleteCotacao(id);
        return ResponseEntity.ok().build();
    }
}
