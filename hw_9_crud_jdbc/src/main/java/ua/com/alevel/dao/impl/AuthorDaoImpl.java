package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dto.AuthorDTO;
import ua.com.alevel.entity.Author;
import ua.com.alevel.service.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AuthorDaoImpl implements AuthorDao {

    private static final String CREATE_AUTHOR = "insert into authors value (default, ?, ?, ?)";
    private static final String UPDATE_AUTHOR = "update authors set first_name = ?, last_name = ?, age = ? where id = ?";
    private static final String DELETE_AUTHOR = "delete from authors where id = ?";
    private static final String FIND_ALL_AUTHORS = "select * from authors";
    private static final String FIND_AUTHORS_BY_ID = "select * from authors where id = ";
    private static final String ATTACH_BOOK_TO_AUTHOR = "insert into book_author values(?, ?)";
    private static final String DETACH_AUTHOR_TO_BOOK = "delete from book_author where book_id = ? and author_id = ?";
    private static final String FIND_ALL_AUTHOR_STATISTICS = """
            SELECT authors.id, authors.first_name, authors.last_name, authors.age, COUNT(*) as book_count
            FROM authors
            JOIN book_author ON authors.id = book_author.author_id
            JOIN books ON books.id = book_author.book_id
            GROUP BY authors.id, authors.first_name, authors.last_name, authors.age""";
    private final Connection connection = Service.getInstance().getConnection();
    private final Statement statement = Service.getInstance().getStatement();

    @Override
    public void create(Author author) {
        try (PreparedStatement ps = connection.prepareStatement(CREATE_AUTHOR)) {
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.setInt(3, author.getAge());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Author author) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_AUTHOR)) {
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.setInt(3, author.getAge());
            ps.setLong(4, author.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_AUTHOR)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Author> findById(Long id) {
        try (ResultSet rs = statement.executeQuery(FIND_AUTHORS_BY_ID + id)) {
            rs.next();
            return Optional.of(convertResultSetToAuthors(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try (ResultSet rs = statement.executeQuery(FIND_ALL_AUTHORS)) {
            while (rs.next()) {
                authors.add(convertResultSetToAuthors(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authors;
    }

    @Override
    public void attachBookToAuthor(Long bookId, Long authorId) {
        try (PreparedStatement ps = connection.prepareStatement(ATTACH_BOOK_TO_AUTHOR)) {
            ps.setLong(1, bookId);
            ps.setLong(2, authorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void detachAuthorToBook(Long bookId, Long authorId) {
        try (PreparedStatement ps = connection.prepareStatement(DETACH_AUTHOR_TO_BOOK)) {
            ps.setLong(1, bookId);
            ps.setLong(2, authorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<AuthorDTO> findAuthorStatistics() {
        List<AuthorDTO> authors = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery(FIND_ALL_AUTHOR_STATISTICS)) {
            while (resultSet.next()) {
                authors.add(convertResultSetToAuthorDTO(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authors;
    }

    private Author convertResultSetToAuthors(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int age = resultSet.getInt("age");
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setAge(age);
        author.setId(id);
        return author;
    }

    private AuthorDTO convertResultSetToAuthorDTO(ResultSet resultSet) throws SQLException {
        AuthorDTO authorDto = new AuthorDTO();
        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int age = resultSet.getInt("age");
        Long count = resultSet.getLong("book_count");
        authorDto.setId(id);
        authorDto.setFirstName(firstName);
        authorDto.setLastName(lastName);
        authorDto.setAge(age);
        authorDto.setBookCount(count);
        return authorDto;
    }
}
