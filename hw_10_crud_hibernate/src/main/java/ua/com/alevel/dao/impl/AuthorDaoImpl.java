package ua.com.alevel.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.*;

public class AuthorDaoImpl implements AuthorDao {
    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Author author) {
        Transaction transaction;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void update(Author author) {
        Transaction transaction;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(author);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Author author = session.find(Author.class, id);
            session.delete(author);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            rollbackTransaction(transaction);
        }
    }

    @Override
    public Optional<Author> findById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Author author = session.find(Author.class, id);
            transaction.commit();
            return Optional.of(author);
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            rollbackTransaction(transaction);
            return Optional.empty();
        }
    }

    @Override
    public Collection<Author> findAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Author ");
            List<Author> authors = query.getResultList();
            return authors;
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            rollbackTransaction(transaction);
        }
        return Collections.emptyList();
    }

    @Override
    public void attachBookToAuthor(Book book, Author author) {
        Set<Book> books = author.getBooks();
        books.add(book);
        update(author);
    }

    @Override
    public void detachBookToAuthor(Book book, Author author) {
        Set<Book> books = author.getBooks();
        books.removeIf(bks -> bks.getId().equals(book.getId()));
        update(author);

    }

    private void rollbackTransaction(Transaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }
}
