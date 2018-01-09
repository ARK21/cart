package storage;


import products.Cart;
import products.Item;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Save client cart to postgresql
 */
public class CartKeeper {

    public CartKeeper() {
    }

    public void keepCart(String fullName, String phoneNumber, Cart cart) throws SQLException, NamingException {
        Connection connection = null;
        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/shopCart");
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement ps = connection.prepareStatement("INSERT INTO customer VALUES (nextval('CUSTOMER_ORDER_ID_SEQ'::regclass), ?, ?, ?)");
        ps.setString(1, fullName);
        ps.setString(2, phoneNumber);
        ps.setBigDecimal(3, cart.getOrderPrice());
        ps.execute();
        ps = connection.prepareStatement("SELECT (order_id) FROM customer");
        int order_id = maxId(ps.executeQuery());
        System.out.println(order_id);
        List<Item> order_cart = cart.getUserCart();

        for (int i = 0; i < order_cart.size(); i++) {
            ps = connection.prepareStatement("INSERT INTO orders VALUES (nextval('ORDER_UID_SEQ'::regclass), ?, ?, ?, ?, ?)");
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
