package com.alura.client;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);
}