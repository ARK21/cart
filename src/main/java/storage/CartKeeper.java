package storage;


import products.Cart;
import products.Item;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Save client cart to postgresql
 */
public class CartKeeper {

    // order id
    private int order_id;

    /**
     * Empty constructor
     */
    public CartKeeper() {
    }

    /**
     * Save cart in database
     *
     * @param fullName    customer full name
     * @param phoneNumber customer phone number
     * @param cart        customer cart
     * @throws SQLException
     * @throws NamingException
     */
    public void keepCart(String fullName, String phoneNumber, Cart cart) throws SQLException, NamingException {
        // connection
        Connection connection = null;
        try {
            // connection pool initialize
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/shopCart");
            // get 1 connection from pool
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // create statement.   nextval('CUSTOMER_ORDER_ID_SEQ'::regclass) == auto-incrementing order_id
        PreparedStatement ps = connection.prepareStatement("INSERT INTO customer VALUES (nextval('CUSTOMER_ORDER_ID_SEQ'::regclass), ?, ?, ?)");
        // sets parameters
        ps.setString(1, fullName);
        ps.setString(2, phoneNumber);
        ps.setBigDecimal(3, cart.getOrderPrice());
        // execute statement. Add customer to database
        ps.execute();
        // new statement
        ps = connection.prepareStatement("SELECT (order_id) FROM customer");
        // initialize order_id with maxId from database
        this.order_id = maxId(ps.executeQuery());
        // get cart of Item
        CopyOnWriteArrayList<Item> order_cart = cart.getUserCart();
        // add cart's item to batabase
        for (int i = 0; i < order_cart.size(); i++) {
            ps = connection.prepareStatement("INSERT INTO orders VALUES (nextval('ORDER_UID_SEQ'::regclass), ?, ?, ?, ?, ?)");
            ps.setInt(1, order_id);
            ps.setString(2, order_cart.get(i).getBook().getTitle());
            ps.setString(3, order_cart.get(i).getBook().getAuthor());
            ps.setBigDecimal(4, order_cart.get(i).getBook().getPrice());
            ps.setInt(5, order_cart.get(i).getQuantity());
            ps.execute();
        }
        // close connection
        connection.close();
    }

    /**
     * Return max existing id in database
     *
     * @param set Result set has got after sql statement
     * @return id
     * @throws SQLException
     */
    public int maxId(ResultSet set) throws SQLException {
        int id = 0;
        while (set.next()) {
            id = set.getInt(1);
        }
        return id;
    }

    /**
     * getter for order_id
     *
     * @return order_id value
     */
    public int getOrder_id() {
        return this.order_id;
    }
}
