package servlets;

import products.Cart;
import products.Item;
import storage.CartKeeper;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Servlet class. Add customer order at postgres database.
 */
@WebServlet("/CartToSql")
public class CartToSql extends HttpServlet {

    /**
     * Method reacts if client send post request
     *
     * @param request  httpservletrequest
     * @param response httpservletresponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get session
        HttpSession session = request.getSession();
        // thread-safe list. Keeps customer's order.
        CopyOnWriteArrayList<Item> itemsList =  (CopyOnWriteArrayList<Item>) session.getAttribute("cart");
        // order's price
        BigDecimal sumOrderPrice =  new BigDecimal(request.getParameter("sumOrderValue"));
        // customer full name
        String fullName = request.getParameter("full_name");
        // customer phone number
        String phoneNUmber = request.getParameter("phone_number");
        // class contain item list and order's price
        Cart cart = new Cart(itemsList, sumOrderPrice);
        // class saves cart in database
        CartKeeper cartKeeper = new CartKeeper();
        try {
            // saves
            cartKeeper.keepCart(fullName, phoneNUmber, cart);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        // get order id, to display at orderSucceed.jsp
        int order_id = cartKeeper.getOrder_id();
        session.setAttribute("order_id", order_id);
        // to orderSucceed.jsp
        request.getRequestDispatcher("orderSucceed.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
