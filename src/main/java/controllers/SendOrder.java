package controllers;

import DAO.ArticleDAO;
import DAO.CartItemDAO;
import DAO.OrderDAO;
import model.CartItem;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/SendOrder")
public class SendOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<CartItem> cartItems = new ArrayList<>();



        if (session.getAttribute("user") != null) {
            cartItems = CartItemDAO.getAllProductsFromCart((User) session.getAttribute("user"));

            System.out.println("\n\n\n\n Ideme odoslat objednavku \n\n\n");
            cartItems.stream().forEach(System.out::println);
            System.out.println("\n\n\n\n");

            boolean flag = true;
            ArrayList<Integer> article_ids = new ArrayList<>();

            try {
                synchronized (this) {
                    // skontrolujem, ci je na sklade dost tovaru


                    for (CartItem cartItem : cartItems) {
                        //zistime kolko ks je v kosiku a kolko ks je na sklade, porovname
                        //numOfItems je 0 pretoze chceme objednat len pocet ktoty mame v kosiku
                        if (!(CartItemDAO.enoughItemsInStore(cartItem.getArticle().getId(), 0, ((User) session.getAttribute("user")).getId()))) {
                            //ak niektoreho z tovarov nie je v sklade dost, otoc flag
                            flag = false;
                            article_ids.add(cartItem.getId());
                        }
                    }
                        if (flag == false) {
                            // niektory z tovarov, s id article_id, nie je dostatocne naskladneny
                            System.out.println("nedostatok tovaru na sklade");
                            System.out.println("chybne polozky: ");
                            article_ids.stream().forEach(System.out::println);

                        } else {
                            //vsetky tovary su v dostatocnom pocte

                            System.out.println("USKUTOCNUJEME OBJEDNAVKU");
                            OrderDAO.orderItemsFromCart(session, cartItems);

                            //odcitat nakupeny tovar zo skladovych zasob
                            OrderDAO.decreaseStockCount(cartItems);




                            //nasledne treba vymazat obsah kosika pre daneho usera
                            CartItemDAO.clearCartForUser(session);

                            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/thank-you.jsp");
                            dispatcher.forward(request,response);



                        }

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
