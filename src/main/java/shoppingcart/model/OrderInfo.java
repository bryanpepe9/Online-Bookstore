package shoppingcart.model;

import java.util.Date;
import java.util.List;
 
public class OrderInfo {
 
    private String userID;
    private Date dateOrdered;
    private int orderID;
    private double totalPrice;
 
    private String userName;
    private String userAddress;
    private String userEmail;
    private String userPhone;
 
    private List<OrderDetailInfo> details;
 
    public OrderInfo() {
 
    }
 
    // Using for Hibernate Query.
    public OrderInfo(String userID, Date dateOrdered, int orderID, //
            double totalPrice, String userName, String userAddress, //
            String userEmail, String userPhone) {
        this.userID = userID;
        this.dateOrdered = dateOrdered;
        this.orderID = orderID;
        this.totalPrice = totalPrice;
 
        this.userName = userName;
        this.userAddress = userAddress;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }
 
    public String getUserID() {
        return userID;
    }
 
    public void setUserID(String userID) {
        this.userID = userID;
    }
 
    public Date getDateOrdered() {
        return dateOrdered;
    }
 
    public void setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }
 
    public int getOrderID() {
        return orderID;
    }
 
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
 
    public double getTotalPrice() {
        return totalPrice;
    }
 
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getUserAddress() {
        return userAddress;
    }
 
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
 
    public String getUserEmail() {
        return userEmail;
    }
 
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
 
    public String getUserPhone() {
        return userPhone;
    }
 
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
 
    public List<OrderDetailInfo> getDetails() {
        return details;
    }
 
    public void setDetails(List<OrderDetailInfo> details) {
        this.details = details;
    }
 
}
