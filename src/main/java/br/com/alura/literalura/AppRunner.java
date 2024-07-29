package br.com.alura.literalura;

import br.com.alura.literalura.model.GutendexResponse;
import br.com.alura.literalura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private GutendexService gutendexService;

    @Override
    public void run(String... args) throws Exception {
        GutendexResponse response = gutendexService.buscarLivros("tolstoy", null);
        System.out.println(response);
    }
}
