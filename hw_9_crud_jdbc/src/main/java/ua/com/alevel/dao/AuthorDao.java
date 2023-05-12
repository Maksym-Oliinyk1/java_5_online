package ua.com.alevel.dao;

import ua.com.alevel.dto.AuthorDTO;
import ua.com.alevel.entity.Author;

import java.util.Collection;

public interface AuthorDao extends BaseDao<Author> {
    void attachBookToAuthor(Long bookId, Long authorId);

    void detachAuthorToBook(Long bookId, Long authorId);

    Collection<AuthorDTO> findAuthorStatistics();
}
