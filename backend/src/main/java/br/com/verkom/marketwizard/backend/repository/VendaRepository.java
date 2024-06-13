package br.com.verkom.marketwizard.backend.repository;

import br.com.verkom.marketwizard.backend.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    Boolean existsByPlataformaId(Long id);

    Boolean existsByProdutoId(Long id);

    @Query("SELECT p.id, p.nome, SUM(v.quantidade), SUM(v.valorTotal), SUM(v.valorLiquido) " +
            "FROM Produto p " +
            "JOIN Venda v ON p.id = v.produto.id " +
            "WHERE v.dataVenda BETWEEN :startDate AND :endDate " +
            "GROUP BY p.id, p.nome " +
            "ORDER BY SUM(v.quantidade) DESC")
    List<Object[]> findVendasPorProduto(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT pl.id, pl.nome, p.id, p.nome, SUM(v.quantidade), SUM(v.valorTotal), SUM(v.valorLiquido) " +
            "FROM Plataforma pl " +
            "JOIN Venda v ON pl.id = v.plataforma.id " +
            "JOIN Produto p ON v.produto.id = p.id " +
            "WHERE v.dataVenda BETWEEN :startDate AND :endDate AND pl.id = :plataformaId " +
            "GROUP BY pl.id, pl.nome, p.id, p.nome " +
            "ORDER BY pl.id, SUM(v.quantidade) DESC")
    List<Object[]> findVendasPorPlataformaEProdutos(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("plataformaId") Long plataformaId);

    @Query("SELECT p.id, p.nome, SUM(v.quantidade), SUM(v.valorTotal), SUM(v.valorLiquido) " +
            "FROM Produto p " +
            "JOIN Venda v ON p.id = v.produto.id " +
            "WHERE v.dataVenda BETWEEN :startDate AND :endDate " +
            "GROUP BY p.id, p.nome " +
            "ORDER BY SUM(v.quantidade) DESC")
    List<Object[]> findVendasPorPeriodo(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}