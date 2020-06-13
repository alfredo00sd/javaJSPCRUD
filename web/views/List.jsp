<%@ page import="DAO.CustomerDAO" %>
<%@ page import="Model.CustomerBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: 7440
  Date: 6/12/2020
  Time: 9:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>List</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    </head>
    <body style="background-color: #595757">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12 col-md-offset-2">
                    <div class="card">
                        <div class="card-body d-flex justify-content-between align-items-center">
                            <h1>Clientes agregados</h1>
                            <a class="btn btn-primary btn-lg" href="CustomerController?action=add">Agregar nuevo</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 col-md-offset-2">
                    <table class="table table-dark table-responsive">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Foto</th>
                                <th>Nombre(s)</th>
                                <th>Apellido(s)</th>
                                <th>Edad</th>
                                <th>Profesion</th>
                                <th>Genero</th>
                                <th>Hobbies</th>
                                <th>Direccion</th>
                                <th>Nivel de satisfaccion</th>
                                <th>Color favorito(s)</th>
                                <th>Accion</th>
                            </tr>
                        </thead>
                        <%
                            CustomerDAO dao  = new CustomerDAO();
                            List<CustomerBean> list = dao.list();
                            Iterator<CustomerBean> iterator = list.iterator();

                            CustomerBean customer;

                            while(iterator.hasNext()) {
                                customer = iterator.next();
                        %>
                        <tbody>
                            <tr>
                                <td><%= customer.getId() %></td>
                                <td><img class="img-thumbnail" src="${pageContext.request.contextPath}/assets/photos/3.jpg"></td> <!-- <% //customer.getPhoto() %> -->
                                <td><%= customer.getName() %></td>
                                <td><%= customer.getLastName() %></td>
                                <td><%= customer.getAge() %></td>
                                <td><%= customer.getProfession() %></td>
                                <td><%= customer.getGender() %></td>
                                <td><%= customer.getHobbies() %></td>
                                <td><%= customer.getAddress() %></td>
                                <td><%= customer.getLevelOfSatisfaction() %></td>
                                <td><div style="padding:5px;background-color: <%= customer.getFavoriteColor() %>;"></div> </td>
                                <td>
                                    <a class="btn btn-warning" href="CustomerController?action=edit&id=<%= customer.getId() %>">Editar</a>
                                    <a class="btn btn-danger" href="CustomerController?action=delete&id=<%= customer.getId() %>">Eliminar</a>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </body>
</html>
