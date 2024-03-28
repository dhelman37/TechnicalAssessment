package clark;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Testing of addNewProduct with an empty inventory, ensuring presence in
     * the inventory.
     */
    @Test
    public void addNewProduct1() {
        StoreInventory inventory = new StoreInventory();
        Product product = new Product("123456789012", "Davis", 54.99, 100);
        inventory.addProduct(product);
        assertTrue(inventory.hasID("123456789012"));
        assertEquals(inventory.length(), 1);
    }

    /*
     * Testing of addNewProduct with a slightly full inventory, ensuring
     * presence in inventory
     */
    @Test
    public void addNewProduct2() {
        StoreInventory inventory = TestingHelpers.inventoryCreator(21);
        int length = inventory.length();
        Product product = new Product("123456789012", "Davis", 54.99, 100);
        inventory.addProduct(product);
        assertTrue(inventory.hasID("123456789012"));
        assertEquals(length + 1, inventory.length());
    }

    /*
     * Testing of addNewProduct with a full inventory, ensuring presence in
     * inventory
     */
    @Test
    public void addNewProduct3() {
        StoreInventory inventory = TestingHelpers.inventoryCreator(1000);
        int length = inventory.length();
        Product product = new Product("111111111111", "Davis", 54.99, 100);
        inventory.addProduct(product);
        assertTrue(inventory.hasID("111111111111"));
        assertEquals(length + 1, inventory.length());
    }

    /*
     * Testing of addNewProduct with an overly-full inventory, ensuring presence
     * in inventory
     */
    @Test
    public void addNewProduct4() {
        StoreInventory inventory = TestingHelpers.inventoryCreator(100000);
        int length = inventory.length();
        Product product = new Product("111111111111", "Davis", 54.99, 100);
        inventory.addProduct(product);
        assertTrue(inventory.hasID("111111111111"));
        assertEquals(length + 1, 100001);
    }

    @Test
    public void addNewProduct5() {
        StoreInventory inventory = TestingHelpers.inventoryCreator(500000);
        int length = inventory.length();
        Product product = new Product("111111111111", "Davis", 54.99, 100);
        inventory.addProduct(product);
        assertTrue(inventory.hasID("111111111111"));
        assertEquals(length + 1, inventory.length());
    }

    /*
     * Testing of the removeProduct method with a inventory of 50, ensuring all
     * attributes of the removed product are correct as well as asserting the
     * removed product no longer has membership in the inventory
     */
    @Test
    public void removeProduct1() {
        StoreInventory inventory = TestingHelpers.inventoryCreator(50);
        Product product = new Product("111111111111", "Davis", 54.99, 100);
        inventory.addProduct(product);
        int length = inventory.length();
        Product removed = inventory.removeProduct("111111111111");
        assertEquals(product.name(), removed.name());
        assertEquals(product.id(), removed.id());
        assertTrue(product.price() == removed.price());
        assertTrue(product.quantity() == removed.quantity());
        assertEquals(length - 1, inventory.length());
        assertTrue(!inventory.hasID("111111111111"));
    }

    /*
     * Testing of the removeProduct method with a inventory of 10000, ensuring
     * all attributes of the removed product are correct as well as asserting
     * the removed product no longer has membership in the inventory
     */
    @Test
    public void removeProduct2() {
        StoreInventory inventory = TestingHelpers.inventoryCreator(10000);
        Product product = new Product("101010101010", "Clark", 70, 45);
        inventory.addProduct(product);
        int length = inventory.length();
        Product removed = inventory.removeProduct("101010101010");
        assertEquals(product.name(), removed.name());
        assertEquals(product.id(), removed.id());
        assertTrue(product.price() == removed.price());
        assertTrue(product.quantity() == removed.quantity());
        assertEquals(length - 1, inventory.length());
        assertTrue(!inventory.hasID("101010101010"));
    }

    /*
     * Testing of the updateQuantity method with an inventory size of 100.
     * Ensures that the other attributes remain unchanged, as well as ensures
     * the value has changed by checking the alias "product" has changed from
     * its initialized value.
     */
    @Test
    public void updateQuantity1() {
        StoreInventory inventory = TestingHelpers.inventoryCreator(50);
        int length = inventory.length();
        Product product = new Product("101010101010", "Clark", 70, 45);
        inventory.addProduct(product);
        inventory.updateQuantity("101010101010", 80);
        Product removed = inventory.removeProduct("101010101010");
        inventory.addProduct(removed);
        assertTrue(removed.quantity() == 80);
        assertTrue(product.quantity() != 45);
        assertTrue(length + 1 == inventory.length());
        assertEquals(removed.id(), product.id());
        assertEquals(removed.name(), product.name());
    }

    /*
     * Testing of the updateQuantity method with an updated quantity of
     * MAX_VALUE. Ensures that the other attributes remain unchanged, as well as
     * ensures the value has changed by checking the alias "product" has changed
     * from its initialized value.
     */
    @Test
    public void updateQuantity2() {
        StoreInventory inventory = TestingHelpers.inventoryCreator(1000);
        int length = inventory.length();
        Product product = new Product("111111111111", "Davis", 70, 100);
        inventory.addProduct(product);
        inventory.updateQuantity("111111111111", Integer.MAX_VALUE);
        Product removed = inventory.removeProduct("111111111111");
        inventory.addProduct(removed);
        assertTrue(removed.quantity() == Integer.MAX_VALUE);
        assertTrue(product.quantity() != 100);
        assertTrue(length + 1 == inventory.length());
        assertEquals(removed.id(), product.id());
        assertEquals(removed.name(), product.name());
    }

    /*
     * Test 3 for updating the quantity
     */
    @Test
    public void updateQuantity3() {
        StoreInventory inventory = TestingHelpers.inventoryCreator(1000);
        int length = inventory.length();
        Product product = new Product("111111111111", "Davis", 70, 100);
        inventory.addProduct(product);
        inventory.updateQuantity("111111111111", 0);
        Product removed = inventory.removeProduct("111111111111");
        inventory.addProduct(removed);
        assertTrue(removed.quantity() == 0);
        assertTrue(product.quantity() != 100);
        assertTrue(length + 1 == inventory.length());
        assertEquals(removed.id(), product.id());
        assertEquals(removed.name(), product.name());
    }

    /*
     * Will print the inventory of length 1000 to the console, results will
     * appear in console
     */
    @Test
    public void viewCurrentInventory() {
        StoreInventory inventory = TestingHelpers.inventoryCreator(1000);
        inventory.viewInventory();
    }

    /*
     * Will print the inventory to a csv file, examples of past tests are stored
     * under "test.csv" and "test2.csv"
     */
    @Test
    public void viewCurrentInventoryCSV() {
        StoreInventory inventory = TestingHelpers.inventoryCreator(5000);
        inventory.viewInventoryCSV("test2.csv");
    }

    /*
     * Tests calculation of the total value of the inventory. Due to minor
     * ambiguities in the incredibly large numbers, so converting to string and
     * checking each character value for equality suffices as well as checking
     * the last character to ensure exponents are equal.
     */
    @Test
    public void calculateTotalValue1() {
        StoreInventory inventory = new StoreInventory();
        double sum = 0;
        for (int i = 0; i < 50; i++) {
            String id = TestingHelpers.randomID();
            String name = TestingHelpers.randomName();
            double price = TestingHelpers.randomPrice();
            int quantity = TestingHelpers.randomQuantity();
            sum += price * quantity;
            Product product = new Product(id, name, price, quantity);
            inventory.addProduct(product);
        }
        int length = inventory.length();
        String sumValue = sum + "";
        String totalValue = inventory.totalValue() + "";
        for (int i = 0; i < 9; i++) {
            assertTrue(sumValue.charAt(i) == totalValue.charAt(i));
        }

        assertTrue(sumValue.charAt(sumValue.length() - 1) == totalValue
                .charAt(totalValue.length() - 1));
        assertTrue(length == inventory.length());
    }

    /*
     * Tests the TotalValue method using the same approach, but with a much
     * larger inventory
     */
    @Test
    public void calculateTotalValue2() {
        StoreInventory inventory = new StoreInventory();
        double sum = 0;
        for (int i = 0; i < 1000; i++) {
            String id = TestingHelpers.randomID();
            String name = TestingHelpers.randomName();
            double price = TestingHelpers.randomPrice();
            int quantity = TestingHelpers.randomQuantity();
            sum += price * quantity;
            Product product = new Product(id, name, price, quantity);
            inventory.addProduct(product);
        }
        int length = inventory.length();
        String sumValue = sum + "";
        String totalValue = inventory.totalValue() + "";
        for (int i = 0; i < 9; i++) {
            assertTrue(sumValue.charAt(i) == totalValue.charAt(i));
        }

        assertTrue(sumValue.charAt(sumValue.length() - 1) == totalValue
                .charAt(totalValue.length() - 1));
        assertTrue(length == inventory.length());
    }

}
