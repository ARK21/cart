package products;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private ArrayList<Item> userCart;
    private BigDecimal orderPrice;

    public Cart(ArrayList<Item> userCart, BigDecimal orderPrice) {
        this.userCart = userCart;
        this.orderPrice = orderPrice;
    }

    public Cart() {

    }

    public ArrayList<Item> getUserCart() {
        return userCart;
    }

    public void setUserCart(ArrayList<Item> userCart) {
        this.userCart = userCart;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}
