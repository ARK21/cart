package products;

/**
 * Class keeps instance of Book which customer added in his cart and it's quantity
 */
public class Item {

    private Book book = new Book(); // instance of Book
    private int quantity; // quantity of book

    /**
     * Constructor
     * @param book instance
     * @param quantity how much books
     */
    public Item(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    /**
     * Empty constructor
     */
    public Item() {
    }

    /**
     * book getter
     * @return field book
     */
    public Book getBook() {
        return book;
    }

    /**
     * book setter
     * @param book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * quantity getter
     * @return field quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * quantity setter
     * @param quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
