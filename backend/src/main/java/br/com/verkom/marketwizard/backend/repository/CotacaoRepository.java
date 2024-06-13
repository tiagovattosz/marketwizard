package br.com.verkom.marketwizard.backend.repository;

import br.com.verkom.marketwizard.backend.model.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
    Boolean existsByProdutoId(Long id);
}