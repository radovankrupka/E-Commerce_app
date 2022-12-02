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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String operacia = request.getParameter("operacia");
        Map<String, String> messages = new HashMap<>();
        request.setAttribute("messages", messages);

        if (operacia == null && session.getAttribute("user") == null ) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }

        if (operacia != null && operacia.equals("register") && session.getAttribute("user") == null) {

            if (UserDAO.checkUserByLogin(request.getParameter("email"))) { // uzivatel s danym emailom uz existuje
                messages.put("error", "Chyba -> Pouzivatel s tymto emailom uz existuje!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.forward(request, response);

            }
            else {

                User user = new User();

                user.setLogin(request.getParameter("email"));
                user.setPwd(request.getParameter("password"));
                user.setAdresa(request.getParameter("address"));
                user.setJe_admin(false);
                user.setMeno(request.getParameter("surrname"));
                user.setPriezvisko("last_name");
                user.setZlava(15);

                UserDAO.createUser(user);

                user.setId(UserDAO.getUserByLogin(user.getLogin()).getId()); //nastavim objektu usera ID z db, ktore mu bolo pridelene

                session.setAttribute("user",user);

                response.sendRedirect("home");
            }
        }
    }
}
