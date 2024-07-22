// Classe  responsável por mapear as requisições HTTP para os métodos de serviço, expor o endpoint da API
package br.com.alura.literalura.controller;

import br.com.alura.literalura.model.GutendexResponse;
import br.com.alura.literalura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/books")
public class GutendexController {

    @Autowired
    private GutendexService gutendexService;

    @GetMapping
    public GutendexResponse getBooks() {
        return gutendexService.getbooks();
    }
}
