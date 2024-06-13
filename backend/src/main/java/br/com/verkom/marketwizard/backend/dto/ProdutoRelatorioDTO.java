package br.com.verkom.marketwizard.backend.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRelatorioDTO {
    private Long produtoId;
    private String produtoNome;
    private Long quantidadeTotalCompras;
    private BigDecimal valorTotalCompras;
    private Long quantidadeTotalVendas;
    private BigDecimal valorTotalVendas;
    private BigDecimal valorTotalLiquido;
    private BigDecimal lucroTotal;
}
