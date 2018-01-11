package servlets;

import products.Item;
import storage.StoreGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Servlet class. Add to cart and delete from it.
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {

    private StoreGenerator store = new StoreGenerator();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Method reacts if client send get request
     *
     * @param request  httpservletrequest
     * @param response httpservletresponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action"); //parameter
        HttpSession session = request.getSession(); // get session
        // ordernow
        if (action.equals("ordernow")) {
            // if there is no cart. Create new.
            if (session.getAttribute("cart") == null) {
                CopyOnWriteArrayList<Item> cart = new CopyOnWriteArrayList<Item>();
                // add new Item in cart
                cart.add(new Item(store.getStore().get(Integer.parseInt(request.getParameter("id"))), 1));
                // set attribute
                session.setAttribute("cart", cart);
            }
            // if cart already exists
            else {
                // get cart from session
                CopyOnWriteArrayList<Item> cart = (CopyOnWriteArrayList<Item>) session.getAttribute("cart");
                // get adding book index in cart
                int index = isExisting(Integer.parseInt(request.getParameter("id")), cart);
                // if (index == -1) it don't exists in cart
                if (index == -1) {
                    // add new Item in cart
                    cart.add(new Item(store.getStore().get(Integer.parseInt(request.getParameter("id"))), 1));
                    session.setAttribute("cart", cart);
                }
                // If exists
                else {
                    // get book's quantity,  increase quantity by one
                    int quantity = cart.get(index).getQuantity() + 1;
                    // set quantity
                    cart.get(index).setQuantity(quantity);
                }
            }
            // send to cart.jsp
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
        // delete
        else if (action.equals("delete")) {
            CopyOnWriteArrayList<Item> cart = (CopyOnWriteArrayList<Item>) session.getAttribute("cart");
            //get deleting book index in cart
            int index = isExisting(Integer.parseInt(request.getParameter("id")), cart);
            // decrease deleting book quantity if it > 1
            if (cart.get(index).getQuantity() > 1) {
                int quantity = cart.get(index).getQuantity();
                cart.get(index).setQuantity(quantity - 1);
            }
            // delete book if quantity == 1
            else {
                cart.remove(index);
            }
            // set cart like attribute
            session.setAttribute("cart", cart);
            // send to cart.jsp
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }

    /**
     * Check if added Book already exists in cart.
     *
     * @param id book's id
     * @param cart cart
     * @return -1 if there is no id, return book index at CopyOnWriteArrayList if there is
     */
    private int isExisting(int id, CopyOnWriteArrayList<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getBook().getId() == id)
                return i;
        }
        return -1;
    }
}
