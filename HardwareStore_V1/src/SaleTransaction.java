import java.util.Date;
import java.io.Serializable;

public class SaleTransaction implements Serializable{

    //Private member fields
    private final String itemId;
    private final Date saleDate;
    private final int custID;
    private final int empID;
    private final float quantity;

    /**
     * Transaction constructor initializes the user attributes.
     * @param itemId the customer ID
     * @param eID the employee ID
     * @param tn the tracking number
     * @param shipD the shipping date
     * @param deliverD the deliver date
     * @param costShip the shipping cost
     */
    public SaleTransaction(String itemId, Date saleDate, int custID, int empID, float quantity) {
        this.itemId = itemId;
        this.saleDate = saleDate;
        this.custID = custID;
        this.empID = empID;
        this.quantity = quantity;
    }

    /**
     * Receives the tracking number.
     * @return the tracking number
     */
    public String getItemId() {return itemId;}

    /**
     * Receives the shipping date.
     * @return the shipping date
     */
    public Date getSaleDate() {return saleDate;}

    /**
     * Receives the customer ID.
     * @return the customer's ID
     */
    public int getCustID() {return this.custID;}

    /**
     * Receives the employee ID.
     * @return the employee's ID
     */
    public int getEmpID() {return empID;}

    /**
     * Receives the shipping cost.
     * @return the shipping cost
     */
    public float getQuantity() {return quantity;}






    }
