public class Hardware extends Item {

    private final String category;

    /**
     * Constructor for Hardware
     *
     * @param id       for item ID number
     * @param name     for item name
     * @param category for item category
     * @param quantity for item quantity
     * @param price    for item price
     */
    public Hardware(String id, String name, String category, int quantity, float price) {
        super(id, name, quantity, price);
        this.category = category;

    }

    // the Hardware subclass adds one method.
    public String getCategory() {
        return category;
    }

}