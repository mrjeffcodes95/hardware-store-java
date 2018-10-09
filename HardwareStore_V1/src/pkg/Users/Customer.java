package pkg.Users;

import java.util.Scanner;
public class Customer extends User {

    private String phoneNum;
    private String address;

    /**
     * Constructor for the customer, which is part of the abstract user class
     * @param id for customer ID number
     * @param fName for customer first name
     * @param lName for customer last name
     * @param phone for customer phone number
     * @param address for customer address
     */
    public Customer (int id, String fName, String lName, String phone, String address) {
        super(id, fName, lName);
        this.phoneNum = phone;
        this.address = address;
    }

    /**
     * Validation method for phone number
     * @param phone for phone number
     * @return valid phone number
     */
    public String validatePhone(String phone) {
        Scanner in = new Scanner(System.in);

        while ((phone.length() != 8) || !phone.matches("[0-9]+")) {
            System.out.println ("Invalid phone #. \n Enter phone #");
            phone = in.next();
        }
        return phone;
    }
    /**
     * Validation employee address
     * @param address for address
     * @return valid address
     */
    public String validateAddress(String address) {
        Scanner in = new Scanner(System.in);

        while (!(address.matches("[a-zA-Z0-9 ]+"))) {
            System.out.println("Invalid address. \n Enter address: ");
            address = in.next();
        }
        return address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getAddress() {
        return address;
    }
    /**
     * Setter function for phone number
     * @param phoneNum for customer phone number
     */
    public void setPhone(String phoneNum) {this.phoneNum = phoneNum;}

    /**
     * Setter function for customer address
     * @param address for customer address
     */
    public void setAddress(String address) {this.address = address;}
}
