
package com.cm.classroster.dto;

import java.util.Objects;

/**
 *
 * @author chris
 */
public class Student {
    
    private String firstName;
    private String lastName;
    private String studentId;
    private String cohort;    //Java or .Net + cohort month/year
    
    
    public Student(String studentId){
        this.studentId = studentId;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getStudentId(){
        return studentId;
    }
    
    public String getCohort(){
        return cohort;
    }
    
    public void setCohort(String cohort){
        this.cohort = cohort;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.firstName);
        hash = 53 * hash + Objects.hashCode(this.lastName);
        hash = 53 * hash + Objects.hashCode(this.studentId);
        hash = 53 * hash + Objects.hashCode(this.cohort);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { //object passed in is my object
            return true;
        }
        if (obj == null) { //object passed in null
            return false;
        }
        if (getClass() != obj.getClass()) { //the class for my object is not equal to the object passed in
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {  //name doesnt equal name 
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.studentId, other.studentId)) {
            return false;
        }
        if (!Objects.equals(this.cohort, other.cohort)) {
            return false;
        }
        return true;
    }
    
    

   
}

/*Notice that studentId is a read-only field. It is passed in as a parameter to the 
constructor, and there is no setter for this field. All other fields on the Student class 
are read/write and must be set manually after a Student object has been instantiated.*/
