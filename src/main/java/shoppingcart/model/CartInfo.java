package shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

public class CartInfo {
	 
    private int orderID;
 
    private CustomerInfo customerInfo;
 
    private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();
 
    public CartInfo() {
 
    }
 
    public int getOrderID() {
        return orderID;
    }
 
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
 
    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }
 
    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
 
    public List<CartLineInfo> getCartLines() {
        return this.cartLines;
    }
 
    private CartLineInfo findLineByBookID(String bookID) {
        for (CartLineInfo line : this.cartLines) {
            if (line.getProductInfo().getBookID().equals(bookID)) {
                return line;
            }
        }
        return null;
    }
 
    public void addProduct(ProductInfo productInfo, int quantity) {
        CartLineInfo line = this.findLineByBookID(productInfo.getBookID());
 
        if (line == null) {
            line = new CartLineInfo();
            line.setQuantity(0);
            line.setProductInfo(productInfo);
            this.cartLines.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }
 
    public void validate() {
 
    }
 
    public void updateProduct(String bookID, int quantity) {
        CartLineInfo line = this.findLineByBookID(bookID);
 
        if (line != null) {
            if (quantity <= 0) {
                this.cartLines.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }
 
    public void removeProduct(ProductInfo productInfo) {
        CartLineInfo line = this.findLineByBookID(productInfo.getBookID());
        if (line != null) {
            this.cartLines.remove(line);
        }
    }
 
    public boolean isEmpty() {
        return this.cartLines.isEmpty();
    }
 
    public boolean isValidCustomer() {
        return this.customerInfo != null && this.customerInfo.isValid();
    }
 
    public int getQuantityTotal() {
        int quantity = 0;
        for (CartLineInfo line : this.cartLines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }
 
    public double getAmountTotal() {
        double total = 0;
        for (CartLineInfo line : this.cartLines) {
            total += line.getAmount();
        }
        return total;
    }
 
    public void updateQuantity(CartInfo cartForm) {
        if (cartForm != null) {
            List<CartLineInfo> lines = cartForm.getCartLines();
            for (CartLineInfo line : lines) {
                this.updateProduct(line.getProductInfo().getBookID(), line.getQuantity());
            }
        }
 
    }
 
}