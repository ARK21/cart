package storage;

import products.Book;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Store {

    private Map<Integer, Book> store = new HashMap<Integer, Book>();

    public Store() {

    }

    public Map<Integer, Book> getStore() throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        PreparedStatement statement = c.prepareStatement("SELECT * FROM books");
        ResultSet set = statement.executeQuery();
        Map<Integer, Book> sqlStore = new HashMap<Integer, Book>();
        while (set.next()) {
            int id = set.getInt(1);
            String title = set.getString(2);
            String author = set.getString(3);
            float price = set.getFloat(4);
            sqlStore.put(id, new Book(id, title, author, price));
        }
        return sqlStore;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:1121/OnlineShop", "postgres", "2114");
    }


}
