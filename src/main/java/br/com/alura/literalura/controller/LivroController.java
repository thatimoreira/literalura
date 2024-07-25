package br.com.alura.literalura.controller;

import br.com.alura.literalura.model.GutendexResponse;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LivroController {

    @Autowired
    private GutendexService gutendexService;

    @GetMapping("/api/livros")
    public String buscarLivros(@RequestParam String query) {
        GutendexResponse response = gutendexService.buscarLivros(query);

        if (response != null){
            return response.toString();
        }
        return "Nenhum dado encontrado!";
    }

    @GetMapping("/api/livros/todos")
    public List<Livro> listarTodosLivros() {
        return gutendexService.listarTodosLivrosComAutores();
    }

    @GetMapping("/api/livros/idioma")
    public List<Livro> listarLivrosPorIdioma(@RequestParam String idioma) {
        return gutendexService.listarLivrosPorIdioma(idioma);
    }
}
