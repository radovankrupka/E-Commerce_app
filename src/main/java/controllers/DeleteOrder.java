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

@WebServlet("/DeleteOrder")
public class DeleteOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null && ((User) session.getAttribute("user")).isJe_admin()) {

            int orderID = Integer.parseInt(request.getParameter("deleteID"));

            OrderDAO.deleteOrderById(orderID, session);

            OrderItemDAO.deleteOrderItemsByOrderId(orderID,session);

            RequestDispatcher dispatcher = request.getRequestDispatcher("ManageOrders");
            dispatcher.forward(request,response);

        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
        }
    }


}