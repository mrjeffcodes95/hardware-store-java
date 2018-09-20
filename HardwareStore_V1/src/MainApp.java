import java.io.IOException;
import java.util.Scanner;


public class MainApp {

    // This object will allow us to interact with the methods of the class HardwareStore
    private HardwareStore hardwareStore;
    //private final Hardware hardware;
    private static final Scanner CONSOLE_INPUT = new Scanner(System.in); // Used to read from System's standard input

    /**
     * Default constructor. Initializes a new object of type Hardwarestore
     *
     * @throws IOException
     */
    public MainApp() throws IOException {

        hardwareStore = new HardwareStore();
    }

    /**
     * Shows all items in the inventory.
     */
    public void showAllItems() {
        String showitems = "item";
        System.out.print(hardwareStore.getAllFormatted(showitems));
    }
    public void showAllUsers() {
        String showusers = "user";
        System.out.println(hardwareStore.getAllFormatted(showusers));
    }
    public void showSales() {
        String showSales = "sales";
        System.out.println(hardwareStore.getAllFormatted(showSales));
    }

    /**
     * This method will add items quantity with given number. If the item does
     * not exist, it will call another method to add it.
     *
     */
    public void addItemQuantity() {

        //Setup a scanner
        Scanner sc = new Scanner(System.in);

        System.out.println("Please input the ID of item");
        String idNumber = CONSOLE_INPUT.nextLine();

        //validates user input
        if (!idNumber.matches("[A-Za-z0-9]{5}")) {
            System.out.println("Invalid ID Number: not proper format. "
                    + "ID Number must be 5 alphanumeric characters.\n");
            return;
        }
        //checks data for stored ID
        int itemIndex = hardwareStore.findItem(idNumber);
        if (itemIndex != -1) { // If item exists in the database

            System.out.println("Item found in database. Please enter quantity to add.");
            int quantity = CONSOLE_INPUT.nextInt();
            if (quantity <= 0) {
                System.out.println("Invalid quantity. "
                        + "The addition amount must be larger than 0.\n");
                return;
            }
            hardwareStore.addQuantity(itemIndex, quantity);
        } else {
            //If it reaches here, the item does not exist. We need to add new one.
            System.out.println("Item with given number does not exist.\n");
            // Enter name
            System.out.println("what type of item would you like to add? \n");
            System.out.println("1. Hardware\n" + "2. Appliance\n");
            //get the users input
            int itemType = sc.nextInt();

            //Validate user input
            if((itemType < 1) || (itemType > 2))
            {
                System.out.println("Invalid. Enter valid choice: ");
                itemType = sc.nextInt();
            }
            //Add item
            try {
                hardwareStore.addNewItem(itemType, idNumber);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //hardwareStore.addNewItem(idNumber, name, category, quantity, price);
        }

    }

    public void addNewUser() {

        System.out.println("what kind of user would you like to add?");
        System.out.println("1. Employee\n" + "2. Customer\n");
        int userType  = CONSOLE_INPUT.nextInt();
        hardwareStore.addUser(userType);
    }
    public void updateUser() {

        if (hardwareStore.updateUserInfo()){
            System.out.println("User was updated successfully");
        }
        else {
            System.out.println("Error user was not updated");
        }
    }

    public void deleteItem () {

        Scanner sc = new Scanner(System.in);
        String deleteItem = null;
        System.out.println("What is the Id number of the Item to be deleted?: ");
        String remove = sc.nextLine();
        deleteItem = sc.nextLine();
        if (hardwareStore.findItem(deleteItem) >= 0) {
            //this needs to be created in HardwareStore to remove the item from the array list
            hardwareStore.removeItem(deleteItem);

        }

    }

    public void completeSale() {

        int cID = -1;
        int eID = -1;


        System.out.println("Enter customer ID: ");
        cID = CONSOLE_INPUT.nextInt();
        User customer = hardwareStore.searchUsers(cID);
        if (customer == null) {
            System.out.println("Customer with specified ID does not exist");
            return;
        }

        System.out.println("Enter employee ID: ");
        eID = CONSOLE_INPUT.nextInt();
        User employee = hardwareStore.searchUsers(eID);
        if (employee == null) {
            System.out.println("Employee with specified ID does not exist.");
            return;
        }

        hardwareStore.createNewSale(cID, eID);




    }



    /**
     * This method will remove the given quantity of an item with given number.
     * If the item does not exist, it will show an appropriate message.
     */
    public void removeItemQuantity() {

        System.out.println("Please input the ID of item");
        String idNumber = CONSOLE_INPUT.nextLine();
        if (!idNumber.matches("[A-Za-z0-9]{5}")) {
            System.out.println("Invalid ID Number: not proper format. "
                    + "ID Number must be at least 5 alphanumeric characters.");
            return;
        }

        int itemIndex = hardwareStore.findItem(idNumber);
        int currentQuantity;
        if (itemIndex == -1) {
            System.out.println("Item does not exist.\n");
            return;
        } else {
            currentQuantity = hardwareStore.getItem(itemIndex).getQuantity();
            System.out.println("Current quantity: " + currentQuantity + "\n");
        }

        System.out.println("Please input the quantity to remove.");
        int quantity = CONSOLE_INPUT.nextInt();
        if (quantity > currentQuantity) {
            System.out.println("Invalid quantity. "
                    + "The removal amount must be smaller than current quantity.\n");
        } else {
            hardwareStore.removeQuantity(itemIndex, quantity);
        }
    }

    /**
     * This method can search item by a given name (part of name.
     * Case-insensitive.) Will display all items with the given name.
     */
    public void searchItemByName() {

        System.out.println("Please input the name of item.\n");
        String name = CONSOLE_INPUT.nextLine();

        String output = hardwareStore.getMatchingItemsByName(name);
        if (output == null) {
            System.out.println("Item not found.");
        } else {
            System.out.println(output);
        }
    }

    /**
     * This method can search item below a certain quantity. Will display all
     * items fits such condition.
     */
    public void searchItemByQuantity() {

        System.out.println("Please enter the quantity:\n");
        int quantity = CONSOLE_INPUT.nextInt();

        if (quantity < 0) {
            System.out.println("Quantity should be at least 0.\n");
        }

        String output = hardwareStore.getMatchingItemsByQuantity(quantity);
        if (output == null) {
            System.out.println("No items found below given quantity.");
        } else {
            System.out.println(output);
        }
    }

    public void saveDatabase() throws IOException {
        hardwareStore.writeDatabase();
    }

    /**
     * This method will begin the user interface console. Main uses a loop to
     * continue executing commands until the user types '6'.
     *
     * @param args this program expects no command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        MainApp app = new MainApp();

        String welcomeMessage = "\nWelcome to the Hardware Store database. Choose one of the following functions:\n\n"
                + "\t1. Show all existing items in stock and their quantities.\n"
                + "\t2. Add a new quantity of a specific item to the stock.\n"
                + "\t3. Remove a certain quantity of a specific item type.\n"
                + "\t4. Search for an item (given its name or part of its name).\n"
                + "\t5. Show a list of all items below a certain quantity.\n"
                + "\t6. Exit program.\n";

        System.out.println(welcomeMessage);

        int selection = CONSOLE_INPUT.next().charAt(0);
        CONSOLE_INPUT.nextLine();

        while (selection != 10) {

            switch (selection) {
                case 1:
                    app.showAllItems();
                    break;
                case 2:
                    app.addItemQuantity();
                    break;
                case 3:
                    app.deleteItem();
                    //app.removeItemQuantity();
                    break;
                case 4:
                    app.searchItemByName();
                    break;
                case 5:
                    app.showAllUsers();
                    break;
                case 6:
                    app.addNewUser();
                    break;
                case 7:
                    app.updateUser();
                    break;
                case 8:
                    app.completeSale();
                case 9:
                    app.showSales();
                case 11:
                    System.out.println(welcomeMessage);
                    break;
                default:
                    System.out.println("That is not a recognized command. Please enter another command or 'h' to list the commands.");
                    break;

            }

            System.out.println("Please enter another command or '11' to list the commands.\n");
            selection = CONSOLE_INPUT.next().charAt(0);

            CONSOLE_INPUT.nextLine();
        }

        CONSOLE_INPUT.close();
        
        
        System.out.print("Saving database...");
        app.saveDatabase();

        System.out.println("Done!");

    }
}
