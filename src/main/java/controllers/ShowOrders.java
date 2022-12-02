package controllers;

import DAO.OrderDAO;
import model.Order;

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

@WebServlet("/ShowOrders")
public class ShowOrders extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null){


            List<Order> orderList = new ArrayList<>();
            orderList = OrderDAO.getAllOrdersOfUser(session);
            request.setAttribute("orderList",orderList);


            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/order-list.jsp");
            dispatcher.forward(request,response);
        }
    }
}
