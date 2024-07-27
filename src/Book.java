    public class Book {
        
        private String title;
        private String author;
        private String isbn;
        private int publicationYear;
        private boolean isBorrowed;
    
        // Constructor, getters, and setters
        public Book(String title, String author, String isbn, int publicationYear) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.publicationYear = publicationYear;
            this.isBorrowed = false;
        }
    
        // Getters and setters
        public String getTitle() {
            return title;
        }
    
        public String getAuthor() {
            return author;
        }
    
        public String getIsbn() {
            return isbn;
        }
    
        public int getPublicationYear() {
            return publicationYear;
        }
    
        public boolean isBorrowed() {
            return isBorrowed;
        }
    
        public void setBorrowed(boolean borrowed) {
            isBorrowed = borrowed;
        }
    
        @Override
        public String toString() {
            return "Book{" +
                    "title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", isbn='" + isbn + '\'' +
                    ", publicationYear=" + publicationYear +
                    ", isBorrowed=" + isBorrowed +
                    '}';
        }
    }
    
