public class Appliance extends Item{
    private final String brandType;

    public Appliance (String id, String name, int quantity, float price, String brandType) {
        super (id, name, quantity, price);
        this.brandType = brandType;



    }
    /**
     * Gets the Brand name of appliance.
     * @return the name of the appliance.
     */
    public String getBrand () {return this.brand;}
    /**
     * Gets the type of appliance.
     * @return the type of appliance.
     */
    public String getType ()  {return this.type;}

}
