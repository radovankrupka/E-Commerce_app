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

        /*//nie som lognuty, nechcem sa lognut
        if (operacia == null && session.getAttribute("user") == null  ) {
            System.out.println("CHYBA, OPERACIA = null");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
        }

        //som lognuty, chcem zobrazit stranku
*//*
        if (UserDAO.checkIfUserExists(   ((User)session.getAttribute("user") ).getLogin(),((User)session.getAttribute("user") ).getPwd()   )){
*//*
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

        System.out.println("nic?");*/


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
