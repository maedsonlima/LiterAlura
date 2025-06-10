package com.alura.client;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AuthorRepository authorRepository;

    public LivroService(LivroRepository livroRepository, AuthorRepository authorRepository) {
        this.livroRepository = livroRepository;
        this.authorRepository = authorRepository;
    }

    public long contarLivrosPorIdioma(String idioma) {
        return livroRepository.countByIdioma(idioma);
    }

    @Transactional
    public void salvarLivro(BookDTO dto) {
        // Busca autor pelo nome, ou cria um novo
        AuthorEntity autor = authorRepository.findByNome(dto.getAutor())
                .orElseGet(() -> new AuthorEntity(dto.getAutor(), null, null));

        // Cria o livro
        LivroEntity livro = new LivroEntity();
        livro.setTitulo(dto.getTitulo());
        livro.setIdioma(dto.getIdioma());
        livro.setNumeroDownloads(dto.getNumeroDownloads());
        livro.setAutor(autor);

        // Salva autor (para garantir que autor exista)
        authorRepository.save(autor);

        // Salva livro
        livroRepository.save(livro);
    }

    public List<LivroEntity> listarTodos() {
        return livroRepository.findAll();
    }

    public List<LivroEntity> buscarPorIdioma(String idioma) {
        return livroRepository.findByIdiomaIgnoreCase(idioma);
    }
}
