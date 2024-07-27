import java.util.List;

public class Main {
    public static void main(String[] args) {
        Notification notification = new EmailNotification();
        RecommendationStrategy recommendationStrategy = new SimpleRecommendationStrategy();
        Library library = new Library(notification, recommendationStrategy);

        // Add branches
        Branch branch1 = new Branch("B001", "Central Library");
        Branch branch2 = new Branch("B002", "East Branch");
        library.addBranch(branch1);
        library.addBranch(branch2);

        // Add books to branches
        Book book1 = new Book("1984", "George Orwell", "123456789", 1949);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "987654321", 1960);
        library.addBook(book1, "B001");
        library.addBook(book2, "B002");

        // Add patrons
        Patron patron1 = new Patron("John Doe", "P001");
        Patron patron2 = new Patron("Jane Smith", "P002");
        library.addPatron(patron1);
        library.addPatron(patron2);

        // Checkout a book
        library.checkoutBook("123456789", "P001");

        // Display available books
        System.out.println("Available books: " + library.getAvailableBooks());

        // Reserve a book
        library.reserveBook("987654321", "P002");

        // Return a book
        library.returnBook("123456789", "P001");

        // Display available books
        System.out.println("Available books: " + library.getAvailableBooks());

        // Recommend books
        List<Book> recommendations = library.recommendBooks("P001");
        System.out.println("Recommendations for P001: " + recommendations);
    }
}
