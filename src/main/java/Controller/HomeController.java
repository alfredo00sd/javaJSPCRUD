package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController extends HttpServlet {

    //String viewAccess = "";
    String home = "views/Home.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        request.setAttribute("pageTitle", "Home");

        request.getRequestDispatcher(home).forward(request, response);
//        RequestDispatcher view = request.getRequestDispatcher(home);
//        view.forward(request, response);
    }
}
