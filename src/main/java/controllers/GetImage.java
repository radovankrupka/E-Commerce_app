package controllers;


import DAO.ArticleDAO;
import DAO.ImageDAO;
import DAO.OrderDAO;
import DAO.OrderItemDAO;
import model.Image;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/GetImage")
public class GetImage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {

            int articleId = Integer.parseInt(request.getParameter("articleId"));
            Image image = getImage(articleId,session,request,response);


            System.out.println("idem zobrazit img s ID : " + articleId);

            response.setContentType(request.getServletContext().getMimeType(image.getImg_name()));
            response.setContentLength(image.getImg_data().length);
            response.getOutputStream().write(image.getImg_data());

        /*    RequestDispatcher dispatcher = request.getRequestDispatcher("ManageOrders");
            dispatcher.forward(request,response);*/

        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
        }
    }

    private Image getImage(int articleID, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        return ImageDAO.getImageById(session, articleID, request, response);
    }


}