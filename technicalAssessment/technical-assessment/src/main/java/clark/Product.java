package clark;

public class Product {

    /*
     * Private members --------------------------------------------------------
     */

    private String NAME; //Product name

    private String ID; //ID of product

    private double PRICE; // price of product

    private int QUANTITY; // quantity of product

    /**
     *
     * @param id,
     *            the id of product
     * @param name,
     *            the name of product
     * @param price,
     *            the price of product
     * @param quantity,
     *            the quantity of product
     *
     * @return a constructed product with the specified attributes
     */
    public Product(String id, String name, double price, int quantity) {
        assert name != null : "No name recieved by constructor";
        assert id != null : "No id recieved by constructor";
        assert price >= 0 : "No price recieved by constructor";
        assert quantity >= 0 : "No quantity recieved by constructor";

        this.NAME = name;
        this.ID = id;
        this.PRICE = price;
        this.QUANTITY = quantity;
    }

    /**
     *
     * @return the name associated with this
     */
    public String name() {
        return this.NAME;
    }

    public String id() {
        return this.ID;
    }

    /**
     *
     * @return the price associated with this
     */
    public double price() {
        return this.PRICE;
    }

    /**
     *
     * @return the quantity associated with this
     */
    public int quantity() {
        return this.QUANTITY;
    }

    /*
     * These methods allow for the client to alter all attributes of the
     * product, implemented for flexibility of the program;
     */

    void changeName(String newName) {
        this.NAME = newName;
    }

    public void changeID(String newID) {
        this.ID = newID;
    }

    public void changePrice(double newPrice) {
        this.PRICE = newPrice;
    }

    public void changeQuanity(int newQuantity) {
        this.QUANTITY = newQuantity;
    }

}
