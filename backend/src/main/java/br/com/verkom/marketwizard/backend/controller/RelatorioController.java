package br.com.verkom.marketwizard.backend.controller;

import br.com.verkom.marketwizard.backend.dto.RelatorioGeralDTO;
import br.com.verkom.marketwizard.backend.service.RelatorioService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {
    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/geral")
    public ResponseEntity<RelatorioGeralDTO> getRelatorioGeral(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        RelatorioGeralDTO relatorioGeralDTO = relatorioService.getRelatorioGeral(startDate, endDate);
        return ResponseEntity.ok(relatorioGeralDTO);
    }
}
