package products;

import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;

/**
 * Test Book class
 */
public class BookTest {

    /**
     * Check if Book's class methods work right
     */
    @Test
    public void isClassBookWorkRight() {
        // create new Book
        Book book = new Book(1, "Poltava", "Pushkin", new BigDecimal(75.5));
        // true
        assertTrue(book.getAuthor().equals("Pushkin"));
        assertTrue(book.getTitle().equals("Poltava"));
        assertTrue(book.getId() == 1);
        assertTrue(book.getPrice().compareTo(new BigDecimal(75.5)) == 0);
        // set new Id, author, tittle, price
        book.setId(2);
        book.setTitle("War and Piece");
        book.setAuthor("Tolstoy");
        book.setPrice(new BigDecimal(82.5));
        // now it's false
        assertFalse(book.getAuthor().equals("Pushkin"));
        assertFalse(book.getTitle().equals("Poltava"));
        assertFalse(book.getId() == 1);
        assertFalse(book.getPrice().compareTo(new BigDecimal(75.5)) == 0);
    }

}