package com.alura.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    @JsonAlias("title")
    private String title;

    @JsonAlias("authors")
    private List<Author> authors;

    @JsonAlias("languages")
    private List<String> languages;

    @JsonAlias("download_count")
    private int downloadCount;

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    // Setters (necessários para deserialização)
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return """
                ------LIVRO---------
                Título: %s
                Autor(es): %s
                Idioma(s): %s
                Downloads: %d
                --------------------
                """.formatted(title, authors, languages, downloadCount);
    }
}
