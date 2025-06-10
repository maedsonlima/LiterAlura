package com.alura.client;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<AuthorEntity> listarTodos() {
        return repository.findAll();
    }

    public List<AuthorEntity> listarVivosEm(int ano) {
        return repository.findByAnoFalecimentoIsNullOrAnoFalecimentoGreaterThan(ano);
    }

    public void salvar(AuthorEntity autor) {
        repository.save(autor);
    }

    public Optional<AuthorEntity> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }
}
