package com.example.library;

import java.util.List;
import java.util.Scanner;

public final class Main {
    private final LibraryCatalog catalog = new LibraryCatalog();
    private final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            printMenu();
            switch (input.nextLine().trim()) {
                case "1" -> addBook();
                case "2" -> showBooks(catalog.getBooks());
                case "3" -> searchBooks();
                case "4" -> updateLoan(true);
                case "5" -> updateLoan(false);
                case "0" -> running = false;
                default -> System.out.println("Choose one of the listed options.");
            }
        }
        System.out.println("Goodbye.");
    }

    private void printMenu() {
        System.out.println("""

                === Library Management ===
                1. Add a book
                2. List all books
                3. Search books by title
                4. Check out a book
                5. Return a book
                0. Exit
                """);
        System.out.print("Select an option: ");
    }

    private void addBook() {
        try {
            int id = readId("Book ID: ");
            System.out.print("Title: ");
            String title = input.nextLine();
            System.out.print("Author: ");
            String author = input.nextLine();
            catalog.addBook(new Book(id, title, author));
            System.out.println("Book added.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Unable to add book: " + exception.getMessage());
        }
    }

    private void searchBooks() {
        System.out.print("Title to search: ");
        try {
            showBooks(catalog.searchByTitle(input.nextLine()));
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void updateLoan(boolean checkout) {
        try {
            int id = readId("Book ID: ");
            if (checkout) {
                catalog.borrowBook(id);
                System.out.println("Book checked out.");
            } else {
                catalog.returnBook(id);
                System.out.println("Book returned.");
            }
        } catch (IllegalArgumentException | IllegalStateException exception) {
            System.out.println("Unable to update loan: " + exception.getMessage());
        }
    }

    private int readId(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Book ID must be a whole number.", exception);
        }
    }

    private void showBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book book : books) {
            String status = book.isAvailable() ? "Available" : "Checked out";
            System.out.printf("%d | %s | %s | %s%n",
                    book.getId(), book.getTitle(), book.getAuthor(), status);
        }
    }
}
