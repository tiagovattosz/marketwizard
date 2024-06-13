//package br.com.verkom.marketwizard.backend.model;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Objects;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "usuario")
//public class Usuario {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String nome;
//
//    @Column(nullable = false, unique = true)
//    private String login;
//
//    @Column(nullable = false)
//    private String senha;
//
//    @Column(nullable = false)
//    private String email;
//
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        Usuario other = (Usuario) obj;
//        return Objects.equals(id, other.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//}