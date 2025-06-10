package com.alura.client;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    List<AuthorEntity> findByAnoFalecimentoIsNullOrAnoFalecimentoGreaterThan(Integer ano);

    Optional<AuthorEntity> findByNome(String nome);
}