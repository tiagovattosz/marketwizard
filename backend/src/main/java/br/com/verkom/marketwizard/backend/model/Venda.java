package br.com.verkom.marketwizard.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "venda")
@EntityListeners(AuditingEntityListener.class)
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plataforma_id", nullable = false)
    @NotNull(message = "O campo plataforma não deve ser nulo.")
    private Plataforma plataforma;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    @NotNull(message = "O campo produto não deve ser nulo.")
    private Produto produto;

    @Column(name = "data_venda", nullable = false)
    @CreatedDate
    private LocalDate dataVenda;

    @Column(name = "quantidade", nullable = false)
    @Min(value = 1, message = "A quantidade deve ser maior que zero.")
    @NotNull(message = "O campo quantidade não deve ser nulo.")
    private Integer quantidade;

    @Column(name = "valor_unidade", nullable = false)
    @DecimalMin(value = "0.0", message = "O valor deve ser positivo.")
    @NotNull(message = "O campo valor unidade não deve ser nulo.")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valorUnidade;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "valor_liquido")
    private BigDecimal valorLiquido;

}