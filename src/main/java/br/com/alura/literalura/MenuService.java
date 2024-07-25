package br.com.alura.literalura;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.GutendexResponse;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.GutendexService;
import org.springframework.transaction.annotation.Transactional;
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
        Scanner teclado = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===================== LITERALURA =====================\n");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Inserir novo livro");
            System.out.println("2. Consultar livros");
            System.out.println("3. Listar todos os livros");
            System.out.println("4. Listar livros por idioma");
            System.out.println("5. Sair");

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
                    listarTodosLivros();
                    break;
                case 4:
                    listarLivrosPorIdioma(teclado);
                    break;
                case 5:
                    running = false;
//                    System.out.println("Saindo...");
                    teclado.close();
//                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void inserirNovoLivro(Scanner teclado) {
        System.out.println("Digite o título do livro: ");
        String titulo = teclado.nextLine();

        List<Autor> autores = new ArrayList<>();
        while (true) {
            System.out.println("Digite o nome do(a) autor(a) (ou 'sair' para finalizar): ");
            String nome = teclado.nextLine();
            if (nome.equalsIgnoreCase("sair")) {
                break;
            }

//            Livro.Autor autor = new Livro.Autor();
//            autor.setNome(nome);

            System.out.println("Digite o ano de nascimento do(a) autor(a): ");
            while (!teclado.hasNextInt()) {
                System.out.println("Ano de nascimento inválido! Por favor, tente novamente!");
                teclado.next();
            }
            int anoNascimento = teclado.nextInt();
            teclado.nextLine();
//            autor.setAnoDeNascimento(anoNascimento);

            System.out.println("Digite o ano de óbito do(a) autor(a) (ou 0 se ainda vivo(a)): ");
            while (!teclado.hasNextInt()) {
                System.out.println("Ano de óbito inválido! Por favor, tente novamente!");
                teclado.next();
            }
            Integer anoObito = teclado.nextInt();
            teclado.nextLine();

            Autor autor = new Autor();
            autor.setNome(nome);
            autor.setAnoDeNascimento(anoNascimento);
            if (anoObito == 0) {
                autor.setAnoDeObito(null);
            } else {
                autor.setAnoDeObito(anoObito);
            }
            autores.add(autor);
        }

            //           gutendexService.inserirNovoLivro(titulo, autores);

            boolean sucesso = gutendexService.inserirNovoLivro(titulo, autores);
            if (sucesso) {
                System.out.println("Livro '" + titulo + "' inserido com sucesso!");
            } else {
                System.out.println("Erro ao inserir o livro '" + titulo + "'.");
            }
        }

    private void consultarLivro (Scanner teclado){
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

    @Transactional(readOnly = true)
    public void listarTodosLivros () {
        List<Livro> livros = gutendexService.listarTodosLivrosComAutores();

        if (!livros.isEmpty()) {
            livros.forEach(livro -> {
                System.out.println("Título: '" + livro.getTitulo() + "'");
                livro.getAutores().forEach(autor -> {
                    System.out.println("Autor(a): " + autor.getNome());
                    System.out.println("Ano de nascimento: " + autor.getAnoDeNascimento());
                    if (autor.getAnoDeObito() != null) {
                        System.out.println("Ano de obito: " + autor.getAnoDeObito());
                    } else {
                        System.out.println("Ano de obito: ainda vivo(a)");
                    }
                });
                System.out.println("Idiomas: " + String.join(", ", livro.getIdiomas()));
                System.out.println("Número de downloads: " + livro.getNumeroDeDownloads() + "\n");
            });
        } else {
            System.out.println("Nenhum livro encontrado para esta consulta");
        }
    }

    private void listarLivrosPorIdioma (Scanner teclado){
        System.out.println("Digite o idioma dos livros que deseja listar: ");
        String idioma = teclado.nextLine();
        List<Livro> livros = gutendexService.listarLivrosPorIdioma(idioma);

        if (!livros.isEmpty()) {
            livros.forEach(livro -> {
                System.out.println("Título: '" + livro.getTitulo() + "'");
                livro.getAutores().forEach(autor -> {
                    System.out.println("Autor(a): " + autor.getNome());
                    System.out.println("Ano de nascimento: " + autor.getAnoDeNascimento());
                    if (autor.getAnoDeObito() != null) {
                        System.out.println("Ano de obito: " + autor.getAnoDeObito());
                    } else {
                        System.out.println("Ano de obito: ainda vivo(a)");
                    }
                });
                System.out.println("Idiomas: " + String.join(", ", livro.getIdiomas()));
                System.out.println("Número de downloads: " + livro.getNumeroDeDownloads() + "\n");
            });
        } else {
            System.out.println("Nenhum livro encontrado em " + idioma);
        }
    }
}
