package br.com.verkom.marketwizard.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "cotacao")
public class Cotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    @NotNull(message = "O campo produto não deve ser nulo.")
    private Produto produto;

    @Column(name = "valor_compra", nullable = false)
    @DecimalMin(value = "0.0", message = "O valor deve ser positivo.")
    @NotNull(message = "O campo valor compra não deve ser nulo.")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valorCompra;

    @Column(name = "valor_venda", nullable = false)
    @DecimalMin(value = "0.0", message = "O valor deve ser positivo.")
    @NotNull(message = "O campo valor venda não deve ser nulo.")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valorVenda;

    @Column(name = "lucro")
    private BigDecimal lucro;

    @Column(name = "porcentagem_lucro")
    private BigDecimal porcentagemLucro;

}