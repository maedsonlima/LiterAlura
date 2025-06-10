package com.alura.client;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
    long countByIdioma(String idioma);

    List<LivroEntity> findByIdiomaIgnoreCase(String idioma); // Corrigido para ignorar maiúsculas/minúsculas
}
