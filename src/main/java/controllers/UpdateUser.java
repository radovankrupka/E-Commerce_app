package controllers;

import DAO.UserDAO;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null && ((User) session.getAttribute("user")).isJe_admin()) {

           if (request.getParameter("action") != null && request.getParameter("action").equals("changeRole")){
               int userID = Integer.parseInt(request.getParameter("userID"));

               UserDAO.changeUserRole(userID,session);

           }
           if (request.getParameter("action") != null && request.getParameter("action").equals("updateDisc")){
               int userID = Integer.parseInt(request.getParameter("userID"));
               int discount = Integer.parseInt(request.getParameter("userDisc"));

              UserDAO.updateDiscount(userID,discount,session);


           }

            RequestDispatcher dispatcher = request.getRequestDispatcher("ManageUsers");
            dispatcher.forward(request,response);


        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
        }
    }
}