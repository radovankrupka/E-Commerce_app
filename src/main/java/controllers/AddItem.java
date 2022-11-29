package controllers;


import DAO.CartItemDAO;
import DAO.TovarDAO;
import DAO.UserDAO;
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


@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();



        if (session.getAttribute("user") != null){
            System.out.println("pridavam tovar " +request.getParameter("itemToAddId"));

            Tovar tovar = TovarDAO.getItemById(Integer.parseInt(request.getParameter("itemToAddId")));
            User user = (User) session.getAttribute("user");

                 CartItemDAO.addItemToCart(tovar, user.getId(), Integer.parseInt(request.getParameter("numOfItems")) );

        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("home");
        dispatcher.forward(request,response);
    }
}
