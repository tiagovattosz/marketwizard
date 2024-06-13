package br.com.verkom.marketwizard.backend.service;

import br.com.verkom.marketwizard.backend.dto.ProdutoRelatorioDTO;
import br.com.verkom.marketwizard.backend.dto.RelatorioGeralDTO;
import br.com.verkom.marketwizard.backend.repository.CompraRepository;
import br.com.verkom.marketwizard.backend.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioService {

    private final CompraRepository compraRepository;

    private final VendaRepository vendaRepository;

    public RelatorioService(CompraRepository compraRepository, VendaRepository vendaRepository) {
        this.compraRepository = compraRepository;
        this.vendaRepository = vendaRepository;
    }

    public RelatorioGeralDTO getRelatorioGeral(LocalDate startDate, LocalDate endDate) {
        LocalDate start = startDate != null ? startDate : LocalDate.EPOCH;
        LocalDate end = endDate != null ? endDate : LocalDate.now();

        RelatorioGeralDTO relatorioGeralDTO = new RelatorioGeralDTO();
        relatorioGeralDTO.setStartDate(start);
        relatorioGeralDTO.setEndDate(end);

        List<Object[]> compras = compraRepository.findComprasPorPeriodo(start, end);
        List<Object[]> vendas = vendaRepository.findVendasPorPeriodo(start, end);

        Map<Long, ProdutoRelatorioDTO> produtosMap = new HashMap<>();

        for (Object[] compra : compras) {
            Long produtoId = (Long) compra[0];
            ProdutoRelatorioDTO produtoDTO = produtosMap.getOrDefault(produtoId, new ProdutoRelatorioDTO());
            produtoDTO.setProdutoId(produtoId);
            produtoDTO.setProdutoNome((String) compra[1]);
            produtoDTO.setQuantidadeTotalCompras((Long) compra[2]);
            produtoDTO.setValorTotalCompras((BigDecimal) compra[3]);
            produtosMap.put(produtoId, produtoDTO);

            relatorioGeralDTO.setQuantidadeTotalCompras(relatorioGeralDTO.getQuantidadeTotalCompras() + produtoDTO.getQuantidadeTotalCompras());
            relatorioGeralDTO.setGastoTotalCompras(relatorioGeralDTO.getGastoTotalCompras().add(produtoDTO.getValorTotalCompras()));
        }

        for (Object[] venda : vendas) {
            Long produtoId = (Long) venda[0];
            ProdutoRelatorioDTO produtoDTO = produtosMap.getOrDefault(produtoId, new ProdutoRelatorioDTO());
            produtoDTO.setProdutoId(produtoId);
            produtoDTO.setProdutoNome((String) venda[1]);
            produtoDTO.setQuantidadeTotalVendas((Long) venda[2]);
            produtoDTO.setValorTotalVendas((BigDecimal) venda[3]);
            produtoDTO.setValorTotalLiquido((BigDecimal) venda[4]);
            produtoDTO.setLucroTotal(produtoDTO.getValorTotalLiquido().subtract(produtoDTO.getValorTotalCompras()));
            produtosMap.put(produtoId, produtoDTO);

            relatorioGeralDTO.setQuantidadeTotalVendas(relatorioGeralDTO.getQuantidadeTotalVendas() + produtoDTO.getQuantidadeTotalVendas());
            relatorioGeralDTO.setFaturamentoTotalVendas(relatorioGeralDTO.getFaturamentoTotalVendas().add(produtoDTO.getValorTotalVendas()));
            relatorioGeralDTO.setFaturamentoTotalSemTaxa(relatorioGeralDTO.getFaturamentoTotalSemTaxa().add(produtoDTO.getValorTotalLiquido()));
            relatorioGeralDTO.setLucroTotal(relatorioGeralDTO.getLucroTotal().add(produtoDTO.getLucroTotal()));
        }

        relatorioGeralDTO.setProdutos(new ArrayList<>(produtosMap.values()));
        return relatorioGeralDTO;
    }
}
