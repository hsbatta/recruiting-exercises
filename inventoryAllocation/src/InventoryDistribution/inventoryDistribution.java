package InventoryDistribution;

import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Determines the most (cost)efficient way to deliver items from multiple warehouses to the customer.
 *
 * @author Hrishi Batta
 */
public class inventoryDistribution {


    /**
     * Determines cheapest way to deliver orders
     *
     * @param orders     a list of Items that the customer wants. Both name and quantity are included in each object of the list.
     * @param warehouses a list of warehouses and the stock that they have
     * @return returns a String with instructions for efficient delivery of orders. If there is no stock, no instruction will be given regarding that particular item.
     */
    public String allocateResources(LinkedHashMap<String, Integer> orders, LinkedHashMap<String, LinkedHashMap<String, Integer>> warehouses) {

        // String that will be returned with the instructions*/
        String instructions = "";

        // A list managing the all of the deliveries and the warehouses they come from */
        LinkedHashMap<String, String> deliveries = new LinkedHashMap<>(4, 0.75f);

        // keySets used for iteration of LinkedHashMaps*/
        Set<String> orderKeySet = orders.keySet();
        Set<String> warehouseKeySet = warehouses.keySet();

        // Will contains the stock for each warehouse. Iniitalized in line 43 */
        LinkedHashMap<String, Integer> resourceList;

        // Checks if a order has been fulfilled */
        boolean orderFulfilled = false;


        //iterate through the list of orders
        for (String order : orderKeySet) {

            //the number of items
            int quantity = orders.get(order);

            //iterate through the list of warehouses
            for (String warehouse : warehouseKeySet) {

                //get the stock for each warehouse
                resourceList = warehouses.get(warehouse);

                //check to see if the item is in stock at the warehouse
                if (resourceList.containsKey(order)) {

                    //check if the warehouse has enough stock to fulfill the order
                    if (quantity <= resourceList.get(order)) {

                        orderFulfilled = true;

                        //add the order and the warehouse location to the deliveries list

                        if (deliveries.containsKey(warehouse)) {
                            deliveries.replace(warehouse, deliveries.get(warehouse) + ", { " + order + ": " + quantity + " }");
                        } else {
                            deliveries.put(warehouse, ("{ " + order + ": " + quantity + " }"));
                        }

                    }

                    /*
                      Checks if the order has been fulfilled. If it has not, then the order will try to be fulfilled using
                      stock from multiple warehouses.
                     */
                    if (!orderFulfilled) {

                        // the amount of each item we need
                        int needed = quantity;

                            // check if the stock of the item AND the number of items we need are NOT equal to 0.
                            if (needed != 0 && resourceList.get(order) != 0) {


                                // add the order and location to the deliveries list
                                if (needed > resourceList.get(order)) {
                                    if (deliveries.containsKey(warehouse)) {
                                        deliveries.replace(warehouse, deliveries.get(warehouse) + ", { " + order + ": " + resourceList.get(order) + " }");
                                    } else {
                                        deliveries.put(warehouse, ("{ " + order + ": " + resourceList.get(order) + " }"));
                                    }

                                } else {
                                    if (deliveries.containsKey(warehouse)) {
                                        deliveries.replace(warehouse, deliveries.get(warehouse) + ", { " + order + ": " + needed + " }");
                                    } else {
                                        deliveries.put(warehouse, ("{ " + order + ": " + needed + " }"));
                                    }
                                }
                                needed -= resourceList.get(order);
                            }

                    }

                }
            }
        }




        //initialize the keysSet for the deliveries list
        Set<String> deliveryKeys = deliveries.keySet();

        //formatting
        instructions += "[";

        // counter for formatting
        int count = 0;

        //iterate through each key-value pairs in the deliveries list
        for (String x : deliveryKeys) {
            // add instructions to the instruction String
            instructions += ("{ " + x + ": " + deliveries.get(x) + " }");

            //formatting for adding commas
            if (count < (deliveries.size() - 1)) {
                instructions += (", ");
                count++;
            }
        }

        //formatting
        instructions += ("]");

        //return instructions
        return instructions;

    }
}
