<%@ page import="DAO.CustomerDAO" %>
<%@ page import="Model.CustomerBean" %><%--
  Created by IntelliJ IDEA.
  User: 7440
  Date: 6/12/2020
  Time: 9:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    </head>
    <body style="background-color: #595757">
        <div class="container-fluid">
            <div class="row" style="margin-top: 25px">
                <div class="col-md-12 col-md-offset-2">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between align-items-center">
                            <h1>Editar Cliente</h1>
                            <a class="btn btn-info btn-lg" href="CustomerController?action=list">Volver</a>
                        </div>
                        <%
                            CustomerDAO dao = new CustomerDAO();
                            int id = Integer.parseInt((String) request.getAttribute("customerId"));
                            CustomerBean customer = dao.listById(id);
                        %>
                        <div class="card-body">
                            <form action="CustomerController"> <!-- method = "post" enctype = "multipart/form-data" -->
                                <div class="form-row">
                                    <input type="number" readonly value="<%= customer.getId() %>" hidden="true" name="inpId">
                                    <div class="form-group col-md-3">
                                        <label for="inputProfession"><b>Profesion :</b></label>
                                        <select id="inputProfession" name="inputProfession" class="form-control" required>
                                            <option selected>Elegir...</option>
                                            <option>Developer</option>
                                            <option>QA</option>
                                            <option>Accountant</option>
                                            <option>Pilot</option>
                                            <option>Other</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label for="inputName"><b>Nombre(s) :</b></label>
                                        <input type="text" value="<%= customer.getName() %>" class="form-control" id="inputName" name="inputName" minlength="3" maxlength="20" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="inputLastName"><b>Apellido(s) :</b></label>
                                        <input type="text" value="<%= customer.getLastName() %>" class="form-control" id="inputLastName" name="inputLastName" required minlength="5" maxlength="40">
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label for="inputBirthDay"><b>Cumpleaños :</b></label>
                                        <input type="date" class="form-control" id="inputBirthDay" name="inputBirthDay"  min="1920-01-01" max="2002-12-31" required>
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group col-md-3">
                                        <label for="inputPhoto"><b>Foto :</b></label><br>
                                        <img src="#" class="img-thumbnail"> <!--{pageContext.request.contextPath}/assets/photos/3.jpg -->
                                        <input type="file" class="form-control-file" id="inputPhoto" name="inputPhoto">
                                    </div>

                                    <div class="form-group col-md-7">
                                        <label for="inputAddress"><b>Direccion :</b></label>
                                        <textarea class="form-control" id="inputAddress" name="inputAddress" rows="4" minlength="5"><%= customer.getAddress() %> </textarea>
                                    </div>

                                    <div class="form-group col-md-2">
                                        <label><b>Pasatiempos :</b></label>
                                        <div class="form-group form-check">
                                            <input type="checkbox" value="Dormir" name="checkHobbiesList" class="form-check-input" id="check">
                                            <label class="form-check-label" for="check">Dormir</label>
                                        </div>
                                        <div class="form-group form-check">
                                            <input type="checkbox" value="Correr" name="checkHobbiesList" class="form-check-input" id="check1">
                                            <label class="form-check-label" for="check1">Correr</label>
                                        </div>
                                        <div class="form-group form-check">
                                            <input type="checkbox" value="Comer" name="checkHobbiesList" class="form-check-input" id="check2">
                                            <label class="form-check-label" for="check2">Comer</label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-row">

                                    <div class="col-md-3"></div>

                                    <div class="form-group col-md-2">
                                        <label><b>Genero :</b></label>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="inputGender" id="inputGender1" value="M" checked>
                                            <label class="form-check-label" for="inputGender1">
                                                Hombre
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="inputGender" id="inputGender2" value="F">
                                            <label class="form-check-label" for="inputGender2">
                                                Mujer
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="inputGender" id="inputGender3" value="I">
                                            <label class="form-check-label" for="inputGender3">
                                                Perfiero no decir
                                            </label>
                                        </div>
                                    </div>

                                    <div class="form-group col-md-2">
                                        <label for="inputSatsLevel"><b>Nivel de satisfaccion :</b></label>
                                        <input type="range" class="form-control-range" value="<%= customer.getLevelOfSatisfaction() %>" id="inputSatsLevel" name="inputSatsLevel">
                                    </div>

                                    <div class="col-md-1"></div>

                                    <div class="form-group col-md-2">
                                        <label for="inputColor"><b>Color favorito:</b></label>
                                        <input type="color" class="form-control" value="<%= customer.getFavoriteColor() %>" id="inputColor" name="inputColor">
                                    </div>

                                    <div class="col-md-2"></div>

                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <input type="submit" class="btn btn-primary btn-block" name="action" value="actualizar">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </body>
</html>