<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PetAHP</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        <c:import url="/WEB-INF/views/panel/form/style/style.jsp"/>
    </style>
</head>
<body>

<header>
    <c:import url="/WEB-INF/views/header&footer/header.jsp"/>
</header>

<div class="container">
    <nav class="navbar navbar-expand-sm navbar-light">
        <div class="navbar-nav">
            <a class="nav-item nav-link active" href="<c:url value="/panel/"/>">panel użytkownika</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active"><strong>edycja</strong></a>
        </div>
    </nav>
    <hr>

    <div class="row">
        <c:if test="${forms == null}">
            <div class="col-lg-12 text-center">
                <h3>Nie masz żadnych utworzonych ankiet</h3>
            </div>
        </c:if>
        <c:if test="${forms != null}">
            <div class="col-lg-12 text-center">
                <h2>Edycja ankiety</h2>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">lp.</th>
                        <th scope="col">nazwa</th>
                        <th scope="col">data utworzenia</th>
                        <th scope="col">data zakończenia</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${forms}" var="f" varStatus="i">
                        <tr>
                            <th scope="row">${i.count}</th>
                            <td>${f.name}</td>
                            <td>${f.creationDate}</td>
                            <td>${f.dateToVote}</td>
                            <td>
                                <button type="button" class="btn btn-warning">edycja</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger">usuń</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
