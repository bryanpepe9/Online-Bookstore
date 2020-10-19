package shoppingcart.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shoppingcart.entity.Order;
import shoppingcart.entity.OrderDetail;
import shoppingcart.entity.Product;
import shoppingcart.model.CartInfo;
import shoppingcart.model.CartLineInfo;
import shoppingcart.model.CustomerInfo;
import shoppingcart.model.OrderDetailInfo;
import shoppingcart.model.OrderInfo;
import shoppingcart.pagination.PaginationResult;

@Transactional
@Repository
public class OrderDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    @Autowired
    private ProductDAO productDAO;
 
    private int getMaxorderID() {
        String sql = "Select max(o.orderID) from " + Order.class.getName() + " o ";
        Session session = this.sessionFactory.getCurrentSession();
        Query<Integer> query = session.createQuery(sql, Integer.class);
        Integer value = (Integer) query.getSingleResult();
        if (value == null) {
            return 0;
        }
        return value;
    }
 
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(CartInfo cartInfo) {
        Session session = this.sessionFactory.getCurrentSession();
 
        int orderID = this.getMaxorderID() + 1;
        Order order = new Order();
 
        order.setUserID(UUID.randomUUID().toString());
        order.setOrderID(orderID);
        order.setDateOrdered(new Date());
        order.setTotalPrice(cartInfo.getAmountTotal());
 
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        order.setUserName(customerInfo.getName());
        order.setUserEmail(customerInfo.getEmail());
        order.setUserPhone(customerInfo.getPhone());
        order.setUserAddress(customerInfo.getAddress());
 
        session.persist(order);
 
        List<CartLineInfo> lines = cartInfo.getCartLines();
 
        for (CartLineInfo line : lines) {
            OrderDetail detail = new OrderDetail();
            detail.setId(UUID.randomUUID().toString());
            detail.setOrder(order);
            detail.setAmount(line.getAmount());
            detail.setPrice(line.getProductInfo().getPrice());
            detail.setQuanity(line.getQuantity());
 
            String bookID = line.getProductInfo().getBookID();
            Product product = this.productDAO.findProduct(bookID);
            detail.setProduct(product);
 
            session.persist(detail);
        }
 
        // Order Number!
        cartInfo.setOrderID(orderID);
        // Flush
        session.flush();
    }
 
    // @page = 1, 2, ...
    public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
        String sql = "Select new " + OrderInfo.class.getName()//
                + "(ord.id, ord.dateOrdered, ord.orderID, ord.totalPrice, "
                + " ord.userName, ord.userAddress, ord.userEmail, ord.userPhone) " + " from "
                + Order.class.getName() + " ord "//
                + " order by ord.orderID desc";
 
        Session session = this.sessionFactory.getCurrentSession();
        Query<OrderInfo> query = session.createQuery(sql, OrderInfo.class);
        return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
    }
 
    public Order findOrder(String orderId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(Order.class, orderId);
    }
 
    public OrderInfo getOrderInfo(String orderId) {
        Order order = this.findOrder(orderId);
        if (order == null) {
            return null;
        }
        return new OrderInfo(order.getUserID(), order.getDateOrdered(), //
                order.getOrderID(), order.getTotalPrice(), order.getUserName(), //
                order.getUserAddress(), order.getUserEmail(), order.getUserPhone());
    }
 
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
        String sql = "Select new " + OrderDetailInfo.class.getName() //
                + "(d.id, d.product.bookID, d.product.name , d.quanity,d.price,d.totalPrice) "//
                + " from " + OrderDetail.class.getName() + " d "//
                + " where d.order.id = :orderId ";
 
        Session session = this.sessionFactory.getCurrentSession();
        Query<OrderDetailInfo> query = session.createQuery(sql, OrderDetailInfo.class);
        query.setParameter("orderId", orderId);
 
        return query.getResultList();
    }
 
}