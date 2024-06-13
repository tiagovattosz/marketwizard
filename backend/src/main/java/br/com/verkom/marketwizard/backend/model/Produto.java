package br.com.verkom.marketwizard.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @NotNull(message = "O campo categoria não deve ser nulo.")
    private Categoria categoria;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "O campo nome não pode estar em branco.")
    private String nome;

    @Column(name = "sku", length = 50)
    private String sku;

    @Column(name = "descricao", length = 1000)
    private String descricao;

    @Column(name = "quantidade_estoque")
    @Min(value = 0, message = "A quantidade deve ser positiva.")
    @NotNull(message = "O campo quantidade não deve ser nulo.")
    private Integer quantidade = 0;

}