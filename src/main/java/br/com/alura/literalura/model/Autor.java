package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    @Column(nullable = false)
    private String nome;

    @JsonProperty("birth_year")
    @Column(name = "ano_de_nascimento")
    private int anoDeNascimento;

    @JsonProperty("death_year")
    @Column(name = "ano_de_obito")
    private Integer anoDeObito;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(int anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public Integer getAnoDeObito() {
        return anoDeObito;
    }

    public void setAnoDeObito(Integer anoDeObito) {
        this.anoDeObito = anoDeObito;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nome: '" + nome + '\''+
                ", ano de nascimento: " + anoDeNascimento +
                ", ano de óbito: " + anoDeObito +
                '}';
    }
}

