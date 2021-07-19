package dduwcom.mobile.finalreport;

import java.io.Serializable;

public class Book implements Serializable {
    private long _id;
    private String title;
    private String author;
    private String publisher;
    private String contents;
    private Date date;
    private int imageResId;

    public Book(String bookName, String author, String publisher, String contents, Date date) {
        this.title = bookName;
        this.author = author;
        this.publisher = publisher;
        this.contents = contents;
        this.date = date;
        imageResId = R.mipmap.ic_launcher;
    }

    public Book(long _id, String bookName, String author, String publisher, String contents, Date date) {
        this._id = _id;
        this.title = bookName;
        this.author = author;
        this.publisher = publisher;
        this.contents = contents;
        this.date = date;
        imageResId = R.mipmap.ic_launcher;
    }

    public Book(long _id, String bookName, String author, String publisher, String contents, Date date, int imageResId) {
        this._id = _id;
        this.title = bookName;
        this.author = author;
        this.publisher = publisher;
        this.contents = contents;
        this.date = date;
        this.imageResId = imageResId;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String bookName) {
        this.title = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getBookImageResId() { return imageResId; }

    public void setBookImageResId(int imageResId) { this.imageResId = imageResId; }
}
