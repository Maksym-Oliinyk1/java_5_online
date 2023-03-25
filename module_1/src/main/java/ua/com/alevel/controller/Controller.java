package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.entity.BookAuthor;
import ua.com.alevel.service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    Service service = new Service();

    public void start() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome");
        System.out.println("Select options:");
        String select;
        menu();
        while ((select = bf.readLine()) != null) {
            crud(bf, select);
        }
    }

    private static void menu() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("If you want create author, please enter 1");
        System.out.println();
        System.out.println("If you want create book, please enter 2");
        System.out.println();
        System.out.println("If you want update author, please enter 3");
        System.out.println();
        System.out.println("If you want update book, please enter 4");
        System.out.println();
        System.out.println("If you want delete author, please enter 5");
        System.out.println();
        System.out.println("If you want delete book, please enter 6");
        System.out.println();
        System.out.println("If you want attach book to author enter 7");
        System.out.println();
        System.out.println("If you want find all authors 8");
        System.out.println();
        System.out.println("If you want find all books, please enter 9");
        System.out.println();
        System.out.println("If you want find all authors and theirs books, enter 10");
        System.out.println();
        System.out.println("If you want find all books by the author, please enter 11");
        System.out.println();
        System.out.println("If you want close, please enter 0");
        System.out.println("---------------------------------------------------------------");
    }

   private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" -> createAuthor(reader);
            case "2" -> createBook(reader);
            case "3" -> updateAuthor(reader);
            case "4" -> updateBook(reader);
            case "5" -> deleteAuthor(reader);
            case "6" -> deleteBook(reader);
            case "7" -> attachBookToAuthor(reader);
            case "8" -> findAllAuthors();
            case "9" -> findAllBooks();
            case "10" -> findAllBookAuthors();
            case "11" -> findBookByAuthor(reader);
            case "0" -> System.exit(0);
        }
        menu();
    }

    private void createAuthor(BufferedReader reader) throws IOException {
        System.out.println("Enter first name of author");
        String firstName = reader.readLine();
        System.out.println("Enter last name of author");
        String lastName = reader.readLine();
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        service.addAuthor(author);
        System.out.println("The author " + firstName + " " + lastName + " added!");
    }

    private void createBook(BufferedReader reader) throws IOException {
        System.out.println("Enter name of a book");
        String bookName = reader.readLine();
        System.out.println("Enter genre of a book");
        String genre = reader.readLine();
        System.out.println("Enter rate of book (1 to 5, double)");
        String stringRate = reader.readLine();
        double rate = Double.parseDouble(String.valueOf(stringRate));
        Book book = new Book();
        book.setBookName(bookName);
        book.setGenre(genre);
        book.setRate(rate);
        service.addBook(book);
    }

    private void updateAuthor(BufferedReader reader) throws IOException {
        System.out.println("Enter id");
        String id = reader.readLine();
        System.out.println("Enter the new first name");
        String firstName = reader.readLine();
        System.out.println("Enter the new last name");
        String lastName = reader.readLine();
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setId(id);
        service.updateAuthor(author);
        System.out.println("The author was successfully updated!");
    }

    private void updateBook(BufferedReader reader) throws IOException {
        System.out.println("Enter id");
        String id = reader.readLine();
        System.out.println("Enter the new name");
        String name = reader.readLine();
        System.out.println("Enter the new genre");
        String genre = reader.readLine();
        System.out.println("Enter the new rate");
        String stringRate = reader.readLine();
        double rate = Double.parseDouble(String.valueOf(stringRate));
        Book book = new Book();
        book.setBookName(name);
        book.setGenre(genre);
        book.setRate(rate);
        book.setId(id);
        service.updateBook(book);
        System.out.println("The book was successfully updated!");
    }

    private void attachBookToAuthor(BufferedReader reader) throws IOException {
        System.out.println("Please enter the book id");
        String bookId = reader.readLine();
        System.out.println("Please enter the author id");
        String authorId = reader.readLine();
        service.attachBookToAuthor(bookId, authorId);
        System.out.println("Successful");
    }

    private void findAllAuthors() {
        Author[] authors = service.findAllAuthors();
        for (Author author : authors) {
            if (author != null) {
                System.out.println("Author = " + author);
            }
        }
    }

    private void findAllBooks() {
        Book[] books = service.findAllBooks();
        for (Book book : books) {
            if (book != null) {
                System.out.println("Book = " + book);
            }
        }
    }

    private void findAllBookAuthors() {
        BookAuthor[] booksAuthors = service.findAllBookAuthors();
        for (BookAuthor bookAuthor : booksAuthors) {
            if (bookAuthor != null) {
                System.out.println("Author and book = " + bookAuthor);
            }
        }
    }

    private void findBookByAuthor(BufferedReader reader) throws IOException {
        System.out.println("Please, enter the id of the author, which books you want to find");
        String id = reader.readLine();
        Book[] books = service.findBookByAuthor(id);
        for (Book book : books) {
            if (book != null) {
                System.out.println("Book = " + book);
            }
        }
    }

  private void deleteAuthor(BufferedReader reader) throws IOException {
        System.out.println("Please enter id of the author, you want to delete");
        String Id = reader.readLine();
        service.deleteAuthor(Id);
        System.out.println("The author was successfully deleted!");
    }

    private void deleteBook(BufferedReader reader) throws IOException {
        System.out.println("Please enter id of the book, you want to delete");
        String id = reader.readLine();
        service.deleteBook(id);
        System.out.println("The book was successfully deleted!");

    }


}