package InventoryDistribution;

/**
 * The Item class is used to store values for each item in an order. It has two variables: name and quantity.
 *
 * @author Hrishi Batta
 */
public class Item {

    /**
     * The name of the Item
     */
    String name;

    /**
     * The amount of each item we need
     */
    int quantity;

    /**
     * Constructor for the Item class. Helps in creating objects of type Item.
     *
     * @param name     the name of the item
     * @param quantity the quantity of the item
     */
    public Item(String name, int quantity) {
        setName(name);
        setQuantity(quantity);
    }


    /**
     * Sets the name of the item
     * @param name the name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the quantity of the item
     * @param quantity the quantity of the item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

