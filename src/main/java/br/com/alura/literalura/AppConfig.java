// Classe de configuração no Spring que define um bean RestTemplate para ser usado na
// aplicação para realizar chamadas HTTP
package br.com.alura.literalura;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration //  Indica que a classe tem um ou mais métodos @Bean  e pode ser processada pelo
// Spring Container para gerar definições de beans e requisitar os serviços desses beans em tempo de execução
public class AppConfig {

    @Bean // Indica que o método com essa anotação retornará um objeto que deve ser registrado como um bean no
    // Spring Application Context
    public RestTemplate restTemplate() { // Cria um bean RestTemplate que será gerenciado pelo Spring
        return new RestTemplate(); // Instancia um novo RestTemplate que pode ser injetado em outras partes da
                                   // aplicação onde é necessário fazer chamadas HTTP
    }
}
