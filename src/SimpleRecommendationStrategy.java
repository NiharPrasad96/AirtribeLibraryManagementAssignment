import java.util.ArrayList;
import java.util.List;

public class SimpleRecommendationStrategy implements RecommendationStrategy {
    @Override
    public List<Book> recommendBooks(Patron patron, List<Book> allBooks) {
        // Example: recommend the first 3 books that the patron hasn't borrowed yet
        List<Book> recommendations = new ArrayList<>();
        for (Book book : allBooks) {
            if (!patron.getBorrowedBooks().contains(book) && recommendations.size() < 3) {
                recommendations.add(book);
            }
        }
        return recommendations;
    }
}
