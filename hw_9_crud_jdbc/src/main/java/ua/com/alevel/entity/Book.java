package ua.com.alevel.entity;

public class Book extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                "id='" + getId() + '\'' +
                '}';
    }
}
