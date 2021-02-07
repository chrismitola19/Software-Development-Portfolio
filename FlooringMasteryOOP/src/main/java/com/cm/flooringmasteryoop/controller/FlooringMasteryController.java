package com.cm.flooringmasteryoop.controller;

import com.cm.flooringmasteryoop.dao.FlooringMasteryDaoException;
import com.cm.flooringmasteryoop.service.InvalidAreaException;
import com.cm.flooringmasteryoop.service.InvalidCustomerException;
import com.cm.flooringmasteryoop.service.InvalidOrderDate;
import com.cm.flooringmasteryoop.service.InvalidProductException;
import com.cm.flooringmasteryoop.service.InvalidStateException;
import com.cm.flooringmasteryoop.dto.Order;
import com.cm.flooringmasteryoop.dto.Product;
import com.cm.flooringmasteryoop.dto.StateTax;
import com.cm.flooringmasteryoop.service.FlooringMasteryServiceLayer;
import com.cm.flooringmasteryoop.service.OrderNotFoundException;
import com.cm.flooringmasteryoop.ui.FlooringMasteryView;
import com.cm.flooringmasteryoop.ui.UserIO;
import com.cm.flooringmasteryoop.ui.UserIOConsoleImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author chris
 */
public class FlooringMasteryController {

    FlooringMasteryView view;
    private final FlooringMasteryServiceLayer service;

    private final UserIO io = new UserIOConsoleImpl();

    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        try {

            service.loadAll();

            boolean keepGoing = true;
            int menuSelection = 0;

            while (keepGoing) {
                try {
                    io.print("Mode Option");
                    io.print("1. Training");
                    io.print("2. Production");

                    menuSelection = io.readInt("Select the operation you wish to perform.", 1, 2);

                    //add switch
                    switch (menuSelection) {
                        case 1:
                            displayTrainingList();
                            break;
                        case 2:
                            displayProductionList();
                            break;
                        default:
                            io.print("Unknown Command");
                        //anything put in other than 1-2  

                    }//end switch
                    break;
                } catch (NumberFormatException e) {
                    io.print("\nNot a valid choice\n");

                }
            }//end while

            if (menuSelection == 2) {
                while (keepGoing) {
                    try {
                        menuSelection = getMenuSelection();

                        switch (menuSelection) {
                            case 1:
                                this.listAllOrders();
                                break;
                            case 2:
                                this.listOrdersByDate();
                                break;
                            case 3:
                                this.addOrder();
                                break;
                            case 4:
                                this.editOrder();
                                break;
                            case 5:
                                this.removeOrder();
                                break;
                            case 6:
                                this.saveWork();
                                break;
                            case 7:
                                keepGoing = false;
                                break;
                            default:
                                io.print("Unknown Command");
                        }
                    } catch (NumberFormatException | InvalidOrderDate e) {
                        io.print("\nNot a valid choice\n");

                    }
                }
            } else if (menuSelection == 1) {
                while (keepGoing) {
                    try {
                        menuSelection = getMenuSelectionTraining();

                        switch (menuSelection) {
                            case 1:
                                this.listAllOrders();
                                break;
                            case 2:
                                this.listOrdersByDate();
                                break;
                            case 3:
                                this.addOrder();
                                break;
                            case 4:
                                this.editOrder();
                                break;
                            case 5:
                                this.removeOrder();
                                break;

                            case 6:
                                keepGoing = false;
                                break;
                            default:
                                io.print("Unknown Command");
                        }
                    } catch (NumberFormatException | InvalidOrderDate e) {
                        io.print("\nNot a valid choice\n");
                    }

                }

            }

            io.print("Goodbye.");
        } catch (FlooringMasteryDaoException e) {
            view.displayErrorMessage(e.getMessage());

        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private int getMenuSelectionTraining() {
        return view.printMenuAndGetSelectionTraining();
    }

    public void listAllOrders() throws InvalidOrderDate {
        view.displayAllBanner();
        List<Order> orders = service.listAllOrders();
        view.displayAllOrders(orders);
    }

    public void listOrdersByDate()
            throws InvalidOrderDate {

        try {
            view.displayAllDateBanner();
            LocalDate date = view.getOrderDate();
            List<Order> orders = service.listOrdersByDate(date);
            view.displayAllOrders(orders);

        } catch (InvalidOrderDate e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    public void addOrder() {
        view.displayOrderBanner();
        //get states from service layer
        //service get products from service Layer
        List<StateTax> states = service.getStateTaxList();
        List<Product> products = service.getProductList();

        String name = view.getNewOrderCustomerName();
        String state = view.getNewOrderStateName("Enter State", states);
        String product = view.getNewOrderProductName("Choose a product", products);
        BigDecimal area = view.getNewOrderArea();
        LocalDate ld = LocalDate.now(); //will use the date when info is added 
        String answer = view.addOrderConfirmation().toLowerCase();
        boolean isValid = false;

        while (isValid == false) {
            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
                try {
                    Order order = service.addOrder(name, state, product, area, ld);
                    view.displayOrder(order);
                    view.displayAddOrderSuccessBanner();
                    view.displayContinueBanner();
                    isValid = true;
                } catch (InvalidCustomerException e) {
                    view.displayErrorMessage(e.getMessage());
                    name = view.getNewOrderCustomerName();
                } catch (InvalidStateException e) {
                    view.displayErrorMessage(e.getMessage());
                    state = view.getNewOrderStateName("Enter State", states);
                } catch (InvalidProductException e) {
                    view.displayErrorMessage(e.getMessage());
                    product = view.getNewOrderProductName("Choose a product", products);
                } catch (InvalidAreaException e) {
                    view.displayErrorMessage(e.getMessage());
                    area = view.getNewOrderArea();
                }

            } else {
                view.cancelAddOrder();
                io.print("");
                break; //breaks out of loop
            }
        }
    }

    public void editOrder() throws InvalidOrderDate {

        List<StateTax> states = service.getStateTaxList();
        List<Product> products = service.getProductList();

        //  try {
        //  listOrdersByDate();
        view.editOrderBanner();
        List<Order> orders = service.listAllOrders();
        view.displayAllOrders(orders);
        int orderNumber = view.getNewOrderNum();
        Order order = service.listOrder(orderNumber);
        view.displayOrder(order);

        String answer = view.editOrderConfirmation(); // confirm if they want to edit

        if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {

            boolean case1 = true;
            boolean case2 = true;
            boolean case3 = true;
            boolean case4 = true;

            boolean isValid = true;

            do {

                String choice = view.chooseEdit().toLowerCase();

                switch (choice) {
                    case "customer name":
                        while (case1 == true) {
                            String name = view.getNewOrderCustomerName();
                            if (name != null && name.trim().length() != 0) {
                                order.setCustomerName(name);

                                view.displayEditSuccessBanner();
                                view.displayOrder(order);
                                view.displayContinueBanner();
                                case1 = false;
                                isValid = false;
                            } else {
                                io.print("Not a valid customer name\n");
                                isValid = true;
                            }
                        }
                        break;
                    case "state":
                        while (case2 == true) {
                            try {
                                String state = view.getNewOrderStateName("Enter State", states).toUpperCase();
                                if (state != null && state.trim().length() != 0) {
                                    StateTax stateObj = service.getState(state);
                                    order.setStateTax(stateObj);
                                    view.displayEditSuccessBanner();
                                    view.displayOrder(order);
                                    view.displayContinueBanner();
                                    case2 = false;
                                    isValid = false;
                                } else {
                                    io.print("Not a valid state\n");
                                    isValid = true;
                                }
                            } catch (InvalidStateException e) {
                                view.displayErrorMessage(e.getMessage());
                                isValid = true;
                            }
                        }
                        break;
                    case "area":
                        while (case3 == true) {

                            BigDecimal area = view.getNewOrderArea();
                            if (isNumeric(area) && area.compareTo(new BigDecimal("0")) > 0) {
                                order.setArea(area);
                                view.displayEditSuccessBanner();
                                view.displayOrder(order);
                                view.displayContinueBanner();
                                case3 = false;
                                isValid = false;
                            } else {
                                io.print("Need a valid area");

                            }
                        }

                        break;
                    case "material":
                        while (case4 == true) {
                            try {
                                io.print("");
                                String material = view.getNewOrderProductName("Choose a product", products).toLowerCase();
                                if (material != null && material.trim().length() != 0) {
                                    Product productObj = service.getProduct(material);
                                    order.setProduct(productObj);
                                    view.displayEditSuccessBanner();
                                    view.displayOrder(order);
                                    view.displayContinueBanner();
                                    case4 = false;
                                    isValid = false;
                                } else {
                                    io.print("Not a valid material\n");
                                    isValid = true;
                                }
                            } catch (InvalidProductException e) {
                                view.displayErrorMessage(e.getMessage());
                                isValid = true;
                            }
                        }
                        break;
                }
            } while (isValid);
        } else {
            view.cancelEditOrder();

        }

    }

    public void removeOrder() throws InvalidOrderDate {

        boolean case1 = true;

        view.removeOrderBanner();
        List<Order> orders = service.listAllOrders();

        while (case1 == true) {

            view.displayAllOrders(orders);
            int orderNumber = view.getNewOrderNum();
            if (orderNumber != 0) {
                Order order = service.listOrder(orderNumber);
                view.displayOrder(order);
            }

            String answer = view.removeOrderConfirmation().toLowerCase();

            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {

                try {
                    service.removeOrder(orderNumber);
                    view.displayRemoveSuccessBanner();
                    view.displayContinueBanner();
                    case1 = false;
                } catch (OrderNotFoundException e) {
                    view.displayErrorMessage(e.getMessage());

                }

            } else {
                view.cancelRemoveOrder();
                case1 = false;
            }
        }
    }

    public void saveWork() throws FlooringMasteryDaoException {
        service.writeAll();
        view.displayOrderSuccessfullySavedBanner();
    }

    private void displayTrainingList() {  //used for training option
        view.displayTrainingBanner();
    }

    private void displayProductionList() { //used for production option
        view.displayProductionBanner();
    }

    private boolean isNumeric(BigDecimal area) {
        if (area == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(area.toString());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
