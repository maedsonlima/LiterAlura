package com.alura.client;

import org.springframework.stereotype.Component;

@Component
public class ConversorDeDados {

    public BookDTO converter(Book book) {
        var autor = book.getAuthors().isEmpty() ? "Autor desconhecido" : book.getAuthors().get(0).getName();

        return new BookDTO(
                book.getTitle(),
                autor,
                book.getLanguages().isEmpty() ? "Desconhecido" : book.getLanguages().get(0),
                book.getDownloadCount()
        );
    }
}
