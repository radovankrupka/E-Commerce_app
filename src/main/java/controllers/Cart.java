package controllers;

import DAO.CartItemDAO;
import DAO.TovarDAO;
import model.CartItem;
import model.Tovar;
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

@WebServlet("/cart")
public class Cart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null){
            System.out.println("zobrazujem kosik pre pouzivatela" + session.getAttribute("user").toString());

            //ziskaj vsetky Tovar - i z tab. kosik kde id_usera == session.user.id

            User currentUser = (User) session.getAttribute("user");

            List<CartItem> list = new ArrayList<>();
            list = CartItemDAO.getAllProductsFromCart(currentUser);  //sem sa itemy dostanu


            //napln cart itemy objektami Tovar pre kazdy typ tovvaru
            list.stream().forEach(cartItem -> cartItem.setTovar(TovarDAO.getItemById(cartItem.getId_tovaru())));

            /*List<Tovar> tovarList = new ArrayList<>();

            list.stream().forEach(cartItem -> tovarList.add(TovarDAO.getItemById(cartItem.getId_tovaru())));*/

            list.stream().forEach(cartItem -> toString());


             // might need change to .getsession().getattr
            request.setAttribute("cartItems", list);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cart.jsp");
            dispatcher.forward(request,response);



        }
    }
}
