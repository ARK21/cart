package products;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Class Book. Main class for books store.
 */
public class Book {

    private int id; // book's id
    private String title; // book's title
    private String author; // book's author
    private BigDecimal price; // price per one copy. Choose BigDecimal type for precision.

    /**
     * Constructor
     * @param id     book's id
     * @param title  book's title
     * @param author book's author
     * @param price  price per one copy
     */
    public Book(int id, String title, String author, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Empty constructor
     */
    public Book() {
    }

    /**
     * get field id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * set value into field id
     *
     * @param id setting value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get field "title"
     *
     * @return id
     */
    public String getTitle() {
        return title;
    }

    /**
     * set value into field title
     *
     * @param title setting value
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * get field "author"
     *
     * @return author value
     */
    public String getAuthor() {
        return author;
    }

    /**
     * set value into field author
     *
     * @param author setting value
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * get field "price"
     *
     * @return price value
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * set value into field price. precision = 2
     *
     * @param price BigDecimal value
     */
    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
