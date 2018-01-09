package storage;

import products.Book;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Store {

    private static Map<Integer, Book> store = new HashMap<Integer, Book>();
    private static DataSource dataSource  = null;

    public Store() {

    }

    public static Map<Integer, Book> getStore() throws SQLException, ClassNotFoundException, NamingException {
        InitialContext context = new InitialContext();
        dataSource = (DataSource) context.lookup("java:comp/env/jdbc/shopCart");
        Connection c = dataSource.getConnection();
        PreparedStatement statement = c.prepareStatement("SELECT * FROM books");
        ResultSet set = statement.executeQuery();
        Map<Integer, Book> sqlStore = new HashMap<Integer, Book>();
        while (set.next()) {
            int id = set.getInt(1);
            String title = set.getString(2);
            String author = set.getString(3);
            double price = set.getDouble(4);
            sqlStore.put(id, new Book(id, title, author, new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP) ));
        }
        store = sqlStore;
        c.close();
        return sqlStore;
    }
}
