package controllers;

import DAO.OrderDAO;
import DAO.OrderItemDAO;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UpdateOrder")
public class UpdateOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null && ((User) session.getAttribute("user")).isJe_admin()) {

            int orderID = Integer.parseInt(request.getParameter("updateID"));
            String status = request.getParameter("orderStatus");
            System.out.println("update stavu obj " + orderID + " na > " + status);
            OrderDAO.updateOrderById(orderID,status, session );


            RequestDispatcher dispatcher = request.getRequestDispatcher("ManageOrders");
            dispatcher.forward(request,response);

        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
        }
    }


}