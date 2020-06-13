package Controller;

import DAO.CustomerDAO;
import Model.CustomerBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerController extends HttpServlet {

    String list = "views/List.jsp";
    String add = "views/Add.jsp";
    String edit = "views/Edit.jsp";
    String viewAccess = "";

    CustomerBean customerBean;
    CustomerDAO dao = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if(action.equalsIgnoreCase("list")) {
            //show listAll view
            viewAccess = list;
        }else if(action.equalsIgnoreCase("add")) {
            //Shows add view form
            viewAccess = add;
        }else if(action.equalsIgnoreCase("Agregar")) {
            //process add request

            dao.add(feedObjectFromRequest(request));

            viewAccess = list;

        }else if(action.equalsIgnoreCase("edit")) {
            //Shows edit view form

        }else if(action.equalsIgnoreCase("update")) {
            //process update request

        }else if(action.equalsIgnoreCase("delete")) {
            //process delete request

        }
        RequestDispatcher view = request.getRequestDispatcher(viewAccess);
        view.forward(request, response);
    }

    private CustomerBean feedObjectFromRequest(HttpServletRequest request) {

        CustomerBean customer = new CustomerBean();

        customer.setPhoto(request.getParameter("photo"));
        customer.setName(request.getParameter("inputName"));
        customer.setLastName(request.getParameter("inputLastName"));
        customer.setAge(Byte.parseByte(request.getParameter("inputAge")));
        customer.setProfession(request.getParameter("inputProfession"));
        customer.setAddress(request.getParameter("inputAddress"));
        customer.setLevelOfSatisfaction(request.getParameter("inputSatsLevel"));
        customer.setGender(request.getParameter("inputGender").charAt(0));
        customer.setHobbies(request.getParameter("checkHobbies"));
        customer.setFavoriteColor(request.getParameter("inputColor"));

        return customer;
    }
}
