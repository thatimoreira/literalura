package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties
public class GutendexResponse {
    private int count;

    @JsonProperty("next")
    private String proximo;

    @JsonProperty("previous")
    private String anterior;

    @JsonProperty("results")
    private List<Livro> resultados;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getProximo() {
        return proximo;
    }

    public void setProximo(String proximo) {
        this.proximo = proximo;
    }

    public String getAnterior() {
        return anterior;
    }

    public void setAnterior(String anterior) {
        this.anterior = anterior;
    }

    public List<Livro> getResultados() {
        return resultados;
    }

    public void setResultados(List<Livro> resultados) {
        this.resultados = resultados;
    }

    @Override
    public String toString() {
        return "GutendexResponse {\n" +
                "count: " + count + ",\n" +
                "próximo: " + proximo + ",\n" +
                "anterior: " + anterior + ",\n" +
                "resultados: " + resultados +
                '}';
    }
}
