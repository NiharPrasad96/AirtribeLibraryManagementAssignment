import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Branch> branches;
    private List<Patron> patrons;
    private List<Reservation> reservations;
    private Notification notification;
    private RecommendationStrategy recommendationStrategy;

    // Constructor
    public Library(Notification notification, RecommendationStrategy recommendationStrategy) {
        this.branches = new ArrayList<>();
        this.patrons = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.notification = notification;
        this.recommendationStrategy = recommendationStrategy;
    }

    // Branch management
    public void addBranch(Branch branch) {
        branches.add(branch);
    }

    public void transferBook(String isbn, String fromBranchId, String toBranchId) {
        Branch fromBranch = findBranchById(fromBranchId);
        Branch toBranch = findBranchById(toBranchId);
        if (fromBranch != null && toBranch != null) {
            Book book = findBookInBranch(isbn, fromBranch);
            if (book != null) {
                fromBranch.removeBook(book);
                toBranch.addBook(book);
            }
        }
    }

    private Branch findBranchById(String branchId) {
        for (Branch branch : branches) {
            if (branch.getBranchId().equals(branchId)) {
                return branch;
            }
        }
        return null;
    }

    private Book findBookInBranch(String isbn, Branch branch) {
        for (Book book : branch.getBooks()) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    // Patron management
    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void updatePatron(String patronId, Patron updatedPatron) {
        for (Patron patron : patrons) {
            if (patron.getPatronId().equals(patronId)) {
                patron = updatedPatron;
                break;
            }
        }
    }

    // Reservation system
    public void reserveBook(String isbn, String patronId) {
        Book book = searchBookByIsbn(isbn);
        Patron patron = findPatronById(patronId);
        if (book != null && patron != null) {
            reservations.add(new Reservation(book, patron));
            notification.notify(patron, "Book reserved: " + book.getTitle());
        }
    }

    public void notifyReservations(Book book) {
        List<Reservation> toRemove = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getBook().equals(book)) {
                notification.notify(reservation.getPatron(), "Book available: " + book.getTitle());
                toRemove.add(reservation);
            }
        }
        reservations.removeAll(toRemove);
    }

    private Patron findPatronById(String patronId) {
        for (Patron patron : patrons) {
            if (patron.getPatronId().equals(patronId)) {
                return patron;
            }
        }
        return null;
    }

    // Lending process
    public boolean checkoutBook(String isbn, String patronId) {
        Book book = searchBookByIsbn(isbn);
        if (book != null && !book.isBorrowed()) {
            for (Patron patron : patrons) {
                if (patron.getPatronId().equals(patronId)) {
                    patron.borrowBook(book);
                    book.setBorrowed(true);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean returnBook(String isbn, String patronId) {
        Book book = searchBookByIsbn(isbn);
        if (book != null && book.isBorrowed()) {
            for (Patron patron : patrons) {
                if (patron.getPatronId().equals(patronId)) {
                    patron.returnBook(book);
                    book.setBorrowed(false);
                    notifyReservations(book);
                    return true;
                }
            }
        }
        return false;
    }

    // Inventory management
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Branch branch : branches) {
            availableBooks.addAll(branch.getBooks().stream()
                .filter(book -> !book.isBorrowed())
                .collect(Collectors.toList()));
        }
        return availableBooks;
    }

    public List<Book> getBorrowedBooks() {
        List<Book> borrowedBooks = new ArrayList<>();
        for (Branch branch : branches) {
            borrowedBooks.addAll(branch.getBooks().stream()
                .filter(Book::isBorrowed)
                .collect(Collectors.toList()));
        }
        return borrowedBooks;
    }

    // Book management
    public void addBook(Book book, String branchId) {
        Branch branch = findBranchById(branchId);
        if (branch != null) {
            branch.addBook(book);
        }
    }

    public void removeBook(Book book, String branchId) {
        Branch branch = findBranchById(branchId);
        if (branch != null) {
            branch.removeBook(book);
        }
    }

    public void updateBook(String isbn, Book updatedBook) {
        for (Branch branch : branches) {
            for (Book book : branch.getBooks()) {
                if (book.getIsbn().equals(isbn)) {
                    book = updatedBook;
                    break;
                }
            }
        }
    }

    public Book searchBookByTitle(String title) {
        for (Branch branch : branches) {
            for (Book book : branch.getBooks()) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    return book;
                }
            }
        }
        return null;
    }

    public Book searchBookByAuthor(String author) {
        for (Branch branch : branches) {
            for (Book book : branch.getBooks()) {
                if (book.getAuthor().equalsIgnoreCase(author)) {
                    return book;
                }
            }
        }
        return null;
    }

    public Book searchBookByIsbn(String isbn) {
        for (Branch branch : branches) {
            for (Book book : branch.getBooks()) {
                if (book.getIsbn().equals(isbn)) {
                    return book;
                }
            }
        }
        return null;
    }

    // Recommendation system
    public List<Book> recommendBooks(String patronId) {
        Patron patron = findPatronById(patronId);
        if (patron != null) {
            List<Book> allBooks = getAvailableBooks();
            return recommendationStrategy.recommendBooks(patron, allBooks);
        }
        return Collections.emptyList();
    }
}
