package com.alura.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public void salvarLivro(BookDTO dto) {
        Autor autor = autorRepository.findByNome(dto.getAutor())
                .orElseGet(() -> {
                    Autor novoAutor = new Autor();
                    novoAutor.setNome(dto.getAutor());
                    return autorRepository.save(novoAutor);
                });

        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setIdioma(dto.getIdioma());
        livro.setNumeroDeDownloads(dto.getNumeroDeDownloads());
        livro.setAutor(autor);

        livroRepository.save(livro);
    }
}