package com.example.livrocadastro.model;

public class Book {
    private long id;
    private String title;
    private String author;
    private int pages;
    private String publisher;
    private String releaseDate;

    // Construtor completo
    public Book(long id, String title, String author, int pages, String publisher, String releaseDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
    }

    // Construtor sem id (para inserção, pois o id é auto-gerado)
    public Book(String title, String author, int pages, String publisher, String releaseDate) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
