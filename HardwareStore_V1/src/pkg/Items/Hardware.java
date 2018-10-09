package pkg.Items;

import pkg.Items.Item;

/**
 * Template to extend item which allows for the input of hardware
 * @author Jeffrey Vallejo
 * @version 1 2-21-2018
 */
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

    /**
     * The Hardware subclass adds one method.
     *
     * @return category
     */
    public String getCategory() {
        return category;
    }

}