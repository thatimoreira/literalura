package br.com.alura.literalura;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.GutendexResponse;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private GutendexService gutendexService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner teclado = new Scanner(System.in);
		boolean running = true;
		
		while (running) {
			System.out.println("\n=========================================================================================================================================================");
			System.out.println("                             __       _________________________________     ___     __      __     ___________      ___");
			System.out.println("                            |  |     |  |___    ___|  _______|  ____   |   / _ \\   |  |    |  |   |  |  ____   |   / _ \\");
			System.out.println("                            |  |     |  |   |  |   |  |______  |___/  /   / / \\ \\  |  |    |  |   |  | |___/  /   / / \\ \\");
			System.out.println("                            |  |     |  |   |  |   |   ______|  ___  /   / /___\\ \\ |  |    |  |   |  |  ___  /   / /___\\ \\");
			System.out.println("                            |  |_____|  |   |  |   |  |______   |  \\  \\ /  _____  \\|  |____|  |___|  |  |  \\  \\ /  _____  \\");
			System.out.println("                            |________|__|   |__|   |_________|__|   \\  \\_/       \\_\\_______|_________|__|   \\  \\_/       \\_\\");
			System.out.println("\n=========================================================================================================================================================\n");
			System.out.println("Menu:");
			System.out.println("1. Buscar livros por título");
			System.out.println("2. Buscar livros por autor(a)");
			System.out.println("3. Buscar livros por idioma");
			System.out.println("4. Listar todos os livros pesquisados");
			System.out.println("5. Listar todos os autores pesquisados");
			System.out.println("6. Listar autores vivos em um determinado ano");
			System.out.println("7. Sair");
			System.out.print(">> ");                                            	

			int opcao = teclado.nextInt();
			teclado.nextLine();

			switch (opcao) {
				case 1:
					System.out.print("Digite o titulo do livro: ");
					String titulo = teclado.nextLine();
					GutendexResponse responseByTitulo = gutendexService.buscarLivros(titulo, null);
					System.out.println(responseByTitulo);
					break;
				case 2:
					System.out.print("Digite o nome do(a) autor(a): ");
					String autor = teclado.nextLine();
					GutendexResponse responseByAutor = gutendexService.buscarLivros(autor, null);
					System.out.println(responseByAutor);
					break;
				case 3:
					System.out.print("Digite o idioma (ex: en, fr): ");
					String idioma = teclado.nextLine();
					GutendexResponse responseByIdioma = gutendexService.buscarLivros(null, idioma);
					System.out.println(responseByIdioma);
					break;
				case 4:
					List<Livro> livrosPesquisados = gutendexService.listarLivrosPesquisados();
					System.out.println("Livros pesquisados:");
					livrosPesquisados.forEach(System.out::println);
					break;
				case 5:
					List<Autor> todosAutores = gutendexService.listarTodosAutores();
					System.out.println("Todos os autores:");
					todosAutores.forEach(System.out::println);
					break;
				case 6:
					System.out.println("Digite o ano para listar os autores vivos:");
					int ano = teclado.nextInt();
					List<Autor> autoresVivos = gutendexService.listarAutoresVivosNoAno(ano);
					System.out.println("Autores vivos no ano " + ano + ": ");
					autoresVivos.forEach(System.out::println);
					break;
				case 7:
					running = false;
					System.out.println("Saindo...\n");
					teclado.close();
					exit(0);                   	
				default:
					System.out.println("Opção inválida! Tente novamente.");
			}
		}
	}

}