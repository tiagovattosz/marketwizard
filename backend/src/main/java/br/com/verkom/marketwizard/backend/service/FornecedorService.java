package br.com.verkom.marketwizard.backend.service;

import br.com.verkom.marketwizard.backend.exception.RecursoNaoEncontradoException;
import br.com.verkom.marketwizard.backend.exception.ViolacaoChaveEstrengeiraException;
import br.com.verkom.marketwizard.backend.model.Fornecedor;
import br.com.verkom.marketwizard.backend.repository.CompraRepository;
import br.com.verkom.marketwizard.backend.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final CompraRepository compraRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository, CompraRepository compraRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.compraRepository = compraRepository;
    }

    public List<Fornecedor> getAllFornecedores() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor getFornecedorById(Long id) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);
        if (fornecedorOptional.isEmpty()) {
            throw new RecursoNaoEncontradoException("Fornecedor de id: " + id + " não encontrado.");
        }
        return fornecedorOptional.get();
    }

    public Fornecedor createFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor updateFornecedor(Long id, Fornecedor fornecedorDetails) {
        Fornecedor fornecedor = getFornecedorById(id);
        fornecedor.setNome(fornecedorDetails.getNome());
        return fornecedorRepository.save(fornecedor);
    }

    public void deleteFornecedor(Long id) {
        Fornecedor fornecedor = getFornecedorById(id);
        if (compraRepository.existsByFornecedorId(fornecedor.getId())){
            throw new ViolacaoChaveEstrengeiraException("Existem compras cadastrados com fornecedor de id: " + id + ".");
        }
        fornecedorRepository.delete(fornecedor);
    }

}
