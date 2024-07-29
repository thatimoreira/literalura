package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {
    @JsonProperty("name")
    private String nome;

    @JsonProperty("birth_year")
    private int anoDeNascimento;

    @JsonProperty("death_year")
    private int anoDeObito;

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

    public int getAnoDeObito() {
        return anoDeObito;
    }

    public void setAnoDeObito(int anoDeObito) {
        this.anoDeObito = anoDeObito;
    }

    public boolean isVivoAno(int ano) {
        return (anoDeNascimento <= ano) && (anoDeObito == 0 || anoDeObito >= ano);
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
