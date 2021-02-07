/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cm.classroster.service;

import com.cm.classroster.dto.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author chris
 */
public class ClassRosterServiceLayerTest {
    
    private ClassRosterServiceLayer service;
    
    public ClassRosterServiceLayerTest() {
        //wire the ServiceLayer with stub implementationsof the Dao and Audit Dao
//        ClassRosterDao dao = new ClassRosterDaoStubImpl();
//        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
//        
//        service = new ClassRosterServiceLayerImpl(dao, auditDao); 

          //instantiate Application Context  (for Spring)
          ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
          service = ctx.getBean("serviceLayer", ClassRosterServiceLayer.class);

    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testCreateStudent() throws Exception {
        
        Student student = new Student("0002"); //can be anything other than 0001
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");
        service.createStudent(student);
            
    }
    
    //with dulicate ID
    @Test
    public void testCreateStudentDuplicateId() throws Exception{
        
        Student student = new Student("0001"); //should be same as an existing ID (duplicate)
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");
        
        try{
            service.createStudent(student);
            fail("Expected ClassRosterDuplicateIdException was not thrown.");
            
        } catch (ClassRosterDuplicateIdException e){
            return;
            
        }
            
    }
    //for invalid data 
    @Test
    public void testCreateStudentInvalidData() throws Exception{
        
        Student student = new Student("0002"); //can be anything other than 0001
        student.setFirstName(""); //no name which is invalid
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");
        
        try{
            service.createStudent(student);
            fail("Expected ClassRosterDataValidationException was not thrown.");
            
        } catch (ClassRosterDataValidationException e){
            return;
        }
    }

    /**
     * Test of getAllStudents method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testGetAllStudents() throws Exception {
        
        assertEquals(1, service.getAllStudents().size());
    }

    /**
     * Test of getStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testGetStudent() throws Exception {
        
        Student student = service.getStudent("0001");
        assertNotNull(student); //assert student that exists is not null
        student = service.getStudent("9999");
        assertNull(student); //student that doesnt exist is null
    }

    /**
     * Test of removeStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testRemoveStudent() throws Exception {
        
        Student student = service.removeStudent("0001");
        assertNotNull(student); 
        student = service.removeStudent("9999");
        assertNull(student); 
    }

    
    
    /*First three tests test the business rules, last three test our implementation of our service layer 
is faithfully passing through these pass through methods and its actually reacting the way we expect it to */
    
    
}
