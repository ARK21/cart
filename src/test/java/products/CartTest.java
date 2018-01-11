package products;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

/**
 * Test class for Cart class
 */
public class CartTest {

    /**
     * Check if Cart methods work right
     */
    @Test
    public void IsClassCartWorkRight() {
        // create Item storage
        CopyOnWriteArrayList<Item> userCart = new CopyOnWriteArrayList<Item>();
        // add 2 book. First quantity = 1, second = 2
        userCart.add(new Item(new Book(1, "Poltava", "Pushkin", new BigDecimal(75.5)),1));
        userCart.add(new Item(new Book(2, "War and Piece", "Tolstoy", new BigDecimal(82.5)),2));
        // add userCart in cart, set total price
        Cart cart = new Cart(userCart, new BigDecimal(240.5));
        // is userCart which has added
        assertTrue(cart.getUserCart().equals(userCart));
        // total price == 240.5
        assertTrue(cart.getOrderPrice().compareTo(new BigDecimal(240.5)) == 0);
        // create new storage
        CopyOnWriteArrayList<Item> newUserCart = new CopyOnWriteArrayList<Item>();
        // set new storage (empty)
        cart.setUserCart(newUserCart);
        // set total price
        cart.setOrderPrice(new BigDecimal(10.1));
        // where is no userCart, it's newUserCart
        assertFalse(cart.getUserCart().equals(userCart));
        // price don't 240.5. It's 10.1 now
        assertFalse(cart.getOrderPrice().compareTo(new BigDecimal(240.5)) == 0);
    }

}