
    public class Book {
    private String id;
    private String author;
    private String title;
    private boolean isBorrowed;
    public Book(String id, String author, String title){
        this.id=id;
        this.author=author;
        this.title=title;
        this.isBorrowed=false;
    }
    public String getId(){
       return id;
    }
    public String getAuthor(){
        return author;
    }
    public String getTitle(){
        return title;
    }
    public boolean isBorrowed(){
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed){
        this.isBorrowed=borrowed;

    }
}