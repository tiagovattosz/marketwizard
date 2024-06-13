package br.com.verkom.marketwizard.backend.service;

import br.com.verkom.marketwizard.backend.exception.RecursoNaoEncontradoException;
import br.com.verkom.marketwizard.backend.model.Cotacao;
import br.com.verkom.marketwizard.backend.repository.CotacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class CotacaoService {

    private final CotacaoRepository cotacaoRepository;
    private final ProdutoService produtoService;

    public CotacaoService(CotacaoRepository cotacaoRepository, ProdutoService produtoService) {
        this.cotacaoRepository = cotacaoRepository;
        this.produtoService = produtoService;
    }

    public List<Cotacao> getAllCotacoes() {
        return cotacaoRepository.findAll();
    }

    public Cotacao getCotacaoById(Long id) {
        Optional<Cotacao> cotacaoOptional = cotacaoRepository.findById(id);
        if (cotacaoOptional.isEmpty()) {
            throw new RecursoNaoEncontradoException("Cotação de id: " + id + " não encontrada.");
        }
        return cotacaoOptional.get();
    }

    public Cotacao createCotacao(Cotacao cotacao) {
        cotacao.setLucro(calcularLucro(cotacao.getValorCompra(), cotacao.getValorVenda()));
        cotacao.setPorcentagemLucro(calcularPorcentagemLucro(cotacao.getValorCompra(), cotacao.getValorVenda()));
        cotacao.setProduto(produtoService.getProdutoById(cotacao.getProduto().getId()));

        return cotacaoRepository.save(cotacao);
    }

    public Cotacao updateCotacao(Long id, Cotacao cotacaoDetails) {
        Cotacao cotacao = getCotacaoById(id);
        cotacao.setProduto(produtoService.getProdutoById(cotacaoDetails.getProduto().getId()));

        cotacao.setValorCompra(cotacaoDetails.getValorCompra());
        cotacao.setValorVenda(cotacaoDetails.getValorVenda());
        cotacao.setLucro(calcularLucro(cotacao.getValorCompra(), cotacao.getValorVenda()));
        cotacao.setPorcentagemLucro(calcularPorcentagemLucro(cotacao.getValorCompra(), cotacao.getValorVenda()));

        return cotacaoRepository.save(cotacao);
    }

    public void deleteCotacao(Long id) {
        Cotacao cotacao = getCotacaoById(id);
        cotacaoRepository.delete(cotacao);
    }

    private BigDecimal calcularLucro(BigDecimal valorCompra, BigDecimal valorVenda) {
        return valorVenda.subtract(valorCompra);
    }

    private BigDecimal calcularPorcentagemLucro(BigDecimal valorCompra, BigDecimal valorVenda) {
        if (valorCompra.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return valorVenda.subtract(valorCompra)
                .divide(valorCompra, 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }
}
