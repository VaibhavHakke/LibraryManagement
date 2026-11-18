package com.example.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibraryCatalogTest {
    @Test
    void rejectsDuplicateBookIds() {
        LibraryCatalog catalog = new LibraryCatalog();
        catalog.addBook(new Book(1, "Dune", "Frank Herbert"));

        assertThrows(IllegalArgumentException.class,
                () -> catalog.addBook(new Book(1, "Another book", "Another author")));
    }

    @Test
    void searchesTitlesWithoutCaseSensitivity() {
        LibraryCatalog catalog = new LibraryCatalog();
        catalog.addBook(new Book(1, "The Hobbit", "J. R. R. Tolkien"));

        assertEquals(1, catalog.searchByTitle("hobbit").size());
        assertTrue(catalog.searchByTitle("missing").isEmpty());
    }

    @Test
    void checksOutAndReturnsBooks() {
        LibraryCatalog catalog = new LibraryCatalog();
        Book book = new Book(1, "Dune", "Frank Herbert");
        catalog.addBook(book);

        catalog.borrowBook(1);
        assertFalse(book.isAvailable());
        assertThrows(IllegalStateException.class, () -> catalog.borrowBook(1));

        catalog.returnBook(1);
        assertTrue(book.isAvailable());
    }

    @Test
    void rejectsLoansForUnknownBooks() {
        LibraryCatalog catalog = new LibraryCatalog();

        assertThrows(IllegalArgumentException.class, () -> catalog.borrowBook(99));
        assertThrows(IllegalArgumentException.class, () -> catalog.returnBook(99));
    }
}
