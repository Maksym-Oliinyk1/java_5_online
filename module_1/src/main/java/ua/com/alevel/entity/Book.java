package ua.com.alevel.entity;

public class Book extends BaseEntity {
    private String bookName;
    private String genre;
    private double rate;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Bool{" +
                "ID ='" + getId() + '\'' +
                "Name ='" + bookName + '\'' +
                ", Genre ='" + genre + '\'' +
                ", Rate ='" + rate + '\'' +
                "} " + super.toString();
    }
}
