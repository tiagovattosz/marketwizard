package br.com.verkom.marketwizard.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "plataforma")
public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "O campo nome não pode estar em branco.")
    private String nome;

    @Column(name = "taxa", nullable = false)
    @NotNull(message = "O campo taxa não pode ser nulo.")
    @DecimalMin(value = "0.0", inclusive = false, message = "A taxa deve ser positiva.")
    @Digits(integer = 5, fraction = 2, message = "O valor da taxa deve ser um número decimal com até duas casas decimais.")
    private BigDecimal taxa;
}