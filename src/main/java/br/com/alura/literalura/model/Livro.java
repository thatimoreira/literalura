package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "livros")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("title")
    private String titulo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    @JsonProperty("authors")
    private Set<Autor> autores;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "idiomas",
            joinColumns = @JoinColumn(name = "livro_id")
    )
    @Column(name = "idioma")
    @JsonProperty("languages")
    private Set<String> idiomas;

    @Column(name = "numero_de_downloads")
    @JsonProperty("download_count")
    private Integer numeroDeDownloads;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    public Set<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Set<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Integer numeroDeDownloads) {
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
