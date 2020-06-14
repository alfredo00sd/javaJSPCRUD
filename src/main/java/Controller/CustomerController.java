package Controller;

import DAO.CustomerDAO;
import Model.CustomerBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerController extends HttpServlet {

    String list = "views/List.jsp";
    String add = "views/Add.jsp";
    String edit = "views/Edit.jsp";
    String viewAccess = "";

    CustomerBean customerBean;
    CustomerDAO dao = new CustomerDAO();

    private final String PHOTO_TYPE = ".jpg";
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private boolean isMultipart;
    private String filePath;
    private File file ;

    public void init( ){
        // Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

        // Check that we have a file upload request
        //isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter( );

//        if( !isMultipart ) {
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet upload</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<p>No data uploaded</p>");
//            out.println("</body>");
//            out.println("</html>");
//            return;
//        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        upload.setSizeMax( maxFileSize );

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");

            while ( i.hasNext () ) {
                FileItem fi = (FileItem)i.next();
                if ( !fi.isFormField () ) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    // Write the file
                    if( fileName.lastIndexOf("\\") >= 0 ) {
                        file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
                    } else {
                        file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                    }
                    fi.write( file ) ;
                    out.println("Uploaded Filename: " + fileName + "<br>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        } catch(Exception ex) {
            System.out.println(ex);
        }
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

    private String getSavedServerPhotoUrl(HttpServletRequest request, File uploadedFile) {

        Path folder = Paths.get(request.getContextPath()+"/assets/photos/");
        String filename = request.getParameter("inputName") +"00"+dao.getLastId();
        Path file = null;

        try (InputStream input = new FileInputStream(uploadedFile)) {

            file = Files.createTempFile(folder, filename, PHOTO_TYPE);
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return folder + filename + PHOTO_TYPE;
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
