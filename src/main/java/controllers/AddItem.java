package controllers;


import DAO.CartItemDAO;
import DAO.ArticleDAO;
import model.Article;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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
            System.out.println("pridavam article " +request.getParameter("itemToAddId"));

            Article article = ArticleDAO.getItemById(Integer.parseInt(request.getParameter("itemToAddId")), session);
            User user = (User) session.getAttribute("user");

                 CartItemDAO.addItemToCart(article, user.getId(), Integer.parseInt(request.getParameter("numOfItems")),session );

        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("home");
        dispatcher.forward(request,response);
    }
}
