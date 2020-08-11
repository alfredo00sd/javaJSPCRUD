<%@ page import="DAO.CustomerDAO" %>
<%@ page import="Model.CustomerBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <title>List</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../css/customStyle.css">
    </head>
    <body>
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
            <hr>
            <div class="row">
                <div class="col-md-12 col-md-offset-2">
                    <%
                        CustomerDAO dao  = new CustomerDAO();
                        List<CustomerBean> list = dao.list();
                        Iterator<CustomerBean> iterator = list.iterator();

                        CustomerBean customer;

                        while(iterator.hasNext()) {
                            customer = iterator.next();
                    %>
                        <div class="card mb-auto">
                            <div class="row no-gutters">
                                <div class="col-md-4">
                                    <img class="img-thumbnail" src="${pageContext.request.contextPath}/assets/icons/<%= customer.getPhoto() %>.svg" alt="avatar"/>
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title">Detalle de cliente #00<%= customer.getId() %></h5>
                                        <p class="card-text"><b>Nombre(s) : </b> <%= customer.getName() %> <b>Apellido(s) :</b> <%= customer.getLastName() %> <b>Edad :</b> <%= customer.getAge() %></p>
                                        <p class="card-text"><b>Profesion : </b> <%= customer.getProfession() %> <b>Hobbies : </b> <%= customer.getHobbies() %></p>
                                        <p class="card-text"><b>Genero : </b><%= customer.getGender() %></p>
                                        <p class="card-text"><b>Direccion : </b><%= customer.getAddress() %></p>
                                        <p class="card-text"><b>Nivel de satisfaccion (0/100) : </b><%= customer.getLevelOfSatisfaction() %></p>
                                        <p class="card-text"><b>Color favorito : </b><div style="padding:8px;background-color: <%= customer.getFavoriteColor() %>;"></div>
                                        <p class="card-text"><small class="text-muted">Last updated 3 mins ago...</small></p>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between align-items-center">
                                        <a class="btn btn-warning" href="CustomerController?action=edit&id=<%= customer.getId() %>">Editar</a>
                                        <a class="btn btn-danger" href="CustomerController?action=delete&id=<%= customer.getId() %>">Eliminar</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                    <% } %>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </body>
</html>
