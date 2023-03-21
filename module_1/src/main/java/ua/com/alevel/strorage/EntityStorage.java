package ua.com.alevel.strorage;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.entity.BookAuthor;

import java.util.ArrayList;
import java.util.UUID;

public class EntityStorage {

    private ArrayList<Author> authors = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<BookAuthor> bookAuthors = new ArrayList<>();

   public void addAuthor(Author author) {
        author.setId(generateIdForAuthor());
        authors.add(author);
        }

    public void addBook(Book book) {
        book.setId(generateIdForBook());
        books.add(book);
    }

    public void attachBookToAuthor(String bookId, String authorId) {
                BookAuthor bookAuthor = new BookAuthor();
                bookAuthor.setAuthorId(authorId);
                bookAuthor.setBookId(bookId);
                bookAuthors.add(bookAuthor);
            }

    public ArrayList<Book> findBookByAuthor(String authorId) {
        ArrayList<String> bookIds = new ArrayList<>();
        for (int i = 0; i < bookAuthors.size(); i++) {
            BookAuthor bookAuthor = new BookAuthor();
            bookAuthors.add(i, bookAuthor);
            if (bookAuthor.getAuthorId() != null && bookAuthor.getAuthorId().equals(authorId)) {
                for (int i1 = 0; i1 < bookIds.size(); i++) {
                    if (bookIds.get(i1) == null) {
                        bookIds.add(i1, BookAuthor.getBookId());
                        break;
                    }
                }
            }
        }
        ArrayList<Book> books = new ArrayList<>();
        for (int i = 0; i < this.books.size(); i++) {
            for (int i1 = 0; i1 < bookIds.size(); i++) {
                if (this.books.get(i1) != null && this.books.get(i1).getId().equals(bookIds.get(i1))) {
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

    public void updateAuthor(Author author) {
        for (int i = 0; i < authors.size(); i++) {
            try {
                if (authors.get(i).getId().equals(author.getId())){
                    authors.remove(i);
                    authors.add(i, author);
                }
                break;
            } catch (Exception e) {
                i++;
            }
        }
    }

    public void updateBook(Book book) {
        for (int i = 0; i < books.size(); i++) {
            try {
                if (books.get(i).getId().equals(book.getId())){
                    books.remove(i);
                    books.add(i, book);
                }
            } catch (Exception e) {
                i++;
            }
        }
    }

    public void deleteAuthor(String authorId) {
        for (int i = 0; i < authors.size(); i++) {
            try {
                if (authorId.equals(authors.get(i).getId())) {
                    authors.remove(i);
                }
            }
            catch (Exception exception){
                i++;
            }
        }

        for (int i = 0; i < bookAuthors.size(); i++) {
            try {
                if (bookAuthors.get(i).getAuthorId().equals(authorId)) {
                    bookAuthors.remove(i);
                }
            }
            catch (Exception exception){
                i++;
            }
        }
    }

    public void deleteBook(String bookId) {
        for (int i = 0; i < books.size(); i++) {
            try {
                if (books.get(i).getId().equals(bookId)) {
                    books.remove(i);
                }
            } catch (Exception exception) {
                i++;
            }
        }
    }
    public ArrayList<Book> findAllBooks() {
        return this.books;
    }

    public ArrayList<Author> findAllAuthors() {
        return this.authors;
    }

    public ArrayList<BookAuthor> findAllBookAuthors() {
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