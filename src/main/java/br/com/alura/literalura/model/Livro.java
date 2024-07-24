package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Livro {
    private int id;

    @JsonProperty("title")
    private String titulo;

    @JsonProperty("authors")
    private List<Autor> autores;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id: " + id +
                ", título: '" + titulo + '\'' +
                ", autores: " + autores +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Autor {
        @JsonProperty("name")
        private String nome;

        @JsonProperty("birth_year")
        private int anoDeNascimento;

        @JsonProperty("death_year")
        private Integer anoDeObito;

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
}
