package br.com.verkom.marketwizard.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FornecedorCompraDTO {
    private Long fornecedorId;
    private String fornecedorNome;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ProdutoCompraDTO> produtos = new ArrayList<>();
    private Double valorTotalCompraFornecedor;
    private Long quantidadeTotalItensFornecedor;
}
