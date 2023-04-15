package ua.com.alevel.controller;

import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.AuthorDaoCsv;
import ua.com.alevel.entity.Author;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Controller {
    private final AuthorDao authorDao = new AuthorDaoCsv();

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
        System.out.println("--------------------------------------------------------|");
        System.out.println("                                                        |");
        System.out.println("If you want create author, please enter 1               |");
        System.out.println("                                                        |");
        System.out.println("If you want update author, please enter 2               |");
        System.out.println("                                                        |");
        System.out.println("If you want delete author, please enter 3               |");
        System.out.println("                                                        |");
        System.out.println("If you want find author, please enter 4                 |");
        System.out.println("                                                        |");
        System.out.println("If you want find all authors, please enter 5            |");
        System.out.println("                                                        |");
        System.out.println("If you want close, please enter 6                       |");
        System.out.println("                                                        |");
        System.out.println("--------------------------------------------------------|");
        System.out.println();
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll();
            case "6" -> System.exit(0);
        }
        menu();
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter the first name:");
        String firstName = reader.readLine();
        System.out.println("Please enter the last name:");
        String lastName = reader.readLine();
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.create(author);
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("Please enter the first name:");
        String firstName = reader.readLine();
        System.out.println("Please enter the last name:");
        String lastName = reader.readLine();
        System.out.println("Please enter the id of author, you want to update");
        String id = reader.readLine();
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setId(id);
        authorDao.update(author);
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter the id:");
        String id = reader.readLine();
        authorDao.delete(id);
    }

    private void findById(BufferedReader reader) throws IOException {
        System.out.println("Please enter the id:");
        String id = reader.readLine();
        Author author = authorDao.findById(id);
        System.out.println("author = " + author);
    }

    private void findAll() {
        List<Author> authors = authorDao.findAll();
        for (Author author : authors) {
            System.out.println("author = " + author);
        }
    }
}