package br.com.verkom.marketwizard.backend.service;

import br.com.verkom.marketwizard.backend.dto.PlataformaVendaDTO;
import br.com.verkom.marketwizard.backend.dto.ProdutoVendaDTO;
import br.com.verkom.marketwizard.backend.dto.VendasPeriodoDTO;
import br.com.verkom.marketwizard.backend.exception.EstoqueInsuficienteException;
import br.com.verkom.marketwizard.backend.exception.RecursoNaoEncontradoException;
import br.com.verkom.marketwizard.backend.model.Plataforma;
import br.com.verkom.marketwizard.backend.model.Venda;
import br.com.verkom.marketwizard.backend.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoService produtoService;
    private final PlataformaService plataformaService;

    public VendaService(VendaRepository vendaRepository, ProdutoService produtoService, PlataformaService plataformaService) {
        this.vendaRepository = vendaRepository;
        this.produtoService = produtoService;
        this.plataformaService = plataformaService;
    }

    public List<Venda> getAllVendas() {
        return vendaRepository.findAll();
    }

    public Venda getVendaById(Long id) {
        Optional<Venda> vendaOptional = vendaRepository.findById(id);
        if (vendaOptional.isEmpty()) {
            throw new RecursoNaoEncontradoException("Venda de id: " + id + " não encontrada.");
        }
        return vendaOptional.get();
    }

    public Venda createVenda(Venda venda) {

        venda.setPlataforma(plataformaService.getPlataformaById(venda.getPlataforma().getId()));
        venda.setProduto(produtoService.getProdutoById(venda.getProduto().getId()));
        if(venda.getQuantidade() > venda.getProduto().getQuantidade()){
            throw new EstoqueInsuficienteException("Apenas restam " + venda.getProduto().getQuantidade() + " unidades desse produto no estoque.");
        }
        venda.getProduto().setQuantidade(venda.getProduto().getQuantidade() - venda.getQuantidade());
        calcularValores(venda);
        return vendaRepository.save(venda);
    }

    public Venda updateVenda(Long id, Venda vendaDetails) {
        Venda venda = getVendaById(id);

        venda.getProduto().setQuantidade(venda.getProduto().getQuantidade() + venda.getQuantidade());

        venda.setPlataforma(plataformaService.getPlataformaById(vendaDetails.getPlataforma().getId()));
        venda.setProduto(produtoService.getProdutoById(vendaDetails.getProduto().getId()));
        venda.setDataVenda(vendaDetails.getDataVenda());
        venda.setValorUnidade(vendaDetails.getValorUnidade());
        venda.setQuantidade(vendaDetails.getQuantidade());

        if (venda.getQuantidade() > venda.getProduto().getQuantidade()) {
            throw new EstoqueInsuficienteException("Apenas restam " + venda.getProduto().getQuantidade() + " unidades desse produto no estoque.");
        }

        venda.getProduto().setQuantidade(venda.getProduto().getQuantidade() - venda.getQuantidade());

        calcularValores(venda);

        return vendaRepository.save(venda);
    }

    public void deleteVenda(Long id) {
        Venda venda = getVendaById(id);
        venda.getProduto().setQuantidade(venda.getProduto().getQuantidade() + venda.getQuantidade());
        vendaRepository.delete(venda);
    }

    private void calcularValores(Venda venda) {
        BigDecimal valorTotal = venda.getValorUnidade().multiply(BigDecimal.valueOf(venda.getQuantidade()));
        BigDecimal valorLiquido = valorTotal
                .subtract(valorTotal
                        .multiply(venda.getPlataforma().getTaxa())
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));

        venda.setValorTotal(valorTotal);
        venda.setValorLiquido(valorLiquido);
    }

    public VendasPeriodoDTO getVendasPorProduto(LocalDate startDate, LocalDate endDate) {
        LocalDate start = startDate != null ? startDate : LocalDate.EPOCH;
        LocalDate end = endDate != null ? endDate : LocalDate.now();

        VendasPeriodoDTO vendasPeriodoDTO = new VendasPeriodoDTO();
        vendasPeriodoDTO.setStartDate(start);
        vendasPeriodoDTO.setEndDate(end);

        List<Object[]> result = vendaRepository.findVendasPorProduto(start, end);
        List<ProdutoVendaDTO> produtos = new ArrayList<>();

        for (Object[] row : result) {
            ProdutoVendaDTO dto = new ProdutoVendaDTO();

            dto.setProdutoId((Long) row[0]);
            dto.setProdutoNome((String) row[1]);
            dto.setQuantidadeTotalVendas((Long) row[2]);
            dto.setValorTotalVendas(((Number) row[3]).doubleValue());
            dto.setValorTotalLiquido(((Number) row[4]).doubleValue());
            dto.setMediaValorUnitario(dto.getValorTotalVendas()/dto.getQuantidadeTotalVendas());

            produtos.add(dto);

            vendasPeriodoDTO.setFaturamentoTotal(round(vendasPeriodoDTO.getFaturamentoTotal() + dto.getValorTotalVendas(), 2));
            vendasPeriodoDTO.setFaturamentoTotalSemTaxa(round(vendasPeriodoDTO.getFaturamentoTotalSemTaxa() + dto.getValorTotalLiquido(), 2));
            vendasPeriodoDTO.setQuantidadeTotal(vendasPeriodoDTO.getQuantidadeTotal() + dto.getQuantidadeTotalVendas());
        }

        vendasPeriodoDTO.setProdutos(produtos);
        return vendasPeriodoDTO;
    }

    public PlataformaVendaDTO getVendasPorPlataforma(Long plataformaId, LocalDate startDate, LocalDate endDate) {
        LocalDate start = startDate != null ? startDate : LocalDate.EPOCH;
        LocalDate end = endDate != null ? endDate : LocalDate.now();

        PlataformaVendaDTO plataformaDTO = new PlataformaVendaDTO();

        plataformaDTO.setStartDate(start);
        plataformaDTO.setEndDate(end);

        List<Object[]> result = vendaRepository.findVendasPorPlataformaEProdutos(start, end, plataformaId);

        Plataforma plataforma = plataformaService.getPlataformaById(plataformaId);
        plataformaDTO.setPlataformaId(plataforma.getId());
        plataformaDTO.setPlataformaNome(plataforma.getNome());

        for (Object[] row : result) {

            ProdutoVendaDTO produtoDTO = new ProdutoVendaDTO();

            produtoDTO.setProdutoId((Long) row[2]);
            produtoDTO.setProdutoNome((String) row[3]);
            produtoDTO.setQuantidadeTotalVendas((Long) row[4]);
            produtoDTO.setValorTotalVendas(((Number) row[5]).doubleValue());
            produtoDTO.setValorTotalLiquido(((Number) row[6]).doubleValue());
            produtoDTO.setMediaValorUnitario(round(produtoDTO.getValorTotalVendas()/ produtoDTO.getQuantidadeTotalVendas(), 2));

            plataformaDTO.getProdutos().add(produtoDTO);
        }

        double valorTotalVendasPlataforma = plataformaDTO.getProdutos().stream()
                .mapToDouble(ProdutoVendaDTO::getValorTotalVendas)
                .sum();
        plataformaDTO.setValorTotalVendasPlataforma(Math.round(valorTotalVendasPlataforma * 100.0) / 100.0);

        double valorTotalLiquidoPlataforma = plataformaDTO.getProdutos().stream()
                .mapToDouble(ProdutoVendaDTO::getValorTotalLiquido)
                .sum();
        plataformaDTO.setValorTotalLiquidoPlataforma(Math.round(valorTotalLiquidoPlataforma * 100.0) / 100.0);

        Long quantidadeTotalItensPlataforma = plataformaDTO.getProdutos().stream()
                .mapToLong(ProdutoVendaDTO::getQuantidadeTotalVendas)
                .sum();
        plataformaDTO.setQuantidadeTotalItensPlataforma(quantidadeTotalItensPlataforma);

        return plataformaDTO;
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
