package br.com.verkom.marketwizard.backend.service;

import br.com.verkom.marketwizard.backend.exception.RecursoNaoEncontradoException;
import br.com.verkom.marketwizard.backend.exception.ViolacaoChaveEstrengeiraException;
import br.com.verkom.marketwizard.backend.model.Plataforma;
import br.com.verkom.marketwizard.backend.repository.PlataformaRepository;
import br.com.verkom.marketwizard.backend.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlataformaService {

    private final PlataformaRepository plataformaRepository;
    private final VendaRepository vendaRepository;

    public PlataformaService(PlataformaRepository plataformaRepository, VendaRepository vendaRepository) {
        this.plataformaRepository = plataformaRepository;
        this.vendaRepository = vendaRepository;
    }

    public List<Plataforma> getAllPlataformas() {
        return plataformaRepository.findAll();
    }

    public Plataforma getPlataformaById(Long id) {
        Optional<Plataforma> plataformaOptional = plataformaRepository.findById(id);
        if (plataformaOptional.isEmpty()) {
            throw new RecursoNaoEncontradoException("Plataforma de id: " + id + " não encontrada.");
        }
        return plataformaOptional.get();
    }

    public Plataforma createPlataforma(Plataforma plataforma) {
        return plataformaRepository.save(plataforma);
    }

    public Plataforma updatePlataforma(Long id, Plataforma plataformaForm) {
        Plataforma plataforma = getPlataformaById(id);
        plataforma.setNome(plataformaForm.getNome());
        plataforma.setTaxa(plataformaForm.getTaxa());
        return plataformaRepository.save(plataforma);
    }

    public void deletePlataforma(Long id) {
        Plataforma plataforma = getPlataformaById(id);
        if (vendaRepository.existsByPlataformaId(plataforma.getId())){
            throw new ViolacaoChaveEstrengeiraException("Existem vendas cadastrados com plataforma de id: " + id + ".");
        }
        plataformaRepository.delete(plataforma);

    }

}
