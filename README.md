# Library Management System

A console-based Library Management System built in Java using Core OOP principles, custom exception handling, and file-based data persistence.

## Features

- Add new books to the library
- Register new members with a borrow limit
- View all books and their availability status
- Search for a book by title
- Issue a book to a registered member
- Return a borrowed book
- Custom exception handling for invalid operations (e.g. issuing an already-borrowed book, exceeding borrow limit)
- Data persistence using file handling — books and members are saved to `books.txt` and `members.txt`, so data is retained across program runs

## Tech Stack

- Core Java
- Object-Oriented Programming (Classes, Encapsulation)
- Exception Handling (Custom Exceptions)
- File I/O (BufferedReader, FileWriter)
- Collections (ArrayList)

## Project Structure
src/
├── Book.java # Book entity (id, title, author, borrowed status)
├── Member.java # Member entity (id, name, borrow limit, books borrowed)
├── Library.java # Core logic: add/view/search/issue/return + file handling
├── LibraryException.java # Custom exception for library-specific errors
└── Main.java # Console menu-driven entry point


## How to Run

1. Clone this repository
2. Open the project in IntelliJ IDEA (or any Java IDE)
3. Run `Main.java`
4. Follow the on-screen menu to add books, register members, issue/return books

## Sample Menu
===== LIBRARY MANAGEMENT SYSTEM =====

Add Book
Register Member
View All Books
View All Members
Search Book by Title
Issue Book
Return Book
Exit


## Author

**Vaibhav Jadhao**
Final Year B.Tech CSE, G H Raisoni College of Engineering
[LinkedIn](https://linkedin.com/in/vaibhav-jadhao-4925a1410)