package controllers;


import DAO.TovarDAO;
import DAO.UserDAO;
import model.Tovar;
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
import java.util.concurrent.ScheduledExecutorService;

public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String operacia = request.getParameter("operacia");

        //nie som lognuty, nechcem sa lognut
        if (operacia == null && session.getAttribute("user") == null  ) {
            System.out.println("CHYBA, OPERACIA = null");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
        }

        //som lognuty, chcem zobrazit stranku
/*
        if (UserDAO.checkIfUserExists(   ((User)session.getAttribute("user") ).getLogin(),((User)session.getAttribute("user") ).getPwd()   )){
*/
            if (session.getAttribute("user") != null){

            List<Tovar> tovarList =  TovarDAO.getAllProducts();
            tovarList.stream().forEach(System.out::println);
            request.getSession().setAttribute("tovarList", tovarList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home-page.jsp");
            dispatcher.forward(request,response);

        }

        //nie som lognuty, chcem sa lognut
        if (operacia.equals("login") && session.getAttribute("user") == null ) {// toto tereba poriesit
            System.out.println("email : " + request.getParameter("email") + " heslo :  " + request.getParameter("password"));
            if (UserDAO.checkIfUserExists(request.getParameter("email"), request.getParameter("password"))){
                // USPESNE PRIHLASENIE
                User user = UserDAO.getUserByLogin(request.getParameter("email"));
                session.setAttribute("user", user);

                List<Tovar> tovarList =  TovarDAO.getAllProducts();
                tovarList.stream().forEach(System.out::println);
                request.getSession().setAttribute("tovarList", tovarList);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home-page.jsp");
                dispatcher.forward(request,response);

            } else { //prihlasenie nevyslo / zle data
                //wrong credentials
                System.out.println("wrong cred + |" + operacia + "|");
                System.out.println("|" + request.getParameter("email") + "|" + request.getParameter("password")+"|");
                session.invalidate();
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request,response);
            }
        }



            // chcem sa odhlasit
        if (operacia != null && operacia.equals("logout")){
            request.getSession().invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
        }

        System.out.println("nic?");
    }
}
