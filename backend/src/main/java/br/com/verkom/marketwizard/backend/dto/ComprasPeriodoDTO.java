package br.com.verkom.marketwizard.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ComprasPeriodoDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ProdutoCompraDTO> produtos = new ArrayList<>();
    private Long quantidadeTotal = 0L;
    private Double gastoTotal = 0D;
}
