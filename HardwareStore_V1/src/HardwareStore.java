import sun.font.AttributeValues;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.io.*;


public class HardwareStore {

    //use arrayList of type item to store any subclass of type item.
    private ArrayList<Item> itemList = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();
    private ArrayList<SaleTransaction> transactionInfo = new ArrayList<>();

    private static final String DATA_FILE_NAME = "database.ser";

    /**
     * This constructor creates an empty ArrayList and then calls the 
     * <CODE>readDatabase()</CODE> method to populate items previously stored. 
     *
     * @throws IOException
     */
    public HardwareStore() throws IOException {


        itemList = new ArrayList<>();
        userList = new ArrayList<>();
        transactionInfo = new ArrayList<>();
        readDatabase();
    }



    /**
     * Method getAllItemsFormatted returns the current list of items in the Arraylist in
     * no particular order.
     * 
     * @return a formatted String representation of all the items in itemList.
     */
    public String getAllFormatted(String s) {
        if (s == "item") {
            return getFormattedItemList(itemList);
        }else if (s == "user"){
            return getFormattedUserList(userList);
        }else {
            return getFormattedSales(transactionInfo);
        }
    }

    /**
     * Private method getFormattedPackageList used as an auxiliary method to return a given ArrayList
     * of items in a formatted manner.
     *
     * @param items the item list to be displayed.
     * @return a formatted String representation of all the items in the list give as a parameter.
     */
    private String getFormattedItemList(ArrayList<Item> items) {

        String itemText = " ------------------------------------------------------------------------------------\n" +
                String.format("| %-10s| %-25s| %-20s| %-10s| %-10s| %-10s|%n", "ID Number", "Name", "Quantity", "Price", "other", "other") +
                " ------------------------------------------------------------------------------------\n";
        //For every package, add to string
        for (Item i : items) {
            itemText += String.format("|%11s|%10s|%16s|%13s|", i.getId(), i.getName(), i.getQuantity(), i.getPrice());

            //Add proper package information
            if (i instanceof Hardware)
                itemText += String.format("Category: %3s|\n", ((Hardware) i).getCategory());

            if (i instanceof Appliance)
                itemText += String.format("Type: %3s|Brand: %3s |\n", ((Appliance) i).getType(), ((Appliance) i).getBrand());

        }
        itemText += " ---------------------------------------------------------------------------------------\n";
        return itemText;

    }

    /**
     * @param user
     * @return
     */
    private String getFormattedUserList(ArrayList<User> user) {

        String userText = " ------------------------------------------------------------------------------------\n" +
                String.format("| %-10s| %-25s| %-20s| %-10s| %-10s| %-10s|%n", "User Number", "First Name", "Last Name", "other", "other") +
                " ------------------------------------------------------------------------------------\n";
        //For every package, add to string
        for (User u : user) {
            userText += String.format("|%11s|%10s|%16s|%13s|", u.getUID(), u.getFirstName(), u.getLastName());

            //Add proper package information
            if (u instanceof Employee)
                userText += String.format("Social Security Number: %3s|Monthly salary: %3s|\n", ((Employee) u).getSsn(), ((Employee) u).getSalary();

            if (u instanceof Customer)
                userText += String.format("Phone Number: %3s|Address: %3s |\n", ((Customer) u).getPhoneNum(), ((Customer) u).getAddress());

        }
        userText += " ---------------------------------------------------------------------------------------\n";
        return userText;

    }

    /**
     * @param salesList
     * @return
     */
    private String getFormattedSales(ArrayList<SaleTransaction> salesList) {
        String saleText = " ------------------------------------------------------------------------------------\n" +
                String.format("| %-10s| %-25s| %-20s| %-10s| %-10s| %-10s|%n", "Item ID", "Customer Name", "Employee Name", "Date", "Quantity") +
                " ------------------------------------------------------------------------------------\n";
        for (SaleTransaction s : salesList) {
            saleText += String.format("|%11s|%10s|%16s|%13s|", s.getItemId(), s.getCustID(), s.getEmpID(), s.getSaleDate(), s.getQuantity());
        }
        saleText += " ---------------------------------------------------------------------------------------\n";
        return saleText;

    }


    public void addNewItem(int itemType, String id) {
        //If passed all the checks, add the item to the list
        Scanner CONSOLE_INPUT = new Scanner();
        String name = CONSOLE_INPUT.nextLine();
        String category = null;
        String brandType = null;

        if (itemType == 1) {

            System.out.println("\nenter a name for the Hardware: ");
            name = CONSOLE_INPUT.nextLine();

            // Entery category
            //String category = null;
            System.out.println("\nPlease select the category of Hardware.");
            System.out.println("1: Door&Window\n2: Cabinet&Furniture\n3: Fasteners\n4: Structural\n5: Other");
            int selection = CONSOLE_INPUT.nextInt();
            switch (selection) {
                case 1:
                    category = "Door&Window";
                    break;
                case 2:
                    category = "Cabinet&Furniture";
                    break;
                case 3:
                    category = "Fasteners";
                    break;
                case 4:
                    category = "Structural";
                    break;
                case 5:
                    category = "Other";
                    break;
                default:
                    System.out.println("Invalid category number.");
                    return;
            }

        }else {

            System.out.println("\nenter a name for the Appliance: ");
            name = CONSOLE_INPUT.nextLine();

            //String brandType = null;

            System.out.println("\nPlease enter the brand.");
            brandType = CONSOLE_INPUT.nextLine();
            brandType += " ";
            System.out.println("1: Refrigerators\n2: Washers&Dryers\n3: Ranges&Ovens\n4: Small Appliances");
            int selection = CONSOLE_INPUT.nextInt();
            switch (selection) {
                case 1:
                    brandType += "Refrigerators";
                    break;
                case 2:
                    brandType += "Washers&Dryers";
                    break;
                case 3:
                    brandType += "Ranges&Ovens";
                    break;
                case 4:
                    brandType += "Small Appliances";
                    break;
                default:
                    System.out.println("Invalid category number.");
                    return;
            }
        }

        // Entery quantity
        System.out.println("\nPlease type the quantity of the item.");
        int quantity = CONSOLE_INPUT.nextInt();
        if (quantity < 0) {
            System.out.println("Invalid price. "
                    + "The quantity cannot be smaller than 0.");
            return;
        }

        // Enter price
        System.out.println("\nPlease type the price of the item.");
        float price = CONSOLE_INPUT.nextFloat();
        if (price < 0) {
            System.out.println("Invalid price. "
                    + "The price cannot be smaller than 0.");
            return;
        }
        //figure out how to add idNumber when it has not been passed in
        if (itemType == 1){
            itemList.add(new Hardware(id, name, category, quantity, price));
        }
        if (itemType == 2) {
            itemList.add(new Appliance(id, name, quantity, price, brandType));
        }




        System.out.println("Item has been added.\n");
    }

    public void removeItem(String removeId) {
        itemList.remove(removeId);
        System.out.println("Item has been removed.");
    }

    public void showAllUser() {

    }
    
    
    /**
     * Add a certain quantity of the given item index.
     * Preconditions: 1. Item exists.
     * @param itemIndex the index of the item in the itemList
     * @param quantity  the quantity to remove
     */
    public void addQuantity(int itemIndex, int quantity) {
        Item temp = getItem(itemIndex);
        temp.setQuantity(temp.getQuantity() + quantity);
        System.out.println("Quantity updated.\n");
    }

    
    /**
     * Removes a certain quantity of the given item index. 
     * Preconditions: 1. Item exists. 2. Quantity to remove smaller than current quantity.
     * @param itemIndex the index of the item in the itemList
     * @param quantity  the quantity to remove
     */
    public void removeQuantity(int itemIndex, int quantity) {
        Item temp = getItem(itemIndex);
        temp.setQuantity(temp.getQuantity() - quantity);
        System.out.println("Quantity updated.\n");
    }

    /**
     * Returns all the items that (partially) match the given name.
     * @param name the name to match.
     * @return a string containing a table of the matching items.
     */
    public String getMatchingItemsByName(String name) {
        ArrayList<Item> temp = new ArrayList<Item>();
        for (Item tempItem : itemList) {
            if (tempItem.getName().toLowerCase().contains(name.toLowerCase())) {
                temp.add(tempItem);
            }
        }
        
        if (temp.size() == 0) {
            return null;
        } else {
            return getFormattedItemList(temp);
        }
    }

    /**
     * Returns all the items with current quantity lower than (or equal) the
     * given threshold.
     * @param quantity the quantity threshold.
     * @return a string containing a table of the matching items.
     */
    public String getMatchingItemsByQuantity(int quantity) {
        ArrayList<Item> temp = new ArrayList<Item>();
        for (Item tempItem : itemList) {
            if (tempItem.getQuantity() <= quantity) {
                temp.add(tempItem);
            }
        }
        
        if (temp.isEmpty()) {
            return null;
        } else {
            return getFormattedItemList(temp);
        }
    }

    /**
     * This method can be used to find a item in the Arraylist of items.
     *
     * @param idNumber a <CODE>String</CODE> that represents the ID number of
     * the item that to be searched for.
     * @return the <CODE>int</CODE> index of the items in the Arraylist of
     * items, or -1 if the search failed.
     */
    public int findItem(String idNumber) {

        int index = -1;

        for (int i = 0; i < itemList.size(); i++) {
            String temp = itemList.get(i).getId();

            if (idNumber.equalsIgnoreCase(temp)) {
                index = i;
                break;
            }

        }

        return index;
    }
    public void addUser(int type) {

        Scanner in = new Scanner(System.in);

        if (type < 1 || type > 2) {
            System.out.println("Enter 1 for customer or 2 for employee.");
            type = in.nextInt();
        }

        int id;
        System.out.println("Enter the user ID: ");
        id = in.nextInt();
        String fName, lName;


        System.out.println("Enter first name: ");
        in.nextLine();
        fName = in.nextLine();

        System.out.println("Enter your last name: ");
        lName = in.nextLine();

        switch(type)
        {
            case 1:
            {


                Scanner SSN = new Scanner(System.in);
                System.out.println("Enter social security number (8 digits): ");
                int SSNum = SSN.nextInt();
                System.out.println("this is the ssn" + SSNum);
                String SSString = Integer.toString(SSNum);

                while (SSString.length() != 8)
                {
                    System.out.println("Invalid SSN, enter again: ");
                    SSN = new Scanner(System.in);
                    SSNum = SSN.nextInt();
                    SSString = Integer.toString(SSNum);
                }

                Scanner sc = new Scanner(System.in);
                System.out.println("Enter monthly salary: ");
                float salary = sc.nextFloat();

                System.out.println("Enter bank account number (6 digits): ");
                int account = sc.nextInt();

                String accountString = Integer.toString(account);

                while (accountString.length() != 6)
                {
                    System.out.println("Incorrect bank account number, enter again: ");
                    account = sc.nextInt();
                    accountString = Integer.toString(account);
                }

                Employee employeeObject = new Employee(id, fName, lName, SSNum, salary);
                userList.add(employeeObject);
                break;
            }
            case 2:
            {
                Scanner phoneNumber = new Scanner(System.in);

                System.out.println("Enter phone number (10 digits Numbers only! No spaces): ");
                String phone = phoneNumber.nextLine();

                while (phone.length() != 10)
                {
                    System.out.println("Invalid number, enter again: ");
                    phone = phoneNumber.nextLine();
                }

                Scanner address = new Scanner(System.in);
                System.out.println("Enter address: ");
                String location = address.nextLine();
                Customer customerObject = new Customer(id, fName, lName, phone, location);
                userList.add(customerObject);
                break;
            }
            default:
                break;
        }

    }
    public boolean updateUserInfo() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the user ID: ");
        int userId = sc.nextInt();
        int choice;

        User updating = searchUsers(userId);
        if (updating == null) {
            return false;
        }

        //Variables to use for name
        String line;
        Scanner in = new Scanner(System.in);

        //Get first name of user
        System.out.println("You are updating  " + updating.getFirstName() + updating.getLastName() + "is this ok?");
        System.out.println("Yes (y) or No (n)");
        choice = sc.next().charAt(0);
        if (choice == 'N' || choice == 'n') {
            return false;
        }

        System.out.println("Enter first name: ");
        line = in.nextLine();

        //Update first name with input
        if(!(line.isEmpty()))
            updating.setFirstName(updating.validateName(line));

        //Get last name of user
        System.out.println("Enter last name: " + updating.getLastName());
        line = in.nextLine();

        //Update last name with input
        if(!(line.isEmpty()))
            updating.setLastName(updating.validateName(line));

        System.out.println("Would you like to update salary and Social?");
        System.out.println("Yes (y) or No (n)");
        choice = sc.next().charAt(0);

        if (choice == 'N' || choice == 'n') {
            //Update information for employee
            if(updating instanceof Employee)
            {
                //Get salary and update
                System.out.println("Enter salary " + ((Employee) updating).getSalary());
                line = in.nextLine();
                if(!(line.isEmpty()))
                    ((Employee) updating).setSalary(((Employee) updating).validateSal(Float.parseFloat(line)));

                //Get social security number and update
                System.out.println("Enter your Social.  It was originally: " + ((Employee) updating).getSsn());
                line = in.nextLine();
                if(!(line.isEmpty()))
                    ((Employee) updating).setSsn(((Employee) updating).validateSSN(Integer.parseInt(line)));
            }

            //Update information for customer
            if(updating instanceof Customer)
            {
                //Get address and update
                System.out.println("Enter your address. It was originally: " + ((Customer) updating).getAddress());
                line = in.nextLine();
                if(!(line.isEmpty()))
                    ((Customer) updating).setAddress(((Customer) updating).validateAddress(line));

                //Get phone # and update
                System.out.println("Enter your phone # It was originally: " + ((Customer) updating).getPhoneNum());
                line = in.nextLine();
                if(!(line.isEmpty()))
                    ((Customer) updating).setPhone(((Customer) updating).validatePhone(line));
            }

        }
        return true;

    }

    public void createNewSale(int cID, int eID) {

        Date saleDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String itemid;
        int quantity = 0;
        String line = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter sale date (MM-dd-yyyy):");
        //sc.nextLine();
        line = sc.nextLine();

        if(saleDate == null)
        {
            try
            {
                saleDate = sdf.parse(line);
            }
            catch(ParseException pe)
            {
                System.out.println("Invalid date.");
            }
        }

        System.out.println("Enter the Items ID: ");
        itemid = sc.nextLine();

        System.out.println("Enter the amount sold: ");
        quantity = sc.nextInt();

        while(quantity <= 0)
        {
            System.out.println("Invalid amount. must be greater than 0:");
            sc.nextLine();
            quantity = sc.nextInt();
        }

        transactionInfo.add(new SaleTransaction(itemid, saleDate, quantity, cID, eID));
        //return here to finish the transactions

    }




    public User searchUsers(int id) {

        Boolean match = false;

        //Loop through every user
        for(User users : userList) {
            //Check if IDs match
            if((users.getUID()) == id)
            {
                match = true;
                return users;
            }
        }

        //Tell user if no ID found matching
        if(!match)
            System.out.println("No user match for that ID");

        return null;
    }

    public int userAlreadyExists(int user){

        boolean found = false;

        for(User u : userList) {

            String tempUID = u.getUID();
            if(user.equals(tempUID)) {//fix this to an int
                found = true;
                break;
            }
        }

        return found;

    }

    /**
     * This method is used to retrieve the Item object from the
     * <CODE>itemList</CODE> at a given index.
     *
     * @param i the index of the desired <CODE>Item</CODE> object.
     * @return the <CODE>Item</CODE> object at the index or null if the index is
     * invalid.
     */
    public Item getItem(int i) {
        if (i < itemList.size() && i >= 0) {
            return itemList.get(i);
        } else {
            System.out.println("Invalid Index.\n");
            return null;
        }
    }

    /**
     * This method opens the database file and overwrites it with a
     * text representation of all the items in the <CODE>itemList</CODE>. This
     * should be the last method to be called before exiting the program.
     *
     * @throws IOException
     */
    public void writeDatabase() throws IOException {
        PrintWriter pw = new PrintWriter(DATA_FILE_NAME);

        for (Item c : itemList) {
            pw.print(c.toString());
        }

        pw.close();
    }

    /**
     * The method opens the database file and initializes the <CODE>itemList</CODE> 
     * with its contents. If no such file exists, then one is created. 
     * The contents of the file are "loaded" into the itemList ArrayList in no 
     * particular order. The file is then closed during the duration of the 
     * program until <CODE>writeDatabase()</CODE> is called.
     *
     * @throws IOException
     */
    public void readDatabase() throws IOException {

        File dataFile = new File(DATA_FILE_NAME);

        // If data file does not exist, create it.
        if (!dataFile.exists()) {
            System.out.println("database.ser does not exist, creating one now . . .");
            //if the file doesn't exists, create it
            PrintWriter pw = new PrintWriter(DATA_FILE_NAME);
            //close newly created file so we can reopen it
            pw.close();

            return; // No need to try to read anything from an empty file, so return.
        }

        Scanner itemScanner = new Scanner(new FileReader(dataFile));

        //Initialize the Array List with items from database.txt
        while (itemScanner.hasNextLine()) {

            // split values using the space character as separator
            String[] temp = itemScanner.nextLine().split("~");

            itemList.add(new Item(temp[0], temp[1], temp[2],
                    Integer.parseInt(temp[3]), Float.parseFloat(temp[4])));
        }

        //item list is now in the ArrayList completely so we can close the file
        itemScanner.close();
    }

}
