package com.alura.client;
import java.util.List;

public class BookResponse {
    private int count;
    private List<Book> results;

    // Getters e Setters

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Book> getResults() {
        return results;
    }

    public void setResults(List<Book> results) {
        this.results = results;
    }
}