package pkg.Items;
import java.io.Serializable;

/**
 * This class is a very simple representation of a hardware item. There are only getter
 * methods and no setter methods and as a result an item cannot be mutated once
 * initialized. An item object can also call the two override methods
 * <CODE>toString()</CODE> and <CODE>equals()</CODE>
 *
 * @author Jeffrey Vallejo
 */
public abstract class Item implements Serializable {

    private  String id;
    private  String name;
    private int quantity;
    private  float price;

    public Item () {} //default constructor.

    /**
     * This constructor initializes the item object. The constructor provides no
     * user input validation. That should be handled by the class that creates a
     * item object.
     *
     * @param id a <b><CODE>String</CODE></b> that represents the ID
     *                 random string of length 5 – can contain letters and numbers
     *
     * @param name a <b><CODE>String</CODE></b> that represents the name.
     *
     * @param quantity a <b><CODE>int</CODE></b> that represents the quantity
     *
     * @param price an <b><CODE>float</CODE></b> that represents the price
     *
     */
    public Item(String id, String name, int quantity, float price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
/*
    public class SortById implements Comparator<Item> {
        /*Comparator for sorting the list by Item Id*//*
        public static Comparator<Item> ItemIdComparator = new Comparator<Item>()
        {

            public int compare(Item s1, Item s2)
            {
                String ItemId1 = s1.getId().toUpperCase();
                String ItemId2 = s2.getId().toUpperCase();

                //ascending order
                return ItemId1.compareTo(ItemId2);

            }
        };
*/

    /**
     * This method returns the item's ID.
     *
     * @return a <b><CODE>String</CODE></b> that is the ID number of the item.
     */
    public String getId() {
        return id;
    }

    /**
     * This method returns the item's name.
     *
     * @return a <b><CODE>String</CODE></b> that is the item's name.
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the item's quantity.
     *
     * @return an <b><CODE>int</CODE></b> that is the item's weight
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * This method set the item's quantity.
     *
     *  @param quantity a <b><CODE>int</CODE></b> that represents the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity= quantity;
    }

    /**
     * This method returns the item's price.
     *
     * @return a <b><CODE>float</CODE></b> that is the item's price
     */
    public float getPrice() {
        return price;
    }
    /**
     * This method returns the item's fields as a string representation.
     *
     * @return a <b><CODE>String</CODE></b> that lists the fields of the item
     * object delineated by a space and in the same order as the constructor
     */
    @Override
    public String toString() {
        return id + "~" + name + "~" + /*category +*/ "~" + quantity + "~"
                + String.format("%.2f", price) + "\n";
    }

    /**
     * This method provides a way to compare two item objects.
     *
     * @param c a <b><CODE>Item</CODE></b> object that is used to compare to
     * <b><CODE>this</CODE></b> item. Two orders are equal if their ID is the
     * same.
     * @return the <CODE>boolean</CODE> value of the comparison.
     */
    public boolean equals(Item c) {
        return c.getId().equals(this.id);
    }

}
