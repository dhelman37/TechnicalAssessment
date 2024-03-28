package clark;

import java.util.Random;

public class TestingHelpers {

    //All of these methods are included for ease of testing

    /*
     * Generates a random ID of 12 digits for testing
     */
    public static String randomID() {
        Random rand = new Random();
        String id = "";
        for (int i = 0; i < 12; i++) {
            int one = rand.nextInt(10);
            id += one;
        }
        return id;
    }

    /*
     * Generates a random "Name" between 1-9 characters in length
     */
    public static String randomName() {
        Random rand = new Random();
        String name = "";
        int length = rand.nextInt(9) + 1;
        for (int i = 0; i < length; i++) {
            char c = (char) (rand.nextInt(26) + 'a');
            name += c;
        }
        return name;
    }

    /*
     * Generates a random price between 1-500
     */
    public static double randomPrice() {
        Random rand = new Random();
        double price = 1 + rand.nextInt(50000) / 100.0;
        return price;
    }

    /*
     * Generates a random quantity between 1-5000
     */
    public static int randomQuantity() {
        Random rand = new Random();
        int quant = rand.nextInt(5000) + 1;
        return quant;
    }

    /**
     *
     * @param length
     *            the amount of products to assign to the inventory
     * @return a randomly generated StoreInventory with the specified amount of
     *         products
     */
    public static StoreInventory inventoryCreator(int length) {
        StoreInventory inventory = new StoreInventory();
        for (int i = 0; i < length; i++) {
            String id = randomID();
            String name = randomName();
            double price = randomPrice();
            int quantity = randomQuantity();
            if (!inventory.hasID(id)) {
                Product product = new Product(id, name, price, quantity);
                inventory.addProduct(product);
            }
        }
        return inventory;
    }

}
