package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("name")
    @Column(nullable = false)
    private String nome;

    @JsonProperty("birth_year")
    @Column(name = "ano_de_nascimento")
    private int anoDeNascimento;

    @JsonProperty("death_year")
    @Column(name = "ano_de_obito")
    private Integer anoDeObito;

    @ManyToMany(mappedBy = "autores")
    private List<Livro> livros;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public boolean isVivoAno(int ano) {
        return (anoDeNascimento <= ano) && (anoDeObito == null || anoDeObito >= ano);
    }

    @Override
    public String toString() {
        return "\n                     Autores(as) { " +
                "nome: " + nome + " | " +
                "ano de nascimento: " + anoDeNascimento + " | " +
                "ano de óbito: " + anoDeObito +
                " } ";
    }
}
