package ua.com.alevel.entity;

public class Author extends BaseEntity {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = this.firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = this.lastName;
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
