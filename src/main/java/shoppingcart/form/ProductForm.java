package shoppingcart.form;

import org.springframework.web.multipart.MultipartFile;

import shoppingcart.entity.Product;

public class ProductForm {
    private String bookID;
    private String title;
    private double price;
 
    private boolean newProduct = false;
 
    // Upload file.
    private MultipartFile fileData;
 
    public ProductForm() {
        this.newProduct= true;
    }
 
    public ProductForm(Product product) {
        this.bookID = product.getBookID();
        this.title = product.getTitle();
        this.price = product.getPrice();
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
 
    public MultipartFile getFileData() {
        return fileData;
    }
 
    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }
 
    public boolean isNewProduct() {
        return newProduct;
    }
 
    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }
 
}
