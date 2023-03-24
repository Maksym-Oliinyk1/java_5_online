package ua.com.alevel.service;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.entity.BookAuthor;

import java.util.UUID;

public class Service {

    private final Author[] authors = new Author[10];
    private final Book[] books = new Book[10];
    private final BookAuthor[] bookAuthors = new BookAuthor[10];

   public void addAuthor(Author author) {
       author.setId(generateIdForAuthor());
       for (int i = 0; i < authors.length; i++) {
           if (authors[i] == null) {
               authors[i] = author;
               break;
           }
       }
   }

    public void addBook(Book book) {
        book.setId(generateIdForBook());
        double rate = book.getRate();
        for (int i = 0; i < books.length; i++) {
            if (rate > 5 || rate < 0) {
                books[i] = null;
                System.out.println("The book was not created. Wrong rate!");
                break;
            }
            else if (books[i] == null) {
                books[i] = book;
                System.out.println("The book " + book.getBookName() + " created!");
                break;
            }
        }
    }

    public void attachBookToAuthor(String bookId, String authorId) {
        for (int i = 0; i < bookAuthors.length; i++) {
            if (bookAuthors[i] == null) {
                BookAuthor bookAuthor = new BookAuthor();
                bookAuthor.setAuthorId(authorId);
                bookAuthor.setBookId(bookId);
                bookAuthors[i] = bookAuthor;
                break;
            }
        }
    }
    public Book[] findBookByAuthor(String authorId) {
        String[] bookIds = new String[10];
        for (int i = 0; i < bookAuthors.length; i++) {
            BookAuthor bookAuthor = bookAuthors[i];
            if (bookAuthor != null && bookAuthor.getAuthorId().equals(authorId)) {
                for (int i1 = 0; i1 < bookIds.length; i1++) {
                    if (bookIds[i1] == null) {
                        bookIds[i1] = BookAuthor.getBookId();
                        break;
                    }
                }
            }
        }
        Book[] books = new Book[10];
        for (int i = 0; i < this.books.length; i++) {
            for (int i1 = 0; i1 < bookIds.length; i1++) {
                if (this.books[i] != null && this.books[i].getId().equals(bookIds[i1])) {
                    for (int i2 = 0; i2 < books.length; i2++) {
                        if (books[i2] == null) {
                            books[i2] = this.books[i];
                            break;
                        }
                    }
                }
            }
        }
        return books;
    }

    public void updateAuthor(Author author) {
        for (int i = 0; i < authors.length; i++) {
            try {
                if (authors[i].getId().equals(author.getId())){
                    authors[i] = author;
                }
            } catch (Exception e) {
                i++;
            }
        }
    }

    public void updateBook(Book book) {
        for (int i = 0; i < books.length; i++) {
            try {
                if (books[i].getId().equals(book.getId())){
                    books[i] = book;
                }
            } catch (Exception e) {
                i++;
            }
        }
    }

    public void deleteAuthor(String authorId) {
        for (int i = 0; i < authors.length; i++) {
            try {
                if(authors[i].getId().equals(authorId)){
                    authors[i] = null;
                    break;
                }
            }
            catch (Exception exception){
                i++;
            }
        }
        for (int i = 0; i < bookAuthors.length; i++) {
            try {
                if (bookAuthors[i].getAuthorId().equals(authorId)) {
                    bookAuthors[i] = null;
                    break;
                }
            }
            catch (Exception exception){
                i++;
            }
        }
    }


    public void deleteBook(String bookId) {
        for (int i = 0; i < books.length; i++) {
            try {
                if(books[i].getId().equals(bookId)){
                    books[i] = null;
                }
            }
            catch (Exception exception){
                i++;
            }
        }
    }

    public Book[] findAllBooks() {
        return this.books;
    }

    public Author[] findAllAuthors() {
        return this.authors;
    }

    public BookAuthor[] findAllBookAuthors() {
        return this.bookAuthors;
    }

    private String generateIdForAuthor() {
        String id = UUID.randomUUID().toString();
        for (Author author : authors) {
                if (author != null && author.getId().equals(id)) {
                return generateIdForAuthor();
            }
        }
        return id;
    }

    private String generateIdForBook() {
        String id = UUID.randomUUID().toString();
        for (Book book : books) {
            if (book != null && book.getId().equals(id)) {
                return generateIdForBook();
            }
        }
        return id;
    }
}