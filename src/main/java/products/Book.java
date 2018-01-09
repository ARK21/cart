package products;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Book implements Product {

    private int id;
    private String title;
    private String author;
    private BigDecimal price;


    public Book(int id, String title, String author, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        price.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Set precision #0.00 to double value
     * @param price double value
     * @return formated value
     */
    private double setPrecision(double price) {

        DecimalFormat dec = new DecimalFormat("#0.00");
        return Double.parseDouble( String.format("%.2f", price));
    }
}
