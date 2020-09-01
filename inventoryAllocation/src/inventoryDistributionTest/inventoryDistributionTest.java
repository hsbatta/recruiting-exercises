package inventoryDistributionTest;

import InventoryDistribution.inventoryDistribution;
import org.junit.jupiter.api.Assertions;

import java.util.LinkedHashMap;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests inventoryDistribution.java
 * @author Hrishi Batta
 */
class inventoryDistributionTest {
    /** Hash Map with all items in the order*/
    LinkedHashMap<String, Integer> items = new LinkedHashMap<>(4, 0.75f);

    /** Hash Map with all locations of the warehouses */
    LinkedHashMap<String, LinkedHashMap<String, Integer>> locations = new LinkedHashMap<>(4, 0.75f);

    /** Initializing variable of type inventoryDistribution to access its methods*/
    inventoryDistribution i = new inventoryDistribution();

    /**
     * Tests inventoryDistribution.java allocateResources() method
     */
    @org.junit.jupiter.api.Test
    void allocateResourcesTest(){

        items.clear();
        locations.clear();
        LinkedHashMap<String, Integer> owd = new LinkedHashMap<>(4, 0.75f);
        LinkedHashMap<String, Integer> dm = new LinkedHashMap<>(4, 0.75f);


        try{
            items.put("apple", 10);

            owd.put("apple",5);
            dm.put("apple", 5);
            locations.put("owd", owd);
            locations.put("dm", dm);

           Assertions.assertEquals("[{ owd: { apple: 5 } }, { dm: { apple: 5 } }]", i.allocateResources(items, locations));

       }catch(Exception e){
           fail();
       }

        items.clear();
        locations.clear();


        try{
            items.put("apple", 10);
            items.put("banana", 10);
            items.put("orange", 10);


            owd.put("apple", 10);
            owd.put("banana", 7);
            dm.put("banana", 10);
            dm.put("orange", 10);
            locations.put("owd", owd);
            locations.put("dm", dm);


            Assertions.assertEquals("[{ owd: { apple: 10 } }, { dm: { banana: 10 }, { orange: 10 } }]", i.allocateResources(items, locations));

        }catch(Exception e){
            fail();
        }

        items.clear();
        locations.clear();

        try{
            items.put("apple", 10);
            items.put("banana", 10);
            items.put("orange", 10);


            owd.put("apple", 10);
            dm.put("banana", 0);
            dm.put("orange", 10);
            locations.put("owd", owd);
            locations.put("dm", dm);


            Assertions.assertEquals("[{ owd: { apple: 10 } }, { dm: { orange: 10 } }]", i.allocateResources(items, locations));

        }catch(Exception e){
            fail();
        }

        items.clear();
        locations.clear();


        try{
            items.put("apple", 1);

            owd.put("apple", 0);
            locations.put("owd", owd);

            Assertions.assertEquals("[]", i.allocateResources(items, locations));

        }catch(Exception e){
            fail();
        }

        items.clear();
        locations.clear();


    }
}