package br.com.alura.literalura.controller;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.GutendexResponse;
import br.com.alura.literalura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.List;

@RestController
public class LivroController {
    @Autowired
    private GutendexService gutendexService;

    @GetMapping(value = {"/books", "/books/"})
    public GutendexResponse getLivros(@RequestParam(name = "search", required = false) String searchQuery,
                                      @RequestParam(name = "languages", required = false) String language) {
        return gutendexService.buscarLivros(searchQuery, language);
    }
    @GetMapping("books/countByLanguage")
    public long contarLivrosPorIdioma(@RequestParam(name = "language", required = false) String language) {
        return gutendexService.contarLivrosPorIdioma(language);
    }

    @GetMapping("authors/aliveInYear")
    public List<Autor> listarAutoresVivosNoAno(@RequestParam(name = "year", required = false) Integer year) {
        return gutendexService.listarAutoresVivosNoAno(year);
    }
}
