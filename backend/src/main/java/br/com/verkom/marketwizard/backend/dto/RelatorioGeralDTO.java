package br.com.verkom.marketwizard.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RelatorioGeralDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ProdutoRelatorioDTO> produtos = new ArrayList<>();
    private Long quantidadeTotalCompras = 0L;
    private BigDecimal gastoTotalCompras = BigDecimal.ZERO;
    private Long quantidadeTotalVendas = 0L;
    private BigDecimal faturamentoTotalVendas = BigDecimal.ZERO;
    private BigDecimal faturamentoTotalSemTaxa = BigDecimal.ZERO;
    private BigDecimal lucroTotal = BigDecimal.ZERO;
}
