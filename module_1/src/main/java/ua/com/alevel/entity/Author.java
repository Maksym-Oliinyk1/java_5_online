package ua.com.alevel.entity;

public class Author extends BaseEntity {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "ID ='" + getId() + '\'' +
                "First Name ='" + firstName + '\'' +
                ", Last Name ='" + lastName + '\'' +
                "} " + super.toString();
    }
}
