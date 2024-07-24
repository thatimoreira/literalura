// Classe de serviço que consome a API Gutendex
package br.com.alura.literalura.service;

import br.com.alura.literalura.client.GutendexClient;
import br.com.alura.literalura.model.GutendexResponse;
import br.com.alura.literalura.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GutendexService {

    private final AtomicInteger idCounter = new AtomicInteger(1);

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

    public boolean inserirNovoLivro(String titulo, List<Livro.Autor> autores) {
        Livro novoLivro = new Livro();
        novoLivro.setId(idCounter.getAndIncrement());
        novoLivro.setTitulo(titulo);
        novoLivro.setAutores(autores);

        return gutendexClient.inserirLivro(novoLivro);
    }
}
