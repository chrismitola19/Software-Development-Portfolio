
package com.cm.flooringmasteryoop.dao;

import com.cm.flooringmasteryoop.dto.Order;
import com.cm.flooringmasteryoop.dto.Product;
import com.cm.flooringmasteryoop.dto.StateTax;
import com.cm.flooringmasteryoop.service.InvalidProductException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author chris
 */
public interface FlooringMasteryDao {
   Order addOrder(Order order);
   
   Order editOrder(int orderNumber, Order order);
   
   Order listOrder(int orderNumber);
   
   Order removeOrder (int orderNumber);
   
   List<Order> getAllOrders();
   
   List<Order> listAllOrdersByDate(LocalDate date);
   
   List<Product> getProductList();
  
   List<StateTax> getStateTaxList();
   
   StateTax getState(String stateName);
   
   Product getProduct(String material);
   
   int getNextOrderNumber();
   
  
   void writeOrder() throws FlooringMasteryDaoException;
  
   void writeStateTax() throws FlooringMasteryDaoException;
  
   void writeProduct() throws FlooringMasteryDaoException;
   
   void loadAll() throws FlooringMasteryDaoException;
  
   void writeAll() throws FlooringMasteryDaoException;
}