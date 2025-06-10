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

    public Application(GutendexClient client, ConversorDeDados conversor, LivroService livroService) {
        this.client = client;
        this.conversor = conversor;
        this.livroService = livroService;
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
            Book primeiroLivro = livros.get(0); // pega o primeiro da lista
            BookDTO dto = conversor.converter(primeiroLivro); // converte o primeiro
            livroService.salvarLivro(dto); // salva no banco
            System.out.println("Livro salvo com sucesso: " + dto);
        }
    }

    private void listarLivrosRegistrados() {
        System.out.println("[2] Listar livros registrados - em breve");
    }

    private void listarAutoresRegistrados() {
        System.out.println("[3] Listar autores registrados - em breve");
    }

    private void listarAutoresVivosNoAno() {
        System.out.println("[4] Listar autores vivos em um determinado ano - em breve");
    }

    private void listarLivrosPorIdioma() {
        System.out.println("[5] Listar livros por idioma - em breve");
    }
}