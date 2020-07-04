package Controller;

import DAO.CustomerDAO;
import Model.CustomerBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class CustomerController extends HttpServlet {

    String list = "views/List.jsp";
    String add = "views/Add.jsp";
    String edit = "views/Edit.jsp";
    String viewAccess = "";

    CustomerBean customerBean;
    CustomerDAO dao = new CustomerDAO();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

    }

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
            dao.add(feedObjectFromRequest(request, false));

            viewAccess = list;

        }else if(action.equalsIgnoreCase("edit")) {
            //Shows edit view form
            request.setAttribute("customerId", request.getParameter("id"));

            viewAccess = edit;

        }else if(action.equalsIgnoreCase("actualizar")) {
            //process update request
            int id = Integer.parseInt(request.getParameter("inpId"));

            CustomerBean editCustomer;

            editCustomer = feedObjectFromRequest(request, true);
            editCustomer.setId(id);

            dao.edit(editCustomer);

            viewAccess = list;

        }else if(action.equalsIgnoreCase("delete")) {
            //process delete request
            int id = Integer.parseInt(request.getParameter("id"));

            dao.delete(id);

            viewAccess = list;
        }
        RequestDispatcher view = request.getRequestDispatcher(viewAccess);
        view.forward(request, response);
    }

    private CustomerBean feedObjectFromRequest(HttpServletRequest request, boolean edit) {

        CustomerBean customer = new CustomerBean();

        if(edit){
            //customer.setPhoto(getSavedServerPhotoUrl(request, new File(request.getParameter("inputPhoto"))));
            customer.setPhoto(null);
            customer.setName(request.getParameter("inputName"));
            customer.setLastName(request.getParameter("inputLastName"));
            customer.setAge(getAgeFromBirthDay(request.getParameter("inputBirthDay")));
            customer.setProfession(null);//request.getParameter("inputProfession")
            customer.setAddress(request.getParameter("inputAddress"));
            customer.setLevelOfSatisfaction(request.getParameter("inputSatsLevel"));
            customer.setGender('M');//request.getParameter("inputGender").charAt(0)
            customer.setHobbies(null);//getCheckedHobbies(request.getParameterValues("checkHobbiesList"))
            customer.setFavoriteColor(request.getParameter("inputColor"));

        }else{
            //customer.setPhoto(getSavedServerPhotoUrl(request, new File(request.getParameter("inputPhoto"))));
            customer.setPhoto(null);
            customer.setName(request.getParameter("inputName"));
            customer.setLastName(request.getParameter("inputLastName"));
            customer.setAge(getAgeFromBirthDay(request.getParameter("inputBirthDay")));
            customer.setProfession(request.getParameter("inputProfession"));//request.getParameter("inputProfession")
            customer.setAddress(request.getParameter("inputAddress"));
            customer.setLevelOfSatisfaction(request.getParameter("inputSatsLevel"));
            customer.setGender(request.getParameter("inputGender").charAt(0));//request.getParameter("inputGender").charAt(0)
            customer.setHobbies(getCheckedHobbies(request.getParameterValues("checkHobbiesList")));//getCheckedHobbies(request.getParameterValues("checkHobbiesList"))
            customer.setFavoriteColor(request.getParameter("inputColor"));
        }
        return customer;
    }

    private String getCheckedHobbies(String[] checkHobbiesList) {

        List<String> checkedHobbies = new ArrayList<>();

        for (String myCheckBoxValue : checkHobbiesList) {

            if (myCheckBoxValue != null) // if is there, it means checkbox checked
                checkedHobbies.add(myCheckBoxValue);
        }
        return checkedHobbies.toString();
    }

    private byte getAgeFromBirthDay(String inputBirthDay) {
        LocalDate now = LocalDate.now();
        LocalDate birthDay = LocalDate.parse(inputBirthDay);

        Period period = Period.between(birthDay, now);
        int diff = period.getYears();

        return (byte) diff;
    }
}
