package br.com.alura.literalura.service;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.GutendexResponse;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GutendexService {
    private static final    Logger logger = LoggerFactory.getLogger(GutendexService.class);
    private final ApiClient apiClient;
    private final ObjectMapper objectMapper;
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public GutendexService(ApiClient apiClient, LivroRepository livroRepository, AutorRepository autorRepository) {
        this.apiClient = apiClient;
        this.objectMapper = new ObjectMapper();
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
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

                if (primeiroLivro.getAutores() != null && !primeiroLivro.getAutores().isEmpty()) {
                    Autor primeiroAutor = primeiroLivro.getAutores().iterator().next();
                    autorRepository.save(primeiroAutor);
                }
                livroRepository.save(primeiroLivro);
            }

            return gutendexResponse;
        } catch (IOException | InterruptedException e) {
            logger.error("Erro ao fazer a solicitação HTTP", e);

            return null;
        }
    }

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdiomasContaining(idioma);
    }

    public List<Livro> listarLivrosPesquisados() {
        return livroRepository.findAll();
    }

    public List<Autor> listarTodosAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosNoAno(Integer ano) {
        return autorRepository.findAutoresVivosNoAno(ano);
    }

    public long contarLivrosPorIdioma(String idioma) {
        return livroRepository.countByIdioma(idioma);
    }
}
