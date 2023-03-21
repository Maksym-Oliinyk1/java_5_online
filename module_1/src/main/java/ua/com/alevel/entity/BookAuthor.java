package ua.com.alevel.entity;

public class BookAuthor {

    private static String bookId;
    private String authorId;

    public static String getBookId() {
     return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Book and Author{" +
                "ID of  the book ='" + getBookId() + '\'' +
                "ID of the author ='" + getAuthorId() + '\'' +
                "} " + super.toString();
    }
}
