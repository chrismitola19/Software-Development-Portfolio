package com.cm.flooringmasteryoop.service;

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
public interface FlooringMasteryServiceLayer {

    Order addOrder(String name, String state, String product, BigDecimal area, LocalDate date)
            throws InvalidProductException, InvalidStateException,
            InvalidCustomerException, InvalidAreaException;

    List<StateTax> getStateTaxList();

    List<Product> getProductList();

    Order listOrder(int orderNumber);

    List<Order> listAllOrders() throws InvalidOrderDate;

    List<Order> listOrdersByDate(LocalDate date) throws InvalidOrderDate;

    Order removeOrder(int orderNumber) throws OrderNotFoundException;

    StateTax getState(String state) throws InvalidStateException;

    Product getProduct(String material) throws InvalidProductException;

    void writeAll() throws FlooringMasteryDaoException;

    void loadAll() throws FlooringMasteryDaoException;
}
