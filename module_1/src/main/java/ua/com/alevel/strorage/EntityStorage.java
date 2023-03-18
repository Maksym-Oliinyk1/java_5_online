package ua.com.alevel.strorage;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.entity.BookAuthor;

import java.util.ArrayList;

public class EntityStorage {
    private ArrayList<Author> authors = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<BookAuthor> bookAuthors = new ArrayList<>();

    public void addAuthor(Author author) {
        author.setId(generateIdForAuthor());
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i) == null) {
                authors.add(author);
                break;
            }
        }
    }

    public void addBook(Book book) {
        book.setId(generateIdForAuthor());
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i) == null) {
                books.add(book);
            }
        }
    }

    public void attachBookToAuthor(int bookId, int authorId) {
        for (int i = 0; i < bookAuthors.size(); i++) {
            if (bookAuthors.get(i) == null) {
                BookAuthor bookAuthor = new BookAuthor();
                bookAuthor.setAuthorId(authorId);
                bookAuthor.setBookId(bookId);
                bookAuthors.add(i, bookAuthor);
                break;
            }
        }
    }

    public ArrayList<Book> findBookByAuthor(int authorId) {
        ArrayList<Integer> bookIds = new ArrayList<>();
        for (int i = 0; i < bookAuthors.size(); i++) {
            BookAuthor bookAuthor = new BookAuthor();
            bookAuthors.add(i, bookAuthor);
            if (bookAuthor != null && bookAuthor.getAuthorId() == authorId) {
                for (int i1 = 0; i1 < bookIds.size(); i++) {
                    if (bookIds.get(i) == 0) {
                        bookIds.add(i1, bookAuthor.getBookId());
                        break;
                    }
                }
            }
        }
        ArrayList<Book> books = new ArrayList<>();
        for (int i = 0; i < this.books.size(); i++) {
            for (int i1 = 0; i1 < bookIds.size(); i++) {
                if (this.books.get(i1) != null && this.books.get(i1).getId() == bookIds.get(i1)) {
                    for (int i2 = 0; i2 < books.size(); i2++) {
                        if (books.get(i2) == null) {
                            books.add(i2, this.books.get(i2));
                            break;
                        }
                    }
                }
            }
        }
        return books;
    }

    public ArrayList<Book> findAllBooks() {
        return this.books;
    }

    public ArrayList<Author> findAllAuthors() {
        return this.authors;
    }

    private int generateIdForAuthor() {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i) == null) {
                return i + 1;
            }
        }
        return 0;
    }

    private int generateIdForBook() {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i) == null) {
                return i + 1;
            }
        }
        return 0;
    }
}