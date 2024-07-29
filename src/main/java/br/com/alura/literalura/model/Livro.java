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

    @JsonProperty("languages")
    private List<String> idiomas;

    @JsonProperty("download_count")
    private int numeroDeDownloads;

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

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public int getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(int numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }

    @Override
    public String toString() {
        return "\n              Livro { " +
                "id: " + id + " | " +
                "título: '" + titulo + "' | " +
                "autores(as): " + autores + "\n                          | " +
                "idiomas: " + idiomas + " | " +
                "nº de downloads: " + numeroDeDownloads +
                " }";
    }
}
