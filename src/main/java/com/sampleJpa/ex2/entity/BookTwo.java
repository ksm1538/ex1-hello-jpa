package com.sampleJpa.ex2.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="BOOK_TWO")
public class BookTwo extends Items{
    private String author;
    private String isbn;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
