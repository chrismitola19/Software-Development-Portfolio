
package com.cm.classroster.dao;

/**
 *
 * @author chris
 */
public class ClassRosterPersistenceException extends Exception {  //we use the extends keyword to indicate that we are extending Exception.
    
    
    
    /*we have two constructors. One takes just a string message and the other takes a string 
message and a Throwable cause. Also notice that each of these constructors turns around and calls
the matching constructor on the Exception class by calling super. We do this because, as we 
mentioned earlier, we want ClassRosterDaoException to act just like Exception, so we let the base 
class constructors do all the hard work of initializing our object.*/


    public ClassRosterPersistenceException(String message){
        super(message);
    }
    public ClassRosterPersistenceException(String message, Throwable cause){
        super(message, cause);
    }
    
    /*We will use the first constructor in cases where something is wrong in our application but it
isn't caused by another exception. For example, maybe our application has some validation rules for student 
data input and one of the fields doesn't pass validation. In that case, we could throw a new ClassRosterDaoException 
with a message describing the problem. We'll look at these cases in the next section of the course.

We will use the second constructor in cases where something is wrong in our application that is caused by another 
exception in the underlying implementation. In these cases, we catch the implementation-specific
exception (for example FileNotFoundException). In the catch block, we would create a 
new ClassRosterDaoException and pass in a new message and the exception that caused
the original problem, and then we throw the newly-created ClassRosterDaoException. 
We have effectively wrapped the original exception with our application-specific exception.*/
}
