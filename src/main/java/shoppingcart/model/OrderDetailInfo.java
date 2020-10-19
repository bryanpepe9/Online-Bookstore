package shoppingcart.model;

public class OrderDetailInfo {
    private String id;
 
    private String order;
    private String product;
 
    private int quanity;
    private double price;
    private double amount;
 
    public OrderDetailInfo() {
 
    }
 
    // Using for JPA/Hibernate Query.
    public OrderDetailInfo(String id, String order, //
            String product, int quanity, double price, double amount) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quanity = quanity;
        this.price = price;
        this.amount = amount;
    }
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getOrder() {
        return order;
    }
 
    public void setOrder(String order) {
        this.order = order;
    }
 
    public String getProduct() {
        return product;
    }
 
    public void setProduct(String product) {
        this.product = product;
    }
 
    public int getQuanity() {
        return quanity;
    }
 
    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    public double getAmount() {
        return amount;
    }
 
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
