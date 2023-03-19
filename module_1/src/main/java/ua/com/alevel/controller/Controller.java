package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.entity.BookAuthor;
import ua.com.alevel.strorage.EntityStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Controller {
    EntityStorage entityStorage = new EntityStorage();

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
        System.out.println();
        System.out.println("If you want create author, please enter 1");
        System.out.println("If you want create book, please enter 2");
        System.out.println("If you want update author, please enter 3");
        System.out.println("If you want update book, please enter 4");
        System.out.println("If you want delete author, please enter 5");
        System.out.println("If you want delete book, please enter 6");
        System.out.println("If you want attach book to author enter 7");
        System.out.println("If you want find all authors 8");
        System.out.println("If you want find all books, please enter 9");
        System.out.println("If you want find all authors and theirs books, enter 10");
        System.out.println("If you want close, please enter 0");
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
        entityStorage.addAuthor(author);
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
        entityStorage.addBook(book);
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
        entityStorage.updateAuthor(author);
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
        entityStorage.updateBook(book);
    }

    private void attachBookToAuthor(BufferedReader reader) throws IOException {
        System.out.println("Please enter the book id");
        String stringBookId = reader.readLine();
        int bookId = Integer.parseInt(String.valueOf(stringBookId));
        System.out.println("Please enter the author id");
        String stringAuthorId = reader.readLine();
        int authorId = Integer.parseInt(String.valueOf(stringAuthorId));
        entityStorage.attachBookToAuthor(bookId, authorId);
        System.out.println("Successful");
    }

    private void findAllAuthors() {
        ArrayList<Author> authors = entityStorage.findAllAuthors();
        for (Author author : authors) {
            if (author != null){
                System.out.println("Author = " + author);
            }
        }
    }

    private void findAllBooks() {
        ArrayList<Book> books = entityStorage.findAllBooks();
        for (Book book : books) {
            if (book != null) {
                System.out.println("Book = " + book);
            }
        }
    }

    private void findAllBookAuthors() {
        ArrayList<BookAuthor> bookAuthors = entityStorage.findAllBookAuthors();
        for (BookAuthor bookAuthor: bookAuthors) {
            if (bookAuthor != null) {
                System.out.println("Book = " + bookAuthor);
            }
        }
    }

  private void deleteAuthor(BufferedReader reader) throws IOException {
        System.out.println("Please enter id of the author, you want to delete");
        String stringId = reader.readLine();
        int id = Integer.parseInt(String.valueOf(stringId));
        entityStorage.deleteAuthor(id);
    }

    private void deleteBook(BufferedReader reader) throws IOException {
        System.out.println("Please enter id of the book, you want to delete");
        String stringId = reader.readLine();
        int id = Integer.parseInt(String.valueOf(stringId));
        entityStorage.deleteBook(id);

    }


}