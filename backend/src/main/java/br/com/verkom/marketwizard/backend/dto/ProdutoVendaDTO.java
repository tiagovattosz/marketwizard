package br.com.verkom.marketwizard.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoVendaDTO {
    private Long produtoId;
    private String produtoNome;
    private Long quantidadeTotalVendas;
    private Double valorTotalVendas;
    private Double valorTotalLiquido;
    private Double mediaValorUnitario;
}
