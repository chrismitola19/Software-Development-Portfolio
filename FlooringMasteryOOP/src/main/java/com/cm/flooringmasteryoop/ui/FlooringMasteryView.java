
package com.cm.flooringmasteryoop.ui;

import com.cm.flooringmasteryoop.dto.Order;
import com.cm.flooringmasteryoop.dto.Product;
import com.cm.flooringmasteryoop.dto.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author chris
 */
public class FlooringMasteryView {

    private UserIO io;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("<<Flooring Program>>");
        io.print("1. Display All Orders");
        io.print("2. Display Orders by Date");
        io.print("3. Add an Order");
        io.print("4. Edit an Order");
        io.print("5. Remove an Order");
        io.print("6. Save Current Work");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
        
    }
    
    public int printMenuAndGetSelectionTraining() {
        io.print("<<Flooring Program>>");
        io.print("1. Display All Orders");
        io.print("2. Display Orders by Date");
        io.print("3. Add an Order");
        io.print("4. Edit an Order");
        io.print("5. Remove an Order");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    public String chooseEdit(){
        io.print("Which field would you like to edit?");
    return io.readString("\nCustomer Name \nState \nMaterial \nArea ");
    }
    

    public int getNewOrderNum() {
       
        int orderNumber = io.readInt("Please enter the Order Number: ");
        
        return orderNumber;
    }

    public String getNewOrderCustomerName() {
        String customersName = io.readString("Please enter the customers name: ");
        return customersName;
    }

    public String getNewOrderProductName(String prompt, List<Product> products) {
        for (Product product : products) {
            io.print(product.getProductType());
        }

        String materialName = io.readString("Please enter the Material you would like to use:  ");
        return materialName;
    }

    public String getNewOrderStateName(String prompt, List<StateTax> states) {
        for (StateTax state : states) {
            io.print(state.getStateName() + " " + state.getTaxRate());
        }
        String state = io.readString("Please enter your State's Name: ");

        return state;
    }
    

    public BigDecimal getNewOrderArea(){
        BigDecimal area = io.readBigDecimal("Please enter the Area size:  ");
        return area;
    }
  

    public LocalDate getOrderDate() {
       LocalDate ld = null; 
      try{  
        String orderDate = io.readString("Please enter the order date. (MM/dd/yyyy)");
         ld = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
      }catch (DateTimeParseException e){ 
          io.print("Not a valid date format");
      }
       
          return ld;
    }
    
    public void displayOrderBanner() {
        io.print("--- Order ---");
    }
    
    public void editOrderBanner() {
        io.print("--- Edit Order ---");
    } 
    
    public void removeOrderBanner() {
        io.print("--- Remove Order ---");
    }

    public void displayOrder(Order order) {
        if (order != null) {
  
        
        io.print("Order Number#: "+ order.getOrderNumber()+ " | Customer Name: " + order.getCustomerName() 
                + ", Order Date: " + order.getDate()
                + ", Material: " + order.getProduct().getProductType() + ", Area: " + order.getArea() 
                + ", State: " + order.getStateTax().getStateName()
                + ", Tax Rate: " + order.getStateTax().getTaxRate()
                + ", Cost Per Sq Ft: " + order.getProduct().getCostPerSqFt()
                + ", Labor Cost Per Sq Ft: " + order.getProduct().getLaborCostPerSqFt()
                + ", Material Cost: " + order.getMaterialCost()
                + ", Labor Cost: " + order.getLaborCost()
                + ", Tax: " + order.getTax()
                + ", Total: " + order.getTotal());
        } else {
            io.print("");
        }
 
    }
    
    public void displayAllOrders(List<Order> list){
        
        if(list != null){
        
        for(Order order: list){
        io.print("Order Number#: "+ order.getOrderNumber()+ " | Customer Name: " + order.getCustomerName() 
                + ", Order Date: " + order.getDate()
                + ", Material: " + order.getProduct().getProductType() + ", Area: " + order.getArea() 
                + ", State: " + order.getStateTax().getStateName()
                + ", Tax Rate: " + order.getStateTax().getTaxRate()
                + ", Cost Per Sq Ft: " + order.getProduct().getCostPerSqFt()
                + ", Labor Cost Per Sq Ft: " + order.getProduct().getLaborCostPerSqFt()
                + ", Material Cost: " + order.getMaterialCost()
                + ", Labor Cost: " + order.getLaborCost()
                + ", Tax: " + order.getTax()
                + ", Total: " + order.getTotal());
        }
       } else {
            io.print("No Orders were placed.");
        }
            io.readString("Press enter to continue.");
    }

    public void displayOrderPurchaseBanner() {
        io.print("--- Purchase Order ---");
    }

    public String getOrderNumberChoice() {
        return io.readString("Please enter the Order Number.");
    }

    public String displayContinueBanner() {
        return io.readString("Press enter to continue.");
    }

    public void displayAllBanner(){
        io.print("--- All Orders ---");
    }
    
    public void displayAllDateBanner() {
        io.print("--- All Orders By Date ---");
    }

    public void listAllOrders(LocalDate date) {
        io.print("Listing of  all orders placed on " + date + ":");
    }

    public void listAllProducts(List<Product> list) {
        for(Product product : list){
            io.print("List of all products available: " + product.getProductType());
        }
    }

    public void displayExitBanner() {
        io.print("Goodbye.");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print(errorMsg);
        io.readString("\nPress enter to continue");
    }

    public String addOrderConfirmation(){
        return io.readString("\nAre you sure you want to add this order? \ny/n");
    }
    
    public String removeOrderConfirmation() {
        return io.readString("\nAre you sure you want to remove this Order?\ny/n");
    }

    public String editOrderConfirmation() {
        return io.readString("\nAre you sure you want to edit this Order?\ny/n");
    }
    
    public void cancelAddOrder(){
        io.print("You have choosen not to add your order");
    }
    
    public void cancelEditOrder(){
      io.print("You have choosen not to edit your order");
    }
    
    public void cancelRemoveOrder(){
     io.print("You have choosen not to remove your order");
    }
    
    public void displayEditSuccessBanner(){
        io.print("You have sucessfully edited your order");
    }
    
     public void displayRemoveSuccessBanner(){
        io.print("You have sucessfully removed your order");
    }
     
     public void displayAddOrderSuccessBanner(){
        io.print("You have sucessfully added your order");
    }
     
     public void displayOrderSuccessfullySavedBanner(){
         io.print("You have successfully saved your order(s)");
         io.readString("Press enter to continue");
     }
     
     public void displayTrainingBanner(){
         io.print("***Training Mode***");
     }

     public void displayProductionBanner(){
         io.print("***Production Mode***");
     }
     
     
}

