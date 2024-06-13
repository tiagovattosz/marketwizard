package br.com.verkom.marketwizard.backend.service;

import br.com.verkom.marketwizard.backend.exception.RecursoNaoEncontradoException;
import br.com.verkom.marketwizard.backend.exception.ViolacaoChaveEstrengeiraException;
import br.com.verkom.marketwizard.backend.model.Categoria;
import br.com.verkom.marketwizard.backend.repository.CategoriaRepository;
import br.com.verkom.marketwizard.backend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria getCategoriaById(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isEmpty()) {
            throw new RecursoNaoEncontradoException("Categoria de id: " + id + " não encontrada.");
        }
        return categoriaOptional.get();
    }

    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Long id, Categoria categoriaDetails) {
        Categoria categoria = getCategoriaById(id);
        categoria.setNome(categoriaDetails.getNome());
        return categoriaRepository.save(categoria);
    }

    public void deleteCategoria(Long id) {
        Categoria categoria = getCategoriaById(id);
        if (produtoRepository.existsByCategoriaId(categoria.getId())){
            throw new ViolacaoChaveEstrengeiraException("Existem produtos cadastrados com categoria de id: " + id + ".");
        }
        categoriaRepository.delete(categoria);
    }

}
