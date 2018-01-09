package storage;


import products.Cart;
import products.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Save client cart to postgresql
 */
public class CartKeeper {

    private Connection connection;

    public CartKeeper() {
    }

    public void keepCart(String fullName, String phoneNumber, Cart cart) throws SQLException {
        try {
            connection = Store.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement ps = connection.prepareStatement("INSERT INTO customer VALUES (nextval('customer_order_id_seq'::regclass), ?, ?, ?)");

        ps.setString(1, fullName);
        ps.setString(2, phoneNumber);
        ps.setBigDecimal(3, cart.getOrderPrice());
        ps.execute();

        ps = connection.prepareStatement("SELECT (order_id) from customer");
        int order_id = maxId(ps.executeQuery());
        System.out.println(order_id);
        List<Item> order_cart = cart.getUserCart();

        for (int i = 0; i < order_cart.size(); i++) {
            ps = connection.prepareStatement("INSERT INTO orders VALUES (nextval('order_uid_seq'::regclass), ?, ?, ?, ?, ?)");
            ps.setInt(1, order_id);
            ps.setString(2, order_cart.get(i).getBook().getTitle());
            ps.setString(3, order_cart.get(i).getBook().getAuthor());
            ps.setBigDecimal(4, order_cart.get(i).getBook().getPrice());
            ps.setInt(5, order_cart.get(i).getQuantity());
            ps.execute();
        }
        connection.close();
    }

    public int maxId(ResultSet set) throws SQLException {
        int id = 0;
        while (set.next()) {
            id = set.getInt(1);
        }
        return id;
    }
}
