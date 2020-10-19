package shoppingcart.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Orders", //
        uniqueConstraints = { @UniqueConstraint(columnNames = "OrderID") })
public class Order implements Serializable {
 
    private static final long serialVersionUID = -2576670215015463100L;
 
    @Id
    @Column(name = "UserID", length = 50)
    private String userID;
 
    @Column(name = "DateOrdered", nullable = false)
    private Date dateOrdered;
 
    @Column(name = "OrderID", nullable = false)
    private int orderID;
 
    @Column(name = "TotalPrice", nullable = false)
    private double totalPrice;
 
    @Column(name = "UserName", length = 255, nullable = false)
    private String userName;
 
    @Column(name = "UserAddress", length = 255, nullable = false)
    private String userAddress;
 
    @Column(name = "UserEmail", length = 128, nullable = false)
    private String userEmail;
 
    @Column(name = "UserPhone", length = 128, nullable = false)
    private String userPhone;
 
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
 
}
