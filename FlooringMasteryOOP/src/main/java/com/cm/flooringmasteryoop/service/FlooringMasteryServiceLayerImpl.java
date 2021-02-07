
package com.cm.flooringmasteryoop.service;


import com.cm.flooringmasteryoop.dao.FlooringMasteryDao;
import com.cm.flooringmasteryoop.dao.FlooringMasteryDaoException;
import com.cm.flooringmasteryoop.dto.Order;
import com.cm.flooringmasteryoop.dto.Product;
import com.cm.flooringmasteryoop.dto.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author chris
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

 
    FlooringMasteryDao dao;

    public FlooringMasteryServiceLayerImpl( FlooringMasteryDao dao) {
     
        this.dao = dao;
    }


    @Override
    public Order addOrder(String name, String stateName, String productType, BigDecimal area, LocalDate date)
            throws InvalidProductException, 
            InvalidStateException, 
            InvalidCustomerException,
            InvalidAreaException {
        
        Product product = dao.getProduct(productType);
      
        if (product == null) {
            throw new InvalidProductException("Invalid Material");
        }
        StateTax state = dao.getState(stateName);
        if (state == null) {
            throw new InvalidStateException("Invalid State");
        }

        if (name == null || name.isEmpty()) {
            throw new InvalidCustomerException("Invalid Customer Name");
        }

        if (area == null || area.compareTo(new BigDecimal(0)) <= 0)  {
            throw new InvalidAreaException("Incorrect Area measurment");
        }

        Order newOrder = new Order(name, state, product, area, date);
//        newOrder.setProduct(product);
//        newOrder.setStateTax(state);
        return dao.addOrder(newOrder);

    }
    
    @Override
    public List<Order> listOrdersByDate(LocalDate date) throws InvalidOrderDate{
        List ordersByDate = dao.listAllOrdersByDate(date);
        
        if(ordersByDate == null || ordersByDate.isEmpty()) {
            throw new InvalidOrderDate("There are no orders placed on that date.");
        }
        
        
        return ordersByDate;
    }
    
     @Override
    public List<Order> listAllOrders() throws InvalidOrderDate{
        List orders = dao.getAllOrders();
    if (orders == null || orders.isEmpty()){
        throw new InvalidOrderDate("There are no orders");
    }
    
    return orders;
    }
    
    @Override
    public Order removeOrder(int orderNumber) throws OrderNotFoundException{
        Order removedOrder =  dao.removeOrder(orderNumber);
        if (removedOrder == null) {
            throw new OrderNotFoundException("Order doesn't exist.");
        }
        return removedOrder;
    }
    
    @Override
    public Order listOrder(int orderNumber) {
        return dao.listOrder(orderNumber);
    }

    @Override
    public List<StateTax> getStateTaxList() {
        return dao.getStateTaxList();
    }
    
    @Override
    public StateTax getState(String state) throws InvalidStateException {

        StateTax states = dao.getState(state);

        if (states == null) {
            throw new InvalidStateException("Invalid State");
        }

        return states;
    }
    
    @Override
    public Product getProduct(String material) throws InvalidProductException {
        Product product = dao.getProduct(material);

        if (product == null) {
            throw new InvalidProductException("Invalid Material");
        }
        return product;
    }
    
    public void getArea(BigDecimal area) throws InvalidAreaException{
   
    }

    @Override
    public List<Product> getProductList() {
        return dao.getProductList();
    }
   
    @Override
    public void loadAll() throws FlooringMasteryDaoException {
       dao.loadAll();
    }

    @Override
    public void writeAll() throws FlooringMasteryDaoException {
        dao.writeAll();
    }
   
     
}
