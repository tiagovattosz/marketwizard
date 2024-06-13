package br.com.verkom.marketwizard.backend.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import br.com.verkom.marketwizard.backend.dto.ComprasPeriodoDTO;
import br.com.verkom.marketwizard.backend.dto.FornecedorCompraDTO;
import br.com.verkom.marketwizard.backend.dto.ProdutoCompraDTO;
import br.com.verkom.marketwizard.backend.exception.RecursoNaoEncontradoException;
import br.com.verkom.marketwizard.backend.model.Compra;
import br.com.verkom.marketwizard.backend.model.Fornecedor;
import br.com.verkom.marketwizard.backend.repository.CompraRepository;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final FornecedorService fornecedorService;
    private final ProdutoService produtoService;

    public CompraService(CompraRepository compraRepository, FornecedorService fornecedorService, ProdutoService produtoService) {
        this.compraRepository = compraRepository;
        this.fornecedorService = fornecedorService;
        this.produtoService = produtoService;
    }

    public List<Compra> getAllCompras() {
        return compraRepository.findAll();
    }

    public Compra getCompraById(Long id) {
        Optional<Compra> compraOptional = compraRepository.findById(id);
        if (compraOptional.isEmpty()){
            throw new RecursoNaoEncontradoException("Compra de id: " + id + " não encontrada.");
        }
        return compraOptional.get();
    }

    public Compra createCompra(Compra compra) {
        compra.setFornecedor(fornecedorService.getFornecedorById(compra.getFornecedor().getId()));
        compra.setProduto(produtoService.getProdutoById(compra.getProduto().getId()));
        calcularValores(compra);
        return compraRepository.save(compra);
    }

    public Compra updateCompra(Long id, Compra compraDetails) {
        Compra compra = getCompraById(id);
        compra.getProduto().setQuantidade(compra.getProduto().getQuantidade() - compra.getQuantidade());

        compra.setFornecedor(fornecedorService.getFornecedorById(compraDetails.getFornecedor().getId()));
        compra.setProduto(produtoService.getProdutoById(compraDetails.getProduto().getId()));
        compra.setDataCompra(compraDetails.getDataCompra());
        compra.setValorUnidade(compraDetails.getValorUnidade());
        compra.setQuantidade(compraDetails.getQuantidade());
        compra.setChegou(compraDetails.getChegou());

        if(compra.getChegou()){
            compra.getProduto().setQuantidade(compra.getProduto().getQuantidade() + compra.getQuantidade());
        }

        calcularValores(compra);
        return compraRepository.save(compra);
    }

    public void deleteCompra(Long id) {
        Compra compra = getCompraById(id);
        compra.getProduto().setQuantidade(compra.getProduto().getQuantidade() - compra.getQuantidade());
        compraRepository.delete(compra);
    }

    public List<Compra> listarComprasQueChegaram() {
        return compraRepository.findByChegou(true);
    }

    public List<Compra> listarComprasQueNaoChegaram() {
        return compraRepository.findByChegou(false);
    }

    public Compra confirmarRecebimento(Long id) {
        Optional<Compra> compraOptional = compraRepository.findById(id);
        if (compraOptional.isEmpty()){
            throw new RecursoNaoEncontradoException("Compra de id: " + id + " não encontrada.");
        }
        Compra compra = compraOptional.get();
        compra.setChegou(true);
        compra.getProduto().setQuantidade(compra.getProduto().getQuantidade() + compra.getQuantidade());
        return compraRepository.save(compra);
    }

    private void calcularValores(Compra compra) {
        BigDecimal valorTotal = compra.getValorUnidade().multiply(BigDecimal.valueOf(compra.getQuantidade()));
        compra.setValorTotal(valorTotal);
    }

    public ComprasPeriodoDTO getComprasPorProduto(LocalDate startDate, LocalDate endDate) {
        LocalDate start = startDate != null ? startDate : LocalDate.EPOCH;
        LocalDate end = endDate != null ? endDate : LocalDate.now();

        ComprasPeriodoDTO comprasPeriodoDTO = new ComprasPeriodoDTO();
        comprasPeriodoDTO.setStartDate(start);
        comprasPeriodoDTO.setEndDate(end);

        List<Object[]> result = compraRepository.findComprasPorProduto(start, end);
        List<ProdutoCompraDTO> produtos = new ArrayList<>();

        for (Object[] row : result) {
            ProdutoCompraDTO dto = new ProdutoCompraDTO();

            dto.setProdutoId((Long) row[0]);
            dto.setProdutoNome((String) row[1]);
            dto.setQuantidadeTotalCompras((Long) row[2]);
            dto.setValorTotalCompras(((Number) row[3]).doubleValue());
            dto.setMediaValorUnitario(round(dto.getValorTotalCompras() / dto.getQuantidadeTotalCompras(), 2));

            produtos.add(dto);

            comprasPeriodoDTO.setGastoTotal(round(comprasPeriodoDTO.getGastoTotal() + dto.getValorTotalCompras(), 2));
            comprasPeriodoDTO.setQuantidadeTotal(comprasPeriodoDTO.getQuantidadeTotal() + dto.getQuantidadeTotalCompras());

        }

        comprasPeriodoDTO.setProdutos(produtos);

        return comprasPeriodoDTO;
    }

    public FornecedorCompraDTO getComprasPorFornecedor(Long fornecedorId, LocalDate startDate, LocalDate endDate) {
        LocalDate start = startDate != null ? startDate : LocalDate.EPOCH;
        LocalDate end = endDate != null ? endDate : LocalDate.now();

        FornecedorCompraDTO fornecedorDTO = new FornecedorCompraDTO();

        fornecedorDTO.setStartDate(start);
        fornecedorDTO.setEndDate(end);

        Fornecedor fornecedor = fornecedorService.getFornecedorById(fornecedorId);
        fornecedorDTO.setFornecedorId(fornecedor.getId());
        fornecedorDTO.setFornecedorNome(fornecedor.getNome());

        List<Object[]> result = compraRepository.findComprasPorFornecedorEProdutos(start, end, fornecedorId);
        for (Object[] row : result) {
            fornecedorDTO.setFornecedorId((Long) row[0]);
            fornecedorDTO.setFornecedorNome((String) row[1]);

            ProdutoCompraDTO produtoDTO = new ProdutoCompraDTO();

            produtoDTO.setProdutoId((Long) row[2]);
            produtoDTO.setProdutoNome((String) row[3]);
            produtoDTO.setQuantidadeTotalCompras((Long) row[4]);
            produtoDTO.setValorTotalCompras(((Number) row[5]).doubleValue());
            produtoDTO.setMediaValorUnitario(round(produtoDTO.getValorTotalCompras()/produtoDTO.getQuantidadeTotalCompras(), 2));

            fornecedorDTO.getProdutos().add(produtoDTO);
        }

        double valorTotalComprasFornecedor = fornecedorDTO.getProdutos().stream()
                .mapToDouble(ProdutoCompraDTO::getValorTotalCompras)
                .sum();
        fornecedorDTO.setValorTotalCompraFornecedor(round(valorTotalComprasFornecedor, 2) * 100.0 / 100.0);

        Long quantidadeTotalItensFornecedor = fornecedorDTO.getProdutos().stream()
                .mapToLong(ProdutoCompraDTO::getQuantidadeTotalCompras)
                .sum();
        fornecedorDTO.setQuantidadeTotalItensFornecedor(quantidadeTotalItensFornecedor);

        return fornecedorDTO;
    }

    public static double round(double value, int places) {
        return VendaService.round(value, places);
    }

}
