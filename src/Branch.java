import java.util.ArrayList;
import java.util.List;

public class Branch {
    private String branchId;
    private String name;
    private List<Book> books;

    // Constructor, getters, and setters
    public Branch(String branchId, String name) {
        this.branchId = branchId;
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getBranchId() {
        return branchId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchId='" + branchId + '\'' +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}

