// Classe de serviço que consome a API Gutendex
package br.com.alura.literalura.service;

import br.com.alura.literalura.client.GutendexClient;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.GutendexResponse;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GutendexService {

//    private final List<Livro> livrosInseridos = new ArrayList<>();
    private final AtomicInteger idCounter;
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final GutendexClient gutendexClient;

    @Autowired
    private GutendexService(GutendexClient gutendexClient, LivroRepository livroRepository, AutorRepository autorRepository) {
        this.gutendexClient = gutendexClient;
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.idCounter = new AtomicInteger(buscarUltimoID() + 1);
    }

    private int buscarUltimoID() {
        return livroRepository.findTopByOrderByIdDesc()
                .map(Livro::getId)
                .orElse(0);
    }

    public GutendexResponse buscarLivros(String query) {
        try {
            GutendexResponse response = gutendexClient.buscarLivros(query);

            if (response != null && !response.getResults().isEmpty()) {
                Livro livro = response.getResults().get(0);
                autorRepository.saveAll(livro.getAutores()); // Salva todos os autores primeiro
                livroRepository.save(livro);
            }
            return response;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            return null;
        }
    }

    public boolean inserirNovoLivro(String titulo, List<Autor> autores) {
        Livro novoLivro = new Livro();
        novoLivro.setId(idCounter.getAndIncrement());
        novoLivro.setTitulo(titulo);

        autorRepository.saveAll(autores); // Salvar todos os autores de uma vez

        novoLivro.setAutores(autores);
        livroRepository.save(novoLivro); // Persistir no banco de dados

        return true;
    }

    public List<Livro> listarTodosLivrosComAutores() {
        return livroRepository.findAll();
    }

    public List<Livro> listarLivrosPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
//        List<Livro> resultado = new ArrayList<>();
//        for (Livro livro : livrosInseridos) {
//            if (livro.getIdiomas() != null && !livro.getIdiomas().isEmpty()) {
//                if (livro.getIdiomas().get(0).equalsIgnoreCase(idioma)) {
//                    resultado.add(livro);
//                }
//            }
//        }
//        return resultado;
        return livroRepository.findByIdiomasContaining(idioma);
    }
}
