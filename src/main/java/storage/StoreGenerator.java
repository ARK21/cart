package storage;

import products.Book;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Generate HashMap of Book from postgresql base
 */
public class StoreGenerator {

    private static Map store = new HashMap<Integer, Book>(); // Keep generated map of books <book_id, Book>

    /**
     * Constructor to initialize
     */
    public StoreGenerator() {
        getBookSqlData();
    }

    /**
     * Method gets data from postgresql base. Use connection pool
     */
    private void getBookSqlData() {
        try {
            InitialContext context = new InitialContext(); //connection pool context
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/shopCart"); // initialize pool
            Connection c = dataSource.getConnection(); // get 1 connection from pool
            PreparedStatement statement = c.prepareStatement("SELECT * FROM books");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                int id = set.getInt(1);
                String title = set.getString(2);
                String author = set.getString(3);
                double price = set.getDouble(4);
                // add book to map
                store.put(id, new Book(id, title, author, new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP) ));
            }
            c.close(); // close connection
        } catch (SQLException| NamingException e){
            e.printStackTrace();
        }
    }

    /**
     * Getter for store variable
     * @return store instance
     */
    public Map<Integer, Book> getStore(){
        return store;
    }
}
