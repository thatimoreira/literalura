package br.com.alura.literalura.client;

import br.com.alura.literalura.model.GutendexResponse;
import br.com.alura.literalura.model.Livro;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class GutendexClient {

        private final HttpClient httpClient;
        private final ObjectMapper objectMapper;
        private static final Logger LOGGER = Logger.getLogger(GutendexClient.class.getName());
        private final List<Livro> livrosInseridos = new ArrayList<>();  // Lista pra armazenar os livros inseridos

    public GutendexClient() {
        this.httpClient = HttpClient.newBuilder()
                            .version(HttpClient.Version.HTTP_2)
                            .followRedirects(HttpClient.Redirect.ALWAYS)
                            .build();
        this.objectMapper = new ObjectMapper();
    }

    public GutendexResponse buscarLivros(String query) throws IOException, InterruptedException {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        LOGGER.info("Encoded query: " + encodedQuery);
        String gutendexUrl = "https://gutendex.com/books?search=" + encodedQuery;
        HttpRequest request = HttpRequest.newBuilder()
                                .GET()
                                .uri(URI.create(gutendexUrl))
                                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        LOGGER.info("Response status code: " + response.statusCode());
        LOGGER.info("Response body: " + response.body());

        return objectMapper.readValue(response.body(), GutendexResponse.class);
    }

    public boolean inserirLivro(Livro livro) {
        try {
            String url = "https://gutendex.com/books";
            String json = objectMapper.writeValueAsString(livro);

            HttpRequest request = HttpRequest.newBuilder()
                                    .uri(URI.create(url))
                                    .POST(HttpRequest.BodyPublishers.ofString(json))
                                    .header("Content-Type", "application/json")
                                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return livrosInseridos.add(livro);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
