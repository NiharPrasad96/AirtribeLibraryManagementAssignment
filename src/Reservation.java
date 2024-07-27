public class Reservation {
    private Book book;
    private Patron patron;

    // Constructor, getters, and setters
    public Reservation(Book book, Patron patron) {
        this.book = book;
        this.patron = patron;
    }

    public Book getBook() {
        return book;
    }

    public Patron getPatron() {
        return patron;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "book=" + book +
                ", patron=" + patron +
                '}';
    }
}

