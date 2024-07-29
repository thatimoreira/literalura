package br.com.alura.literalura.controller;

import br.com.alura.literalura.model.GutendexResponse;
import br.com.alura.literalura.service.GutendexService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivroController {
    @Autowired
    private GutendexService gutendexService;

    @GetMapping(value = {"/books", "/books/"})
    public GutendexResponse getLivros(@RequestParam(name = "search", required = false) String searchQuery,
                                      @RequestParam(name = "languages", required = false) String language) {
        return gutendexService.buscarLivros(searchQuery, language);
    }
}
