package ua.com.alevel.contoller;

import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.dao.impl.AuthorDaoImpl;
import ua.com.alevel.dao.impl.BookDaoImpl;
import ua.com.alevel.dto.AuthorDTO;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Controller {
    AuthorDao authorDao = new AuthorDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    public void start() throws RuntimeException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome");
        System.out.println("Select options:");
        String select;
        menu();
        while (true) {
            try {
                if ((select = bf.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                crud(bf, select);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void menu() {
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                        ");
        System.out.println("If you want create author, please enter 1                   If you want create book, please enter 11");
        System.out.println("                                                        ");
        System.out.println("If you want to update author, please enter 2                If you want to update book, please enter 22");
        System.out.println("                                                        ");
        System.out.println("If you want to delete author, please enter 3                If you want to delete book, please enter 33");
        System.out.println("                                                        ");
        System.out.println("If you want to find author, please enter 4                  If you want to find book, please enter 44");
        System.out.println("                                                        ");
        System.out.println("If you want to find all authors, please enter 5             If you want to find all books, please enter 55");
        System.out.println("                                                        ");
        System.out.println("If you want to see author statistics, please enter 6        If you want to know if book exist by name, please enter 66");
        System.out.println("                                                        ");
        System.out.println("If you want attach book to author, please enter 7           If you want detach book to author, please enter 77 ");
        System.out.println("                                                        ");
        System.out.println("If you want close, please enter 0                       ");
        System.out.println("                                                        ");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" -> createAuthor(reader);
            case "2" -> updateAuthor(reader);
            case "3" -> deleteAuthor(reader);
            case "4" -> findByIdAuthor(reader);
            case "5" -> findAllAuthors();
            case "6" -> findAuthorStatistics();
            case "7" -> attachBookToAuthor(reader);

            case "11" -> createBook(reader);
            case "22" -> updateBook(reader);
            case "33" -> deleteBook(reader);
            case "44" -> findByIdBook(reader);
            case "55" -> findAllBooks();
            case "66" -> existByName(reader);
            case "77" -> detachAuthorToBook(reader);
            case "0" -> System.exit(0);
        }
        menu();
    }

    private void createAuthor(BufferedReader reader) throws IOException {
        System.out.println("Please enter the first name:");
        String firstName = reader.readLine();
        System.out.println("Please enter the last name:");
        String lastName = reader.readLine();
        System.out.println("Please, enter age");
        int age = Integer.parseInt(reader.readLine());
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setAge(age);
        authorDao.create(author);
    }

    private void updateAuthor(BufferedReader reader) throws IOException {
        System.out.println("Please enter the first name:");
        String firstName = reader.readLine();
        System.out.println("Please enter the last name:");
        String lastName = reader.readLine();
        System.out.println("Please enter the age of author, you want to update");
        int age = Integer.parseInt(reader.readLine());
        System.out.println("Please enter the id of author, you want to update");
        Long id = Long.valueOf(reader.readLine());
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setAge(age);
        author.setId(id);
        authorDao.update(author);
    }

    private void deleteAuthor(BufferedReader reader) throws IOException {
        System.out.println("Please enter the id:");
        Long id = Long.valueOf(reader.readLine());
        authorDao.delete(id);
    }

    private void findByIdAuthor(BufferedReader reader) throws IOException {
        System.out.println("Please enter the id:");
        Long id = Long.valueOf(reader.readLine());
        Optional<Author> author = authorDao.findById(id);
        System.out.println("author = " + author);
    }

    private void findAllAuthors() {
        List<Author> authors = (List<Author>) authorDao.findAll();
        for (Author author : authors) {
            System.out.println("author = " + author);
        }
    }

    private void findAuthorStatistics() {
        Collection<AuthorDTO> authors = authorDao.findAuthorStatistics();
        authors.forEach(System.out::println);
    }

    private void createBook(BufferedReader reader) throws IOException {
        System.out.println("Please enter the name:");
        String name = reader.readLine();
        Book book = new Book();
        book.setName(name);
        bookDao.create(book);
    }

    private void updateBook(BufferedReader reader) throws IOException {
        System.out.println("Please enter the name:");
        String name = reader.readLine();
        System.out.println("Please enter the id of book, you want to update");
        Long id = Long.valueOf(reader.readLine());
        Book book = new Book();
        book.setName(name);
        book.setId(id);
        bookDao.update(book);
    }

    private void deleteBook(BufferedReader reader) throws IOException {
        System.out.println("Please enter the id of the book:");
        Long id = Long.valueOf(reader.readLine());
        bookDao.delete(id);
    }

    private void findByIdBook(BufferedReader reader) throws IOException {
        System.out.println("Please enter the id of the book:");
        Long id = Long.valueOf(reader.readLine());
        Optional<Book> book = bookDao.findById(id);
        System.out.println("book = " + book);
    }

    private void findAllBooks() {
        List<Book> books = (List<Book>) bookDao.findAll();
        for (Book book : books) {
            System.out.println("book = " + book);
        }
    }

    private void existByName(BufferedReader reader) throws IOException {
        System.out.println("Enter the name of the book");
        String name = reader.readLine();
        System.out.println(bookDao.existByName(name));
    }

    private void attachBookToAuthor(BufferedReader reader) throws IOException {
        System.out.println("Enter the book id");
        Long bookId = Long.valueOf(reader.readLine());
        System.out.println("Enter the author id");
        Long authorId = Long.valueOf(reader.readLine());
        authorDao.attachBookToAuthor(bookId, authorId);
    }

    private void detachAuthorToBook(BufferedReader reader) throws IOException {
        System.out.println("Enter the book id");
        Long bookId = Long.valueOf(reader.readLine());
        System.out.println("Enter the author id");
        Long authorId = Long.valueOf(reader.readLine());
        authorDao.detachAuthorToBook(bookId, authorId);
    }
}

