package clark;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.opencsv.CSVWriter;

public class StoreInventory {

    /*
     * Abstraction stored as a map enables lookups by ID
     */
    private Map<String, Product> STOREINVENTORY;

    /**
     * No argument constructor for the inventory, laid on top of
     * ConcurrentHashMap in oder to ensure fast lookups.
     */
    public StoreInventory() {
        this.STOREINVENTORY = new ConcurrentHashMap<>();
    }

    /**
     * This is necessary in order to prevent aliasing of IDS when making
     * additions
     *
     * @param id
     * @return boolean value if the id is present in the map
     *
     */
    public boolean hasID(String id) {
        return this.STOREINVENTORY.containsKey(id);
    }

    /**
     *
     * @return the amount of products in the inventory
     */
    public int length() {
        return this.STOREINVENTORY.size();
    }

    /**
     *
     * @param id
     * @return the product of with key of String id
     * @requires the id is present in the store inventory
     */
    public Product removeProduct(String id) {
        assert this.STOREINVENTORY.containsKey(id) : "id not found in records";

        Product p = this.STOREINVENTORY.remove(id);
        return p;
    }

    /**
     *
     * @param product
     *            the product to add to the inventory
     * @requires there are no other products with this id
     */
    public void addProduct(Product product) {
        String key = product.id();
        assert !this.STOREINVENTORY
                .containsKey(key) : "this id is already in use";
        this.STOREINVENTORY.put(key, product);
    }

    /**
     *
     * @param id
     *            the id of the quantity to update
     * @param quantity
     *            the updates quantity
     * @requires the id is present within the inventory
     */
    public void updateQuantity(String id, int quantity) {
        assert this.STOREINVENTORY.containsKey(id) : "id not found in records";

        Product p = this.STOREINVENTORY.remove(id);
        p.changeQuanity(quantity);
        this.STOREINVENTORY.put(id, p);
    }

    /**
     * This method will take the current inventory and output it to the console
     * in a chart fashion
     *
     * @requires the inventory is not empty
     */
    public void viewInventory() {
        assert this.STOREINVENTORY.size() != 0 : "Store inventory is empty";
        System.out.print("ID               ");
        System.out.print("NAME          ");
        System.out.print("COST       ");
        System.out.println("QUANTITY");
        System.out.println();
        for (Map.Entry<String, Product> entry : this.STOREINVENTORY
                .entrySet()) {
            Product p = entry.getValue();
            String indent = indent(p.name());
            System.out.print(p.id() + "     ");
            System.out.print(p.name() + "     " + indent);
            System.out.print(p.price() + "     ");
            System.out.println(p.quantity() + "     ");

        }

    }

    /**
     * Allows for flexibility in the method with which the client would like to
     * view the inventory list
     *
     * @param filePath
     *            the path of which to write the CSV file to
     *
     *            Example files are stored in the project as "test1.csv" and
     *            "test2.csv"
     *
     */
    public void viewInventoryCSV(String filePath) {
        File file = new File(filePath);
        try {
            FileWriter out = new FileWriter(file);
            CSVWriter write = new CSVWriter(out);
            String[] header = { "ID", "NAME", "PRICE", "QUANTITY" };
            write.writeNext(header);
            for (Map.Entry<String, Product> entry : this.STOREINVENTORY
                    .entrySet()) {
                Product p = entry.getValue();
                String[] data = new String[4];
                data[0] = p.id();
                data[1] = p.name();
                data[2] = p.price() + "";
                data[3] = p.quantity() + "";
                write.writeNext(data);
            }
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A private helper method for viewInventory
     *
     * @param name
     * @return the correct amount of spaces for table readability
     */
    private static String indent(String name) {
        String indent = "";
        int maxSpaces = 9;
        int indentLength = maxSpaces - name.length();
        for (int i = 0; i < indentLength; i++) {
            indent += " ";
        }
        return indent;
    }

    /**
     * Returns the total value of the inventory as a double
     *
     * @return the total value of the inventory
     * @requires the store inventory to not be empty
     * @requires the total value does not exceed 1.7*10^308
     *
     */
    public double totalValue() {
        assert this.STOREINVENTORY.size() != 0 : "Store inventory is empty";

        double totalValue = 0;
        for (Map.Entry<String, Product> entry : this.STOREINVENTORY
                .entrySet()) {
            Product p = entry.getValue();
            double pTotal = p.price() * p.quantity();
            totalValue += pTotal;
        }
        return totalValue;
    }

}
