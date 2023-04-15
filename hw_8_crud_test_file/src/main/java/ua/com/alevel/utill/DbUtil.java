package ua.com.alevel.utill;

import ua.com.alevel.entity.Author;

import java.util.List;
import java.util.UUID;

public final class DbUtil {

    private static DbUtil instance;

    private DbUtil() { }

    public static DbUtil getInstance() {
        if (instance == null) {
            instance = new DbUtil();
        }
        return instance;
    }

    public String generateId(List<Author> authors) {
        String id = UUID.randomUUID().toString();
        if (authors.stream().anyMatch(author -> author.getId().equals(id))) {
            return generateId(authors);
        }
        return id;
    }
}
