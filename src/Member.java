public class Member {
    private String id;
    private String name;
    private int borrowLimit;
    private int booksBorrowed;
   public Member(String id, String name,int borrowLimit){
        this.id=id;
        this.name=name;
        this.borrowLimit=borrowLimit;
        this.booksBorrowed=0;
    }
    public String getId(){
       return id;
    }
    public String getName(){
       return name;
    }
    public int getBorrowLimit(){
       return borrowLimit;
    }
    public int getBooksBorrowed(){
       return booksBorrowed;
    }
    public boolean canBorrow() {
        return booksBorrowed < borrowLimit;
    }
    public void incrementBorrowedCount() {
        booksBorrowed++;
    }
    public void decrementBorrowedCount() {
        if (booksBorrowed > 0) {
            booksBorrowed--;
        }
    }
}