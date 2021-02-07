
package com.cm.classroster.service;

import com.cm.classroster.dao.ClassRosterPersistenceException;
import com.cm.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author chris
 */
public interface ClassRosterServiceLayer {
    
    void createStudent(Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException;
    
    List<Student> getAllStudents() throws
            ClassRosterPersistenceException;
    
    Student getStudent(String studentId) throws
            ClassRosterPersistenceException;
    
    Student removeStudent(String studentId) throws 
            ClassRosterPersistenceException;
    
    
    
}
