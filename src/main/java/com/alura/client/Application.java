package com.alura.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final Scanner scanner = new Scanner(System.in);
    private final GutendexClient client;
    private final ConversorDeDados conversor;
    private final LivroService livroService;
    private final AuthorService authorService;

    public Application(GutendexClient client, ConversorDeDados conversor, LivroService livroService, AuthorService authorService) {
        this.client = client;
        this.conversor = conversor;
        this.livroService = livroService;
        this.authorService = authorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> buscarLivroPorTitulo();
                    case 2 -> listarLivrosRegistrados();
                    case 3 -> listarAutoresRegistrados();
                    case 4 -> listarAutoresVivosNoAno();
                    case 5 -> listarLivrosPorIdioma();
                    case 0 -> System.out.println("Encerrando...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\nEscolha o número da sua opção:");
        System.out.println("1 - Buscar livro pelo título");
        System.out.println("2 - Listar livros registrados");
        System.out.println("3 - Listar autores registrados");
        System.out.println("4 - Listar autores vivos em um determinado ano");
        System.out.println("5 - Listar livros por idioma");
        System.out.println("0 - Sair");
        System.out.print("Sua escolha: ");
    }

    private void buscarLivroPorTitulo() {
        System.out.print("Insira o nome do livro que você deseja procurar: ");
        String titulo = scanner.nextLine();

        List<Book> livros = client.buscarLivrosPorTitulo(titulo);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse título.");
        } else {
            Book primeiroLivro = livros.get(0);
            BookDTO dto = conversor.converter(primeiroLivro);
            livroService.salvarLivro(dto);
            System.out.println("Livro salvo com sucesso: " + dto);
        }
    }

    private void listarLivrosRegistrados() {
        List<LivroEntity> livros = livroService.listarTodos();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no banco.");
        } else {
            livros.forEach(l -> System.out.printf("Título: %s | Idioma: %s | Downloads: %d | Autor: %s%n",
                    l.getTitulo(), l.getIdioma(), l.getNumeroDownloads(), l.getAutor().getNome()));
        }
    }

    private void listarAutoresRegistrados() {
        List<AuthorEntity> autores = authorService.listarTodos();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado no banco.");
        } else {
            autores.forEach(a -> System.out.printf("Nome: %s | Nascimento: %d | Falecimento: %s%n",
                    a.getNome(), a.getAnoNascimento(),
                    a.getAnoFalecimento() != null ? a.getAnoFalecimento() : "Ainda vivo"));
        }
    }

    private void listarAutoresVivosNoAno() {
        System.out.print("Digite o ano: ");
        int ano = Integer.parseInt(scanner.nextLine());

        List<AuthorEntity> autores = authorService.listarVivosEm(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado para esse ano.");
        } else {
            autores.forEach(a -> System.out.printf("Nome: %s | Nascimento: %d%n",
                    a.getNome(), a.getAnoNascimento()));
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.print("Digite o idioma (ex: 'pt' ou 'en'): ");
        String idioma = scanner.nextLine();

        List<LivroEntity> livros = livroService.buscarPorIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado nesse idioma.");
        } else {
            livros.forEach(l -> System.out.printf("Título: %s | Downloads: %d | Autor: %s%n",
                    l.getTitulo(), l.getNumeroDownloads(), l.getAutor().getNome()));
        }
    }
}
