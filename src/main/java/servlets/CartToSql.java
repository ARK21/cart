package servlets;

import products.Cart;
import products.Item;
import storage.CartKeeper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/CartToSql")
public class CartToSql extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Item> itemsList =  (ArrayList<Item>) session.getAttribute("cart");
        BigDecimal sumOrderValue =  new BigDecimal(request.getParameter("sumOrderValue"));
        String fullName = request.getParameter("full_name");
        String phoneNUmber = request.getParameter("phone_number");
        Cart cart = new Cart(itemsList, sumOrderValue);
        try {
            new CartKeeper().keepCart(fullName, phoneNUmber, cart);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("orderSucceed.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
