package shoppingcart.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Books")
public class Product implements Serializable {
 
    private static final long serialVersionUID = -1000119078147252957L;
 
    @Id
    @Column(name = "BookID", length = 20, nullable = false)
    private String bookID;
 
    @Column(name = "Title", length = 255, nullable = false)
    private String title;
 
    @Column(name = "Price", nullable = false)
    private double price;
 
    @Lob
    @Column(name = "Cover", length = Integer.MAX_VALUE, nullable = true)
    private byte[] cover;
     
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateCreated", nullable = false)
    private Date dateCreated;
 
    public Product() {
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
 
    public Date getDateCreated() {
        return dateCreated;
    }
 
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
 
    public byte[] getCover() {
        return cover;
    }
 
    public void setCover(byte[] cover) {
        this.cover = cover;
    }
 
}
