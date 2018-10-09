package pkg.Users;

import java.util.Scanner;
public class Employee extends User {
    private int ssn;
    private float salary;
    /**
     * Constructor for the employee, which is part of the abstract user class
     * @param id for employee ID number
     * @param fName for employee first name
     * @param lName for employee last name
     * @param ssn for employee phone number
     * @param salary for employee address
     */
    public Employee (int id, String fName, String lName, int ssn, float salary) {
        super (id, fName, lName);
        this.ssn = ssn;
        this.salary = salary;

    }

    /**
     * Validate employee social security number
     * @param social
     * @return valid social security number
     */
    public int validateSSN(int social) {
        while(social < 0)
        {
            System.out.println("Invalid social security number. Enter social: ");
            Scanner in = new Scanner(System.in);
            social = in.nextInt();
        }

        return social;
    }

    /**
     * Validate employee salary
     * @param salary the employee's salary
     * @return salary the employee's salary
     */
    public float validateSal(float salary)
    {
        //While salary is not valid, ask for valid salary
        while(!(salary >= 0))
        {
            System.out.println("Invalid salary. Enter salary: ");
            Scanner in = new Scanner(System.in);
            salary = in.nextFloat();
        }
        //Return valid salary
        return salary;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
