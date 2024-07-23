// Classe de serviço que consome a API Gutendex
package br.com.alura.literalura.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;

    public GutendexService() {
        this.restTemplate = new RestTemplate();
    }

    public String buscarLivros(String query) {
        String gutendexUrl = "https://gutendex.com/books?search=" + query;

        return restTemplate.getForObject(gutendexUrl, String.class);
    }
}
