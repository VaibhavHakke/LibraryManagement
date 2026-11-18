package com.example.library;

import java.util.Objects;

public final class Book {
    private final int id;
    private final String title;
    private final String author;
    private boolean available = true;

    public Book(int id, String title, String author) {
        if (id <= 0) {
            throw new IllegalArgumentException("Book ID must be a positive number.");
        }
        this.id = id;
        this.title = requireText(title, "Title");
        this.author = requireText(author, "Author");
    }

    private static String requireText(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " cannot be null.");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank.");
        }
        return trimmed;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    void setAvailable(boolean available) {
        this.available = available;
    }
}
