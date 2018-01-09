package servlets;

import products.Item;
import storage.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/CartController")
public class CartController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action.equals("ordernow")) {
            try {
                if (session.getAttribute("cart") == null) {
                    List<Item> cart = new ArrayList<>();
                    cart.add(new Item(Store.getStore().get(Integer.parseInt(request.getParameter("id"))), 1));
                    session.setAttribute("cart", cart);
                } else {
                    List<Item> cart = (List<Item>) session.getAttribute("cart");
                    int index = isExisting(Integer.parseInt(request.getParameter("id")), cart);
                    if (index == -1) {
                        cart.add(new Item(Store.getStore().get(Integer.parseInt(request.getParameter("id"))), 1));
                        session.setAttribute("cart", cart);
                    } else {
                        int quantity = cart.get(index).getQuantity() + 1;
                        cart.get(index).setQuantity(quantity);
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExisting(Integer.parseInt(request.getParameter("id")), cart);
            if (cart.get(index).getQuantity() > 1) {
                int quantity = cart.get(index).getQuantity();
                cart.get(index).setQuantity(quantity - 1);
            } else {
                cart.remove(index);
            }
            session.setAttribute("cart", cart);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }

    private int isExisting(int id, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getBook().getId() == id)
                return i;
        }
        return -1;
    }
}
