package br.com.verkom.marketwizard.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlataformaVendaDTO {
    private Long plataformaId;
    private String plataformaNome;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ProdutoVendaDTO> produtos = new ArrayList<>();
    private Double valorTotalVendasPlataforma;
    private Double valorTotalLiquidoPlataforma;
    private Long quantidadeTotalItensPlataforma;
}
