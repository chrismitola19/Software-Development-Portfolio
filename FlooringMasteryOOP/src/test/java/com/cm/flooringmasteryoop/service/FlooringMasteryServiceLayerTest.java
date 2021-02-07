package com.cm.flooringmasteryoop.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 * @author chris
 */
public class FlooringMasteryServiceLayerTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    FlooringMasteryServiceLayer service = ctx.getBean("serviceLayer", FlooringMasteryServiceLayerImpl.class);

    File file = new File("testOrder.txt"); //

    public FlooringMasteryServiceLayerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("testOrder.txt"), "utf-8"))) {
        }

        service.loadAll();
    }

    @After
    public void tearDown() {
        file.delete();

        if (file.exists()) {
            System.out.println("Failed to delete file");
        } else {

            System.out.println("File deleted successfully");
        }
    }

    /**
     * Test of addOrder method, of class FlooringProgramServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddOrder() throws Exception {
    }

    /**
     * Test of listOrdersByDate method, of class FlooringProgramServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testInvalidProductException() throws Exception {
        String name = "Jeff";
        String stateName = "PA";
        String productName = "stone";
        BigDecimal area = new BigDecimal(100);
        LocalDate ld = LocalDate.now();

        try {
            service.addOrder(name, stateName, productName, area, ld);
            fail("Expected exception");
        } catch (InvalidProductException e) {

        }
    }

    @Test
    public void testInvalidStateException() throws Exception {
        String name = "Sam";
        String stateName = "CT";
        String productName = "carpet";
        BigDecimal area = new BigDecimal(100);
        LocalDate ld = LocalDate.now();

        try {
            service.addOrder(name, stateName, productName, area, ld);
            fail("Expected exception");
        } catch (InvalidStateException e) {

        }
    }

    @Test
    public void testInvalidCustomerException() throws Exception {
        String name = "";
        String stateName = "MI";
        String productName = "wood";
        BigDecimal area = new BigDecimal(100);
        LocalDate ld = LocalDate.now();

        try {
            service.addOrder(name, stateName, productName, area, ld);
            fail("Expected exception");
        } catch (InvalidCustomerException e) {

        }
    }

    @Test
    public void testInvalidAreaException() throws Exception {
        String name = "Stew";
        String stateName = "PA";
        String productName = "tile";
        BigDecimal area = new BigDecimal(0);
        LocalDate ld = LocalDate.now();

        try {
            service.addOrder(name, stateName, productName, area, ld);
            fail("Expected exception");
        } catch (InvalidAreaException e) {

        }

    }

    @Test
    public void testListOrdersByDateInvalidOrderDate() throws Exception {
        try {
            service.listOrdersByDate(LocalDate.parse("2020-01-01"));
            fail("Expected exception");
        } catch (InvalidOrderDate e) {

        }
    }

    /**
     * Test of removeOrder method, of class FlooringProgramServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveOrderOrderNotFoundException() throws Exception {
        try {
            service.removeOrder(2);
            fail("Expected exception");
        } catch (OrderNotFoundException e) {
        }
    }

    /**
     * Test of getState method, of class FlooringProgramServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetState() throws Exception {
        try {
            service.getState("NJ");
            fail("Expected exception");
        } catch (InvalidStateException e) {
        }
    }

    /**
     * Test of getProduct method, of class FlooringProgramServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetProduct() throws Exception {
        try {
            service.getProduct("metal");  //there is no metal so this should fail 
            fail("Expected exception");
        } catch (InvalidProductException e) {
        }
    }

}
