package br.com.alura.literalura;

import br.com.alura.literalura.model.GutendexResponse;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class MenuService {

    @Autowired
    private GutendexService gutendexService;

    public void exibirMenu() {
        System.out.println("\n===================== LITERALURA =====================\n");
        Scanner teclado = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Inserir novo livro");
            System.out.println("2. Consultar livros");
            System.out.println("3. Sair");

            int opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    inserirNovoLivro(teclado);
                    break;
                case 2:
                    consultarLivro(teclado);
                    break;
                case 3:
                    running = false;
                    System.out.println("Saindo...");
                    teclado.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void inserirNovoLivro(Scanner teclado) {
        System.out.println("Digite o título do livro: ");
        String titulo = teclado.nextLine();
        List<Livro.Autor> autores = new ArrayList<>();

        while (true) {
            System.out.println("Digite o nome do(a) autor(a) (ou 'sair' para finalizar): ");
            String nome = teclado.nextLine();
            if ("sair".equalsIgnoreCase(nome)) {
                break;
            }
            System.out.println("Digite o ano de nascimento do(a) autor(a): ");
            int anoNascimento = teclado.nextInt();
            teclado.nextLine();

            System.out.println("Digite o ano de óbito do(a) autor(a) (ou 0 se ainda vivo(a)): ");
            Integer anoObito = teclado.nextInt();
            teclado.nextLine();

            Livro.Autor autor = new Livro.Autor();
            autor.setNome(nome);
            autor.setAnoDeNascimento(anoNascimento);
            if (anoObito == 0) {
                autor.setAnoDeObito(null);
            } else {
                autor.setAnoDeObito(anoObito);
            }
            autores.add(autor);
        }

        boolean sucesso = gutendexService.inserirNovoLivro(titulo, autores);
        if (sucesso) {
            System.out.println("Livro '" + titulo + "' inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir o livro '" + titulo + "'.");
        }
    }

    private void consultarLivro(Scanner teclado) {
        System.out.println("Digite o título do livro que deseja consultar: ");
        String titulo = teclado.nextLine();

        GutendexResponse response = gutendexService.buscarLivros(titulo);
        if ((response != null) && !response.getResults().isEmpty()) {
            response.getResults().forEach(livro -> {
                System.out.println("Título:'" + livro.getTitulo() + "'");
                livro.getAutores().forEach(autor -> {
                    System.out.println("Autor:'" + autor.getNome() + "'");
                    System.out.println("Ano de nascimento: " + autor.getAnoDeNascimento());
                    if (autor.getAnoDeObito() != null) {
                        System.out.println("Ano de obito: " + autor.getAnoDeObito());
                    } else {
                        System.out.println("Ano de óbtido: ainda vivo(a)");
                    }
                });
                System.out.println();
            });
        } else {
            System.out.println("Nenhum livro encontrado para a consulta: '" + titulo + "'");
        }
    }
}
