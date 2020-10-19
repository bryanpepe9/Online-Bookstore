package shoppingcart.model;

import shoppingcart.entity.Product;

public class ProductInfo {
    private String bookID;
    private String title;
    private double price;
 
    public ProductInfo() {
    }
 
    public ProductInfo(Product product) {
        this.bookID = product.getBookID();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
 
    // Using in JPA/Hibernate query
    public ProductInfo(String bookID, String title, double price) {
        this.bookID = bookID;
        this.title = title;
        this.price = price;
    }
 
    public String getBookID() {
        return bookID;
    }
 
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
}
