package br.com.verkom.marketwizard.backend.service;

import br.com.verkom.marketwizard.backend.exception.RecursoNaoEncontradoException;
import br.com.verkom.marketwizard.backend.exception.ViolacaoChaveEstrengeiraException;
import br.com.verkom.marketwizard.backend.model.Produto;
import br.com.verkom.marketwizard.backend.repository.CompraRepository;
import br.com.verkom.marketwizard.backend.repository.CotacaoRepository;
import br.com.verkom.marketwizard.backend.repository.ProdutoRepository;
import br.com.verkom.marketwizard.backend.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;
    private final CompraRepository compraRepository;
    private final VendaRepository vendaRepository;
    private final CotacaoRepository cotacaoRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaService categoriaService, CompraRepository compraRepository, VendaRepository vendaRepository, CotacaoRepository cotacaoRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaService = categoriaService;
        this.compraRepository = compraRepository;
        this.vendaRepository = vendaRepository;
        this.cotacaoRepository = cotacaoRepository;
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProdutoById(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()) {
            throw new RecursoNaoEncontradoException("Produto de id: " + id + " não encontrado.");
        }
        return produtoOptional.get();
    }

    public Produto createProduto(Produto produto) {
        produto.setCategoria(categoriaService.getCategoriaById(produto.getCategoria().getId()));
        return produtoRepository.save(produto);
    }

    public Produto updateProduto(Long id, Produto produtoDetails) {
        Produto produto = getProdutoById(id);
        produto.setCategoria(categoriaService.getCategoriaById(produtoDetails.getCategoria().getId()));
        produto.setNome(produtoDetails.getNome());
        produto.setSku(produtoDetails.getSku());
        produto.setDescricao(produtoDetails.getDescricao());
        produto.setQuantidade(produtoDetails.getQuantidade());
        return produtoRepository.save(produto);
    }

    public void deleteProduto(Long id) {
        Produto produto = getProdutoById(id);
        if(vendaRepository.existsByProdutoId(produto.getId())){
            throw new ViolacaoChaveEstrengeiraException("Existem vendas cadastradas com produto de id: " + id + ".");
        }
        if(compraRepository.existsByProdutoId(produto.getId())){
            throw new ViolacaoChaveEstrengeiraException("Existem compras cadastradas com produto de id: " + id + ".");
        }
        if(cotacaoRepository.existsByProdutoId(produto.getId())){
            throw new ViolacaoChaveEstrengeiraException("Existem cotações cadastradas com produto de id: " + id + ".");
        }
        produtoRepository.delete(produto);
    }

}
