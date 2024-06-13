package br.com.verkom.marketwizard.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VendasPeriodoDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ProdutoVendaDTO> produtos = new ArrayList<>();
    private Long quantidadeTotal = 0L;
    private Double faturamentoTotal = 0D;
    private Double faturamentoTotalSemTaxa = 0D;
}
