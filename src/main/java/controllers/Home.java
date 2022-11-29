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


        if ( session.getAttribute("user") != null   ) {//user uz je v session = je prihlaseny

            System.out.println("zobraz main page");
            zobrazMainPage(session, request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home-page.jsp");
            dispatcher.forward(request,response);

        }

        if ( session.getAttribute("user") == null && operacia.equals("login")  ) {

            if (UserDAO.checkIfUserExists(request.getParameter("email"), request.getParameter("password"))) { //over credentials

                User user = UserDAO.getUserByLogin(request.getParameter("email"));
                session.setAttribute("user", user);

                System.out.println("uspesne prihlasenie, zobraz main page");
                zobrazMainPage(session, request, response);



            }
            else {  //nespravne credentials

                zobrazLoginPage(request,response);

            }

        }

        if ( session.getAttribute("user") == null && operacia == null  ) { // neopravneny pristup

            zobrazLoginPage(request,response);

        }



    }

    private void zobrazLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("neopravneny vstup, prihlas sa");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request,response);
    }

    private void zobrazMainPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Tovar> tovarList =  TovarDAO.getAllProducts();
        tovarList.stream().forEach(System.out::println);
        session.setAttribute("tovarList", tovarList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home-page.jsp");
        dispatcher.forward(request, response);

    }
}
