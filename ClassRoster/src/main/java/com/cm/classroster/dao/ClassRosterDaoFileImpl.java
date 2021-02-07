
package com.cm.classroster.dao;

import com.cm.classroster.dto.Student;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author chris
 */
/*we’ll create two constants in our DAO — one for the name of the file that holds all the student 
data and one for the delimiter (two colons).  Add the following constants near the top of 
your ClassRosterDaoFileImpl class:*/




    
    public class ClassRosterDaoFileImpl implements ClassRosterDao {
        
        public static final String ROSTER_FILE = "roster.txt";
        public static final String DELIMITER = "::";
        
        private void loadRoster() throws ClassRosterPersistenceException {
    Scanner scanner;

    try {
        // Create Scanner for reading the file
        scanner = new Scanner(
                new BufferedReader(
                        new FileReader(ROSTER_FILE)));
    } catch (FileNotFoundException e) {
        throw new ClassRosterPersistenceException(
                "-_- Could not load roster data into memory.", e);
    }
    // currentLine holds the most recent line read from the file
    String currentLine;
    // currentStudent holds the most recent student unmarshalled
    Student currentStudent;
    // Go through ROSTER_FILE line by line, decoding each line into a 
    // Student object by calling the unmarshallStudent method.
    // Process while we have more lines in the file
    while (scanner.hasNextLine()) {
        // get the next line in the file
        currentLine = scanner.nextLine();
        // unmarshall the line into a Student
        currentStudent = unmarshallStudent(currentLine);

        // We are going to use the student id as the map key for our student object.
        // Put currentStudent into the map using student id as the key
        students.put(currentStudent.getStudentId(), currentStudent);
    }
    // close scanner
    scanner.close();
}
        
        
        
        /*Now we are ready to implement the addStudent method. This method is straightforward; we simply
        put the supplied Student into our map using the supplied student id as the key and we're done.*/ 
    @Override
    public Student addStudent(String studentId, Student student)
       throws ClassRosterPersistenceException{ 
        loadRoster();
        Student newStudent = students.put(studentId, student);
        writeRoster();
        return newStudent;
        }
        
    
    
    /*This code gets all of the Student objects out of the students map as a collection by calling the 
    values() method. We pass that returned collection into the constructor for a new ArrayList. 
    One of the constructors for ArrayList take a collection as a parameter, which effectively allows us
    to convert the collection of Student objects into an ArrayList of Student objects that we can return 
    from the method. Note that our method specifies that we'll return a List<Student> but we create and return
    an ArrayList<Student>. This is perfectly fine because ArrayList implements the List interface so it can be
    treated as a List.*/
    
    @Override
    public List<Student> getAllStudents()
        throws ClassRosterPersistenceException{
        loadRoster();
        return new ArrayList<Student>(students.values());
// throw new UnsupportedOperationException("Not supported yet."); = use this when there is nothing supported yet, its a placeholder 
    }
    
    @Override
    public Student getStudent(String studentId) 
        throws ClassRosterPersistenceException {
        loadRoster();
        return students.get(studentId); //implement the getStudent method
    }  /*This method reads all the Student objects from the file, gets them out of the map, 
    and then returns an ArrayList of the Student objects to the caller.*/
    
    @Override
    /*we will implement the removeStudent() method. This method is simple — we just 
    ask the students map to remove the Student object mapped with the given ID.*/
    public Student removeStudent(String studentId) 
       throws ClassRosterPersistenceException {
        loadRoster();
       Student removedStudent = students.remove(studentId);
       writeRoster();
       return removedStudent;
       /*This method ensures all Student objects are read in from the file and loaded into our map. 
       Then the method removes the specified Student object from our map, writes the updated students 
       map to the file, and returns the removed Student object to the caller.*/
    }
    //Add private member
    private Map<String, Student> students = new HashMap<>();

    /*As we mentioned earlier, the initial version of ClassRosterDaoFileImpl will hold all data 
    in memory only — it won't read from or write to a file. */
    
    
    /*create a method that knows translate a line of text into a Student object. This method will follow 
the pattern described above, and the process also described in the Data Marshaling and Unmarshaling lesson:

-Take in a String line to break apart for student information.
-Split the String into chunks at the :: delimiter.
-Create a new Student object.
-Use the first value from the split String to set the Student id (this is passed into the constructor of the 
new Student object).
-Use the second value from the split String to set the Student first name.
-Use the third value from the split String to set the Student last name.
-Use the fourth value from the split String to set the cohort value.*/

private Student unmarshallStudent(String studentAsText){
    // studentAsText is expecting a line read in from our file.
    // For example, it might look like this:
    // 1234::Ada::Lovelace::Java-September1842
    //
    // We then split that line on our DELIMITER - which we are using as ::
    // Leaving us with an array of Strings, stored in studentTokens.
    // Which should look like this:
    // ______________________________________
    // |    |   |        |                  |
    // |1234|Ada|Lovelace|Java-September1842|
    // |    |   |        |                  |
    // --------------------------------------
    //  [0]  [1]    [2]         [3]
    String[] studentTokens = studentAsText.split(DELIMITER);

    // Given the pattern above, the student Id is in index 0 of the array.
    String studentId = studentTokens[0];

    // Which we can then use to create a new Student object to satisfy
    // the requirements of the Student constructor.
    Student studentFromFile = new Student(studentId);

    // However, there are 3 remaining tokens that need to be set into the
    // new student object. Do this manually by using the appropriate setters.

    // Index 1 - FirstName
    studentFromFile.setFirstName(studentTokens[1]);

    // Index 2 - LastName
    studentFromFile.setLastName(studentTokens[2]);

    // Index 3 - Cohort
    studentFromFile.setCohort(studentTokens[3]);

    // We have now created a student! Return it!
    return studentFromFile;
}

/*We use the String.split(…) method to split each line in the file into an array of Strings.  
When the split method splits a String on the given delimiter, it throws the delimiter away.  
Carefully read the comments in the above code for details.*/


/*create a method that reads the roster file into memory.  This method will follow the algorithm 
described in the Data Marshaling and Unmarshaling lesson:

-Open the file for reading.
-For each line in the file, do the following:
-Read the line into a String variable.
-Pass the line to our unmarshallStudent method to parse it into Student
-Put the newly created and initialized Student object into the student map.
-Close the file*/



/*Now we’ll create the method that organizes the student information from an in memory object into
a line of text, so that it can then be written properly into a file. This method will also follow the 
pattern described above, and also the process outlined in the Data Marshaling and Unmarshaling lesson.

However most importantly - we must preserve the order of information when we translate our 
Student into text because we are eventually expecting to unmarshall it back into a Student again.
The write method must be the equal & opposite balance to the read method:

-Take in a student.
-Create a String consisting of student id, first name, last names, and cohort (in that order), 
separated by the :: delimiter.*/

private String marshallStudent(Student aStudent){
    // We need to turn a Student object into a line of text for our file.
    // For example, we need an in memory object to end up like this:
    // 4321::Charles::Babbage::Java-September1842

    // It's not a complicated process. Just get out each property,
    // and concatenate with our DELIMITER as a kind of spacer. 

    // Start with the student id, since that's supposed to be first.
    String studentAsText = aStudent.getStudentId() + DELIMITER;

    // add the rest of the properties in the correct order:

    // FirstName
    studentAsText += aStudent.getFirstName() + DELIMITER;

    // LastName
    studentAsText += aStudent.getLastName() + DELIMITER;

    // Cohort - don't forget to skip the DELIMITER here.
    studentAsText += aStudent.getCohort();

    // We have now turned a student to text! Return it!
    return studentAsText;
}

/*Now we’ll create the method that writes the student information from memory to a file.  
We will follow the algorithm outlined in the Data Marshaling and Unmarshaling lesson:

-Open the file for writing.
-Go through the Student objects in the student map one by one.
-For each Student, do the following:
-Turn a Student to text, using our marshallStudent method, spaced by our delimiter
-Write the String to the output file.
-Close the file.*/

/**
 * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
 * for file format.
 * 
 * @throws ClassRosterPersistenceException if an error occurs writing to the file
 */
private void writeRoster() throws ClassRosterPersistenceException {
    // NOTE FOR APPRENTICES: We are not handling the IOException - but
    // we are translating it to an application specific exception and 
    // then simple throwing it (i.e. 'reporting' it) to the code that
    // called us.  It is the responsibility of the calling code to 
    // handle any errors that occur.
    PrintWriter out;

    try {
        out = new PrintWriter(new FileWriter(ROSTER_FILE));
    } catch (IOException e) {
        throw new ClassRosterPersistenceException(
                "Could not save student data.", e);
    }

    // Write out the Student objects to the roster file.
    // NOTE TO THE APPRENTICES: We could just grab the student map,
    // get the Collection of Students and iterate over them but we've
    // already created a method that gets a List of Students so
    // we'll reuse it.
    String studentAsText;
    List<Student> studentList = this.getAllStudents();
    for (Student currentStudent : studentList) {
        // turn a Student into a String
        studentAsText = marshallStudent(currentStudent);
        // write the Student object to the file
        out.println(studentAsText);
        // force PrintWriter to write line to the file
        out.flush();
    }
    // Clean up
    out.close();
}
    }



