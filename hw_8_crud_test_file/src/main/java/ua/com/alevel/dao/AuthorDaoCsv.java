package ua.com.alevel.dao;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.entity.Author;
import ua.com.alevel.utill.DbUtil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AuthorDaoCsv implements AuthorDao {

    public static final File AUTHORS_DATA_BASE_AUTHORS = new File("Authors_Data_Base/authors.csv");

    private List<Author> authors = new ArrayList<>();

    @Override
    public void create(Author author) {
        initAuthors();
        author.setId(DbUtil.getInstance().generateId(authors));
        authors.add(author);
        writeAuthorsToCSV();
        System.out.println("Author created!");
    }

    @Override
    public void update(Author author) {
        if (author != null) {
            initAuthors();
            for (int i = 0; i < authors.size(); i++) {
                if (authors.get(i).getId().equals(author.getId())) {
                    authors.set(i, author);
                }
            }
            writeAuthorsToCSV();
            System.out.println("author updated!");
        }
    }

    @Override
    public void delete(String id) {
        initAuthors();
        authors.removeIf(author -> author.getId().equals(id));
        writeAuthorsToCSV();
        System.out.println("Author deleted!");
    }

    @Override
    public Author findById(String id) {
        initAuthors();
        for(Author author : authors) {
            if(id.equals(author.getId())) {
                return author;
            }
        }
        return null;
    }

    @Override
    public List<Author> findAll() {
        initAuthors();
        return authors;
    }

    private void initAuthors() {
        try (CSVReader cvsReader = new CSVReader(new FileReader(AUTHORS_DATA_BASE_AUTHORS))) {
            List<String[]> list = cvsReader.readAll();
            authors = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(list)) {
                for (String[] elements : list) {
                    Author author = new Author();
                    author.setId(elements[0]);
                    author.setFirstName(elements[1]);
                    author.setLastName(elements[2]);
                    authors.add(author);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeAuthorsToCSV() {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(AUTHORS_DATA_BASE_AUTHORS))) {
            List<String[]> list = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(authors)) {
                for (Author author : authors) {
                    String[] st = new String[]{
                            author.getId(),
                            author.getFirstName(),
                            author.getLastName(),
                    };
                    list.add(st);
                }
                csvWriter.writeAll(list);
                csvWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
