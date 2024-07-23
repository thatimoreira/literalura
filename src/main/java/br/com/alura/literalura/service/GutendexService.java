// Classe de serviço que consome a API Gutendex
package br.com.alura.literalura.service;

import br.com.alura.literalura.client.GutendexClient;
import br.com.alura.literalura.model.GutendexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GutendexService {

    @Autowired
    private GutendexClient gutendexClient;

    public GutendexResponse buscarLivros(String query) {
        try {
            return gutendexClient.buscarLivros(query);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            return null;
        }
    }
}
