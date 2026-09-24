import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. View All Books");
            System.out.println("4. View All Members");
            System.out.println("5. Search Book by Title");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine().trim());

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Book ID: ");
                        String bid = sc.nextLine();
                        System.out.print("Enter Author: ");
                        String author = sc.nextLine();
                        System.out.print("Enter Title: ");
                        String title = sc.nextLine();
                        library.addBook(new Book(bid, author, title));
                        System.out.println("Book added successfully.");
                        break;

                    case 2:
                        System.out.print("Enter Member ID: ");
                        String mid = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Borrow Limit: ");
                        int limit = Integer.parseInt(sc.nextLine().trim());
                        library.registerMember(new Member(mid, name, limit));
                        System.out.println("Member registered successfully.");
                        break;

                    case 3:
                        library.viewAllBooks();
                        break;

                    case 4:
                        library.viewAllMembers();
                        break;

                    case 5:
                        System.out.print("Enter title to search: ");
                        String searchTitle = sc.nextLine();
                        Book found = library.searchBookByTitle(searchTitle);
                        if (found == null) {
                            System.out.println("Book not found.");
                        } else {
                            System.out.println(found.getId() + " | " + found.getTitle() + " | "
                                    + found.getAuthor() + " | " + (found.isBorrowed() ? "Borrowed" : "Available"));
                        }
                        break;

                    case 6:
                        System.out.print("Enter book title to issue: ");
                        String issueTitle = sc.nextLine();
                        System.out.print("Enter Member ID: ");
                        String issueMemberId = sc.nextLine();
                        library.issueBook(issueTitle, issueMemberId);
                        break;

                    case 7:
                        System.out.print("Enter book title to return: ");
                        String returnTitle = sc.nextLine();
                        System.out.print("Enter Member ID: ");
                        String returnMemberId = sc.nextLine();
                        library.returnBook(returnTitle, returnMemberId);
                        break;

                    case 0:
                        System.out.println("Exiting... Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (LibraryException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        } while (choice != 0);

        sc.close();
    }
}