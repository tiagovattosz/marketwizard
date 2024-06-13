package br.com.verkom.marketwizard.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoCompraDTO {
    private Long produtoId;
    private String produtoNome;
    private Long quantidadeTotalCompras;
    private Double valorTotalCompras;
    private Double mediaValorUnitario;
}
