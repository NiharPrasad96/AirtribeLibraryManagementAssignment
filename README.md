# AirtribeLibraryManagementAssignment

## Library management class Readme file

These are the classes and interfaces I have created to implement the Library management

Book: Represents a book in the library. It has attributes such as title, author, isbn, publicationYear, and isBorrowed.
Patron: Represents a library member. It has attributes such as name, patronId, and borrowedBooks (a list of books the patron has borrowed).
Branch: Represents a library branch. It has attributes such as branchId, name, and books (a list of books in the branch).
Reservation: Represents a reservation for a book by a patron. It has attributes book and patron.
Notification: An interface for sending notifications to patrons. It has a method notify(Patron, String).
EmailNotification: Implements the Notification interface to send email notifications.
RecommendationStrategy: An interface for recommending books to patrons. It has a method recommendBooks(Patron, List<Book>).
SimpleRecommendationStrategy: Implements the RecommendationStrategy interface with a simple recommendation algorithm.
Library: Manages books, patrons, branches, reservations, notifications, and recommendations. It uses the Notification and RecommendationStrategy interfaces. The library has methods for branch management, patron management, reservation system, lending process, inventory management, book management, and recommendation system.

