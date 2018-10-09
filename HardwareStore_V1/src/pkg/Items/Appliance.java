package pkg.Items;

/**
 * Application which extends item.java to accommodate for appliances
 * @author Jeffrey Vallejo
 * @version 1 2-19-2018
 */
public class Appliance extends Item {
    private final String brandType;

    public Appliance (String id, String name, int quantity, float price, String brandType) {
        super (id, name, quantity, price);
        this.brandType = brandType;



    }
    /**
     * Gets the Brand name of appliance.
     * @return the name of the appliance.
     */
    public String getBrand () {return this.brandType;}


}
