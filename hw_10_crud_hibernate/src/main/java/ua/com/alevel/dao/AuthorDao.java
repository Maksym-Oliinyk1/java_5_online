package ua.com.alevel.dao;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public interface AuthorDao extends BaseDao<Author> {
    void attachBookToAuthor(Book book, Author author);

    void detachBookToAuthor(Book book, Author author);
}
