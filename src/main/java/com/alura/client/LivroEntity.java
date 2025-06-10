package com.alura.client;

import jakarta.persistence.*;

@Entity
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String idioma;

    private Integer numeroDownloads;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AuthorEntity autor;

    public LivroEntity() {}

    public LivroEntity(String titulo, String idioma, Integer numeroDownloads, AuthorEntity autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.numeroDownloads = numeroDownloads;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public AuthorEntity getAutor() {
        return autor;
    }

    public void setAutor(AuthorEntity autor) {
        this.autor = autor;
    }
}