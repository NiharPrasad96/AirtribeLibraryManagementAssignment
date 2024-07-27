import java.util.List;

public interface RecommendationStrategy {
    List<Book> recommendBooks(Patron patron, List<Book> allBooks);
}
