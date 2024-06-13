package br.com.verkom.marketwizard.backend.repository;

import br.com.verkom.marketwizard.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

     Boolean existsByCategoriaId(Long id);


}