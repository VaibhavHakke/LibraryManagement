package com.example.library;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class LibraryCatalog {
    private final Map<Integer, Book> books = new LinkedHashMap<>();

    public void addBook(Book book) {
        if (books.putIfAbsent(book.getId(), book) != null) {
            throw new IllegalArgumentException("A book with ID " + book.getId() + " already exists.");
        }
    }

    public List<Book> getBooks() {
        return List.copyOf(books.values());
    }

    public List<Book> searchByTitle(String query) {
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException("Search text cannot be blank.");
        }
        String normalizedQuery = query.trim().toLowerCase(Locale.ROOT);
        List<Book> matches = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase(Locale.ROOT).contains(normalizedQuery)) {
                matches.add(book);
            }
        }
        return List.copyOf(matches);
    }

    public void borrowBook(int id) {
        Book book = getBook(id);
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book " + id + " is already checked out.");
        }
        book.setAvailable(false);
    }

    public void returnBook(int id) {
        Book book = getBook(id);
        if (book.isAvailable()) {
            throw new IllegalStateException("Book " + id + " is not checked out.");
        }
        book.setAvailable(true);
    }

    private Book getBook(int id) {
        Book book = books.get(id);
        if (book == null) {
            throw new IllegalArgumentException("No book found with ID " + id + ".");
        }
        return book;
    }
}
