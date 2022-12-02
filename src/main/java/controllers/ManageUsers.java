package controllers;

import DAO.OrderDAO;
import DAO.UserDAO;
import model.Order;
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

@WebServlet("/ManageUsers")
public class ManageUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null && ((User) session.getAttribute("user")).isJe_admin()) {

            List<User> userList = new ArrayList<>();
            userList = UserDAO.getAllUsers();
            request.setAttribute("userList",userList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/order-manage.jsp");
            dispatcher.forward(request,response);


        }
    }
}
