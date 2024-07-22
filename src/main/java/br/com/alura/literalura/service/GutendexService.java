// Essa classe consume a API Gutendex e fornece os dados para o controller
package br.com.alura.literalura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import br.com.alura.literalura.model.GutendexResponse;

@Service
public class GutendexService {
    private static final String GUTENDEX_API_URL = "https://gutendex.com/books/";

    @Autowired
    private RestTemplate restTemplate;

    public GutendexResponse getbooks() {
        return restTemplate.getForObject(GUTENDEX_API_URL, GutendexResponse.class);
    }
}
