package controllers;


import DAO.UserDAO;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String operacia = request.getParameter("operacia");
        if (operacia == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
        }

        if (operacia.equals("login")) {
            if (UserDAO.checkIfUserExists(request.getParameter("email"), request.getParameter("password"))) {
                // USPESNE PRIHLASENIE
                User user = UserDAO.getUserByNick(request.getParameter("email"));
                session.setAttribute("user", user);


                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home-page.jsp");
                dispatcher.forward(request,response);




            } else {
                //wrong credentials
                System.out.println("wrong cred + |" + operacia + "|");
                System.out.println("|" + request.getParameter("email") + "|" + request.getParameter("password")+"|");
                session.invalidate();
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request,response);
            }
        } else if (operacia.equals("logout")){
            request.getSession().invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
        }


    }
}
