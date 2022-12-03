package controllers;


import DAO.ArticleDAO;
import DAO.UserDAO;
import config.DBConnection;
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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/main")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String operacia = request.getParameter("operacia");


        if ( session.getAttribute("user") != null && operacia == null && (!((User)session.getAttribute("user")).isJe_admin()) ) {//user uz je v session = je prihlaseny, nechce nic konkretne

            System.out.println("zobraz main page");
            zobrazMainPage(session, request, response);

        }
        if ( session.getAttribute("user") != null && operacia == null && ((User)session.getAttribute("user")).isJe_admin() ) {//user uz je v session = je prihlaseny, nechce nic konkretne

            System.out.println("zobraz admin page");
            zobrazAdminPage(request, response);

        }

        if ( session.getAttribute("user") == null ) { //ak nemam usera, musi sa prihlasit

            if (UserDAO.checkIfUserExists(request.getParameter("email"), request.getParameter("password"),session)) { //over credentials

                User user = UserDAO.getUserByLogin(request.getParameter("email"),session);
                session.setAttribute("user", user);
                DBConnection.createConnection(session);

                System.out.println("uspesne prihlasenie, zobraz main page");
                operacia = null;
                request.removeAttribute("operacia");

                if ((!((User)session.getAttribute("user")).isJe_admin()))
                    zobrazMainPage(session, request, response);
                else {
                    //zobraz admin page
                    zobrazAdminPage(request, response);
                }



            }
            else {  //nespravne credentials
                System.out.println("neopravneny vstup, prihlas sa");
                zobrazLoginPage(request,response);

            }

        }

        if (session.getAttribute("user") != null && operacia.equals("logout")){ //mam usera, chcem sa odhlasit

            System.out.println("idem odhlasit");
            try {
                odhlasUsera(request,response,session);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }

    private void zobrazAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/admin-page.jsp");
        dispatcher.forward(request,response);
    }

    private void odhlasUsera(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException, SQLException {

        DBConnection.getConnection(session).close();
        request.getSession().invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request,response);

    }

    private void zobrazLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request,response);
    }

    private void zobrazMainPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Article> articleList =  ArticleDAO.getAllProducts(session);
        articleList.stream().forEach(System.out::println);
        session.setAttribute("articleList", articleList);

        System.out.println(session.getAttribute("exception"));


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home-page.jsp");
        dispatcher.forward(request, response);

    }
}
