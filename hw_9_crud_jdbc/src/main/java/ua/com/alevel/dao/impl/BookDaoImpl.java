package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.BookDao;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {

    private static final String CREATE_BOOK = "insert into books values (default, ?)";
    private static final String UPDATE_BOOK = "update books set name = ? where id = ?";
    private static final String DELETE_BOOK = "delete from books where id = ?";
    private static final String FIND_ALL_BOOKS = "select * from books";
    private static final String FIND_BOOK_BY_ID = "select * from books where id = ";
    private final Connection connection = Service.getInstance().getConnection();
    private final Statement statement = Service.getInstance().getStatement();

    @Override
    public void create(Book book) {
        try (PreparedStatement ps = connection.prepareStatement(CREATE_BOOK)) {
            ps.setString(1, book.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void update(Book book) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_BOOK)) {
            ps.setString(1, book.getName());
            ps.setLong(2, book.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_BOOK)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (ResultSet rs = statement.executeQuery(FIND_BOOK_BY_ID + id)) {
            rs.next();
            return Optional.of(convertResultSetToBook(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (ResultSet rs = statement.executeQuery(FIND_ALL_BOOKS)) {
            while (rs.next()) {
                books.add(convertResultSetToBook(rs));
            }
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
        return books;
    }

    @Override
    public boolean existByName(String name) {
        try (PreparedStatement ps = connection.prepareStatement("select count(*) as count_of_books from books where name like ?")) {
            ps.setString(1, "%" + name + "%");
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            long count = resultSet.getLong("count_of_books");
            return count > 0;
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
        return false;
    }

    private Book convertResultSetToBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        book.setId(id);
        book.setName(name);
        return book;
    }
}
