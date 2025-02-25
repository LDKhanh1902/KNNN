package vn.DA_KNNN.Model.DTO;

public class Book {
    private int bookId;
    private String title;
    private int publisherId;
    private int categoryId;
    private java.sql.Date publicationDate;
    private double price;
    private int authorId;
    private java.sql.Date entryDate;
    private double purchasePrice;
    private int quantity;

    // Getters and Setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public java.sql.Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(java.sql.Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public java.sql.Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(java.sql.Date entryDate) {
        this.entryDate = entryDate;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
