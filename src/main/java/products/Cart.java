package products;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class contains ArrayList of Item which customer chose.
 * Else contains order full price
 */
public class Cart {

    private ArrayList<Item> userCart; // List of Items
    private BigDecimal orderPrice; // full price

    /**
     * Constructor
     * @param userCart list of Item
     * @param orderPrice order full price
     */
    public Cart(ArrayList<Item> userCart, BigDecimal orderPrice) {
        this.userCart = userCart;
        this.orderPrice = orderPrice;
    }

    /**
     * Empty constructor
     */
    public Cart() {
    }

    /**
     * userCart getter
     * @return field userCart
     */
    public ArrayList<Item> getUserCart() {
        return userCart;
    }

    /**
     * Setter for private field userCart
     * @param userCart ArrayList<Item>
     */
    public void setUserCart(ArrayList<Item> userCart) {
        this.userCart = userCart;
    }

    /**
     * orderPrice getter
     * @return field orderPrice
     */
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    /**
     * setter for private field orderPrice
     * @param orderPrice BigDecimal field orderPrice
     */
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}
