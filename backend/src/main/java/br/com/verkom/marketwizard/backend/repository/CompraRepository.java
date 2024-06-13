package br.com.verkom.marketwizard.backend.repository;

import br.com.verkom.marketwizard.backend.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

     Boolean existsByFornecedorId(Long id);
     Boolean existsByProdutoId(Long id);
     List<Compra> findByChegou(Boolean chegou);

     @Query("SELECT p.id, p.nome, SUM(c.quantidade), SUM(c.valorTotal) " +
             "FROM Produto p " +
             "JOIN Compra c ON p.id = c.produto.id " +
             "WHERE c.dataCompra BETWEEN :startDate AND :endDate " +
             "GROUP BY p.id, p.nome " +
             "ORDER BY SUM(c.quantidade) DESC")
     List<Object[]> findComprasPorProduto(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

     @Query("SELECT f.id, f.nome, p.id, p.nome, SUM(c.quantidade), SUM(c.valorTotal) " +
             "FROM Fornecedor f " +
             "JOIN Compra c ON f.id = c.fornecedor.id " +
             "JOIN Produto p ON c.produto.id = p.id " +
             "WHERE c.dataCompra BETWEEN :startDate AND :endDate AND f.id = :fornecedorId " +
             "GROUP BY f.id, f.nome, p.id, p.nome " +
             "ORDER BY f.id, SUM(c.quantidade) DESC")
     List<Object[]> findComprasPorFornecedorEProdutos(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("fornecedorId") Long fornecedorId);

     @Query("SELECT p.id, p.nome, SUM(c.quantidade), SUM(c.valorTotal) " +
             "FROM Produto p " +
             "JOIN Compra c ON p.id = c.produto.id " +
             "WHERE c.dataCompra BETWEEN :startDate AND :endDate " +
             "GROUP BY p.id, p.nome " +
             "ORDER BY SUM(c.quantidade) DESC")
     List<Object[]> findComprasPorPeriodo(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}