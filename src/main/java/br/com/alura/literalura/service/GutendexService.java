package br.com.alura.literalura.service;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.GutendexResponse;
import br.com.alura.literalura.model.Livro;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GutendexService {
    private static final    Logger logger = LoggerFactory.getLogger(GutendexService.class);
    private final ApiClient apiClient;
    private final ObjectMapper objectMapper;
    private final List<Livro> catalogoLivros;
    private final List<Autor> catalogoAutores;

    public GutendexService(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.objectMapper = new ObjectMapper();
        this.catalogoLivros = new ArrayList<>();
        this.catalogoAutores = new ArrayList<>();
    }

    public GutendexResponse buscarLivros(String searchQuery, String language) {
        // Constrói a URL base
        StringBuilder urlBuilder = new StringBuilder("https://gutendex.com/books/?");

        // Adiciona os parÂmetros de consulta quando presentes
        if (searchQuery != null && !searchQuery.isEmpty()) {
            urlBuilder.append("search=").append(ApiClient.encodeValue(searchQuery)).append("&");
        }
        if (language != null && !language.isEmpty()) {
            urlBuilder.append("languages=").append(ApiClient.encodeValue(language)).append("&");
        }

        // Quando precisar, remover o último '&'
        String url;
        if (urlBuilder.toString().endsWith("&"))
        {
            url = urlBuilder.substring(0, urlBuilder.length() - 1);
        } else {
            url = urlBuilder.toString();
        }
        logger.info("Solicitando URL: {}", url);

        try {
            String response = apiClient.get(url);
            logger.info("Response da API Gutendex: {}", response);

            // Checar se a resposta está vazia
            if (response == null || response.isEmpty()) {
                logger.warn("Resposta vazia da API Gutendex");
                return null;
            }

            // Converter json para objeto GutendexResponse
            GutendexResponse gutendexResponse = objectMapper.readValue(response, GutendexResponse.class);
            logger.info("Response convertida: {}", gutendexResponse);

            if (gutendexResponse != null &&
                    gutendexResponse.getResultados() != null &&
                    !gutendexResponse.getResultados().isEmpty()) {
                Livro primeiroLivro = gutendexResponse.getResultados().get(0);
                catalogoLivros.add(primeiroLivro);

                if (primeiroLivro.getAutores() != null && !primeiroLivro.getAutores().isEmpty()) {
                    Autor primeiroAutor = primeiroLivro.getAutores().get(0);
                    catalogoAutores.add(primeiroAutor);
                }
            }

            return gutendexResponse;
        } catch (IOException | InterruptedException e) {
            logger.error("Erro ao fazer a solicitação HTTP", e);

            return null;
        }
    }

    public List<Livro> listarTodosLivros() {
        return new ArrayList<>(catalogoLivros);
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return catalogoLivros.stream()
                .filter(livro -> livro.getIdiomas() != null &&
                        !livro.getIdiomas().isEmpty() &&
                        livro.getIdiomas().get(0).equals(idioma))
                .collect(Collectors.toList());
    }

    public List<Livro> listarLivrosPesquisados() {
        return new ArrayList<>(catalogoLivros);
    }

    public List<Autor> listarTodosAutores() {
        return new ArrayList<>(catalogoAutores);
    }

    public List<Autor> listarAutoresVivosNoAno(int ano) {
        return catalogoAutores.stream()
                .filter(autor -> autor.isVivoAno(ano))
                .collect(Collectors.toList());
    }
}
