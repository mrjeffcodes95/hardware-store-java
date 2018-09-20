import java.io.Serializable;
import java.util.ArrayList;

public class Transactions implements Serializable{

    private String Filename = "sales.ser";
    private static ArrayList<SaleTransaction> printTransactions;

    public static void endTransaction () {

        printTransactions.add(new SaleTransaction(custID, eID, tn, shipD, deliverD, costShip));
        System.out.println("Here is the complete transaction log: ");
        for(Transaction transactionsCompleted: CTransaction)
            System.out.println(transactionsCompleted);
    }
}
