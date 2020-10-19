package shoppingcart.dao;

import java.io.IOException;
import java.util.Date;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import shoppingcart.entity.Product;
import shoppingcart.form.ProductForm;
import shoppingcart.model.ProductInfo;
import shoppingcart.pagination.PaginationResult;

@Transactional
@Repository
public class ProductDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    public Product findProduct(String bookID) {
        try {
            String sql = "Select e from " + Product.class.getName() + " e Where e.bookID =:bookID ";
 
            Session session = this.sessionFactory.getCurrentSession();
            Query<Product> query = session.createQuery(sql, Product.class);
            query.setParameter("bookID", bookID);
            return (Product) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
 
    public ProductInfo findProductInfo(String bookID) {
        Product product = this.findProduct(bookID);
        if (product == null) {
            return null;
        }
        return new ProductInfo(product.getBookID(), product.getTitle(), product.getPrice());
    }
 
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void save(ProductForm productForm) {
 
        Session session = this.sessionFactory.getCurrentSession();
        String bookID = productForm.getBookID();
 
        Product product = null;
 
        boolean isNew = false;
        if (bookID != null) {
            product = this.findProduct(bookID);
        }
        if (product == null) {
            isNew = true;
            product = new Product();
            product.setDateCreated(new Date());
        }
        product.setBookID(bookID);
        product.setTitle(productForm.getTitle());
        product.setPrice(productForm.getPrice());
 
        if (productForm.getFileData() != null) {
            byte[] cover = null;
            try {
                cover = productForm.getFileData().getBytes();
            } catch (IOException e) {
            }
            if (cover != null && cover.length > 0) {
                product.setCover(cover);
            }
        }
        if (isNew) {
            session.persist(product);
        }
        // If error in DB, Exceptions will be thrown out immediately
        session.flush();
    }
 
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage,
            String likeName) {
        String sql = "Select new " + ProductInfo.class.getName() //
                + "(p.bookID, p.title, p.price) " + " from "//
                + Product.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.title) like :likeName ";
        }
        sql += " order by p.dateCreated desc ";
        // 
        Session session = this.sessionFactory.getCurrentSession();
        Query<ProductInfo> query = session.createQuery(sql, ProductInfo.class);
 
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
    }
 
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
        return queryProducts(page, maxResult, maxNavigationPage, null);
    }
 
}
