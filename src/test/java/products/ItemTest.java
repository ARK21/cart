package products;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Test class for Item class
 */
public class ItemTest {

    /**
     * heck if Item's class methods work right
     */
    @Test
    public void IsClassItemWorksRight() {
        // create book
        Book book = new Book(1, "Poltava", "Pushkin", new BigDecimal(75.5));
        // add book in item and set quantity
        Item item = new Item(book,1);
        // is this that book which has added at Item
        assertTrue(item.getBook().equals(book));
        // is quantity == 1
        assertTrue(item.getQuantity() == 1);
        // new book
        Book newBook = new Book(1, "War and Piece", "Tolstoy", new BigDecimal(82.5));
        // set in Item
        item.setBook(newBook);
        // set quantity == 2
        item.setQuantity(2);
        // now new book inside?
        assertTrue(item.getBook().equals(newBook));
        // quantity == 2?
        assertTrue(item.getQuantity() == 2);
    }
}