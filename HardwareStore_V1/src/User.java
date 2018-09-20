//package HardwareStore2;
import java.io.Serializable;
import java.util.Scanner;


public abstract class User implements Serializable{
    //private members
    private int uID;
    private String firstName;
    private String lastName;

    /**
     * Constructor for User class
     * @param id for user ID number
     * @param fName for user first name
     * @param lName for user last name
     */
    public User (int id, String fName, String lName) {
        this.uID = id;
        this.firstName = fName;
        this.lastName = lName;

    }
    /**
     * Validation function for name
     * @param name for user name
     * @return valid name
     */
    public String validateName(String name) {
        Scanner in = new Scanner(System.in);

        while(!(name.matches("[a-z A-z]+"))) {
            System.out.println("Invalid name. \n Enter name: ");
            name = in.next();
        }
        return name;
    }
    /**
     * Getter function for user ID number
     * @return user ID number
     */
    public int getUID() {
        return uID;
    }
    /**
     * Getter function for first name of user
     * @return user first name
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Getter function for last name of user
     * @return user last name
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Setter function for user first name
     * @param firstName for user first name
     */
    public void setFirstName(String firstName) {this.first = firstName;	}
    /**
     * Setter function for user last name
     * @param lastName for user last name
     */
    public void setLastName(String lastName){this.last = lastName;}
    /**
     * Override toString to print the user.
     * @return user in a string
     */
    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " " + getUID() + "\n";
    }
}
