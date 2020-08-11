<%@ page import="DAO.CustomerDAO" %>
<%@ page import="Model.CustomerBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customStyle.css">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row" style="margin-top: 25px">
                <div class="col-md-12 col-md-offset-2">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between align-items-center">
                            <h1>Editing</h1>
                            <a class="btn btn-warning btn-lg" href="CustomerController?action=list">Cancel</a>
                        </div>
                        <%
                            CustomerDAO dao = new CustomerDAO();
                            int id = Integer.parseInt((String) request.getAttribute("customerId"));
                            CustomerBean customer = dao.listById(id);
                        %>
                        <div class="card-body">
                            <form action="CustomerController" method = "post">
                                <div class="form-row">
                                    <input type="number" readonly value="<%= customer.getId() %>" hidden name="inpId">

                                    <div class="form-group col-md-3">
                                        <label for="inputName"><b>Name(s) :</b></label>
                                        <input type="text" value="<%= customer.getName() %>" class="form-control" id="inputName" name="inputName" minlength="3" maxlength="20" required>
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label for="inputLastName"><b>LastName(s) :</b></label>
                                        <input type="text" value="<%= customer.getLastName() %>" class="form-control" id="inputLastName" name="inputLastName" required minlength="5" maxlength="40">
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label for="inputBirthDay"><b>BirthDay :</b></label>
                                        <input type="date" value="<%= customer.getBirthDay() %>" class="form-control" id="inputBirthDay" name="inputBirthDay"  min="1940-01-01" max="2002-12-31" required>
                                    </div>

                                    <div class="form-group col-md-3">
                                        <label for="inputProfession"><b>Team role :</b></label>
                                        <select id="inputProfession" name="inputProfession" class="form-control" required>
                                            <option selected>Choose ...</option>
                                            <option>Developer</option>
                                            <option>Programmer</option>
                                            <option>Full-stack</option>
                                            <option>Back-End</option>
                                            <option>Front-End</option>
                                            <option>SysAdmin</option>
                                            <option>SysDBA</option>
                                            <option>QA</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-row">

                                    <div class="form-group col-md-6">
                                        <label for="inputAddress"><b>Address :</b></label>
                                        <textarea class="form-control" id="inputAddress" name="inputAddress" rows="4" minlength="5"><%= customer.getAddress() %></textarea>
                                    </div>

                                    <div class="form-group col-md-3">
                                        <label><b>Hobbies :</b></label>
                                        <div class="form-group form-check">
                                            <input type="checkbox" value="sleep" name="checkHobbiesList" class="form-check-input" id="check">
                                            <label class="form-check-label" for="check">Sleep</label>
                                        </div>
                                        <div class="form-group form-check">
                                            <input type="checkbox" value="run" name="checkHobbiesList" class="form-check-input" id="check1">
                                            <label class="form-check-label" for="check1">Run</label>
                                        </div>
                                        <div class="form-group form-check">
                                            <input type="checkbox" value="eat" name="checkHobbiesList" class="form-check-input" id="check2">
                                            <label class="form-check-label" for="check2">Eat</label>
                                        </div>
                                        <div class="form-group form-check">
                                            <input type="checkbox" value="codding" name="checkHobbiesList" class="form-check-input" id="check3">
                                            <label class="form-check-label" for="check3">Codding</label>
                                        </div>
                                        <div class="form-group form-check">
                                            <input type="checkbox" value="Find sense to life" name="checkHobbiesList" class="form-check-input" id="check4">
                                            <label class="form-check-label" for="check4">Find sense to life</label>
                                        </div>
                                    </div>

                                    <div class="form-group col-md-3">
                                        <label><b>Gender :</b></label>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="inputGender" id="inputGender1" value="M" checked>
                                            <label class="form-check-label" for="inputGender1">
                                                Male
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="inputGender" id="inputGender2" value="F">
                                            <label class="form-check-label" for="inputGender2">
                                                Female
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="inputGender" id="inputGender3" value="I">
                                            <label class="form-check-label" for="inputGender3">
                                                Prefer not to say...
                                            </label>
                                        </div>
                                    </div>

                                </div>

                                <div class="form-row">

                                    <div class="form-group col-md-6">
                                        <label><b>Choose avatar :</b></label><br>
                                        <label class="form-image">
                                            <input type="radio" class="form-control image-radio" name="avatar" value="1" checked>
                                            <img class="img-thumbnail" src="${pageContext.request.contextPath}/icons/1.svg" alt="avatar"/>
                                        </label>
                                        <label class="form-image">
                                            <input type="radio" class="form-control image-radio" name="avatar" value="2">
                                            <img class="img-thumbnail" src="${pageContext.request.contextPath}/icons/2.svg" alt="avatar"/>
                                        </label>
                                        <label class="form-image">
                                            <input type="radio" class="form-control image-radio" name="avatar" value="3">
                                            <img class="img-thumbnail" src="${pageContext.request.contextPath}/icons/3.svg" alt="avatar"/>
                                        </label>
                                        <label class="form-image">
                                            <input type="radio" class="form-control image-radio" name="avatar" value="4">
                                            <img class="img-thumbnail" src="${pageContext.request.contextPath}/icons/4.svg" alt="avatar"/>
                                        </label>
                                        <label class="form-image">
                                            <input type="radio" class="form-control image-radio" name="avatar" value="5">
                                            <img class="img-thumbnail" src="${pageContext.request.contextPath}/icons/5.svg" alt="avatar"/>
                                        </label>
                                        <label class="form-image">
                                            <input type="radio" class="form-control image-radio" name="avatar" value="6">
                                            <img class="img-thumbnail" src="${pageContext.request.contextPath}/icons/6.svg" alt="avatar"/>
                                        </label>
                                        <label class="form-image">
                                            <input type="radio" class="form-control image-radio" name="avatar" value="7">
                                            <img class="img-thumbnail" src="${pageContext.request.contextPath}/icons/7.svg" alt="avatar"/>
                                        </label>
                                    </div>

                                    <div class="form-group col-md-2">
                                        <label for="inputSatsLevel"><b>Level of satisfaction :</b></label>
                                        <input type="range" value="<%= customer.getLevelOfSatisfaction() %>" class="form-control-range" id="inputSatsLevel" name="inputSatsLevel">
                                    </div>

                                    <div class="form-group col-md-2">
                                        <label for="inputColor"><b>Favorite color:</b></label>
                                        <input type="color" value="<%= customer.getFavoriteColor() %>" class="form-control" id="inputColor" name="inputColor">
                                    </div>

                                    <div class="form-group col-md-2">
                                        <input style="height:69px;" type="submit" class="btn btn-primary btn-block" name="action" value="Edit">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </body>
</html>