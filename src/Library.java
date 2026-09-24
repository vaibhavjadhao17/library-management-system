import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Library {
    private List<Book> books;
    private List<Member> members;

    private static final String BOOKS_FILE = "books.txt";
    private static final String MEMBERS_FILE = "members.txt";

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        loadBooks();
        loadMembers();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    public void registerMember(Member member) {
        members.add(member);
        saveMembers();
    }

    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book b : books) {
            System.out.println(b.getId() + " | " + b.getTitle() + " | " + b.getAuthor()
                    + " | " + (b.isBorrowed() ? "Borrowed" : "Available"));
        }
    }

    public void viewAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        for (Member m : members) {
            System.out.println(m.getId() + " | " + m.getName()
                    + " | Borrowed: " + m.getBooksBorrowed() + "/" + m.getBorrowLimit());
        }
    }

    public Book searchBookByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    public Member findMemberById(String memberId) {
        for (Member m : members) {
            if (m.getId().equals(memberId)) {
                return m;
            }
        }
        return null;
    }

    public void issueBook(String bookTitle, String memberId) {
        Book book = searchBookByTitle(bookTitle);
        Member member = findMemberById(memberId);

        if (book == null) {
            throw new LibraryException("Book not found.");
        }
        if (member == null) {
            throw new LibraryException("Member not found.");
        }
        if (book.isBorrowed()) {
            throw new LibraryException("Book is already issued.");
        }
        if (!member.canBorrow()) {
            throw new LibraryException("Member has reached their borrow limit.");
        }

        book.setBorrowed(true);
        member.incrementBorrowedCount();
        saveBooks();
        saveMembers();
        System.out.println("Book issued successfully to " + member.getName() + ".");
    }

    public void returnBook(String bookTitle, String memberId) {
        Book book = searchBookByTitle(bookTitle);
        Member member = findMemberById(memberId);

        if (book == null) {
            throw new LibraryException("Book not found.");
        }
        if (member == null) {
            throw new LibraryException("Member not found.");
        }
        if (!book.isBorrowed()) {
            throw new LibraryException("This book was not issued.");
        }

        book.setBorrowed(false);
        member.decrementBorrowedCount();
        saveBooks();
        saveMembers();
        System.out.println("Book returned successfully by " + member.getName() + ".");
    }

    private void saveBooks() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            for (Book b : books) {
                pw.println(b.getId() + "," + b.getAuthor() + "," + b.getTitle() + "," + b.isBorrowed());
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    private void loadBooks() {
        File file = new File(BOOKS_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] p = line.split(",");
                Book b = new Book(p[0], p[1], p[2]);
                b.setBorrowed(Boolean.parseBoolean(p[3]));
                books.add(b);
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }

    private void saveMembers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(MEMBERS_FILE))) {
            for (Member m : members) {
                pw.println(m.getId() + "," + m.getName() + "," + m.getBorrowLimit() + "," + m.getBooksBorrowed());
            }
        } catch (IOException e) {
            System.out.println("Error saving members: " + e.getMessage());
        }
    }

    private void loadMembers() {
        File file = new File(MEMBERS_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] p = line.split(",");
                Member m = new Member(p[0], p[1], Integer.parseInt(p[2]));
                int borrowedCount = Integer.parseInt(p[3]);
                for (int i = 0; i < borrowedCount; i++) {
                    m.incrementBorrowedCount();
                }
                members.add(m);
            }
        } catch (IOException e) {
            System.out.println("Error loading members: " + e.getMessage());
        }
    }
}