<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <meta charset="UTF-8">
    <title>PetAHP</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../static/style/style.css"/>
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
        <c:if test="${polls == null}">
            <div class="col-lg-12 text-center">
                <h3>Nie masz żadnych utworzonych ankiet</h3>
            </div>
        </c:if>
        <c:if test="${polls != null}">
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
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${polls}" var="p" varStatus="i">
                        <tr>
                            <th scope="row">${i.count}</th>
                            <td>${p.name}</td>
                            <td>${p.creationDate.dayOfMonth}-${p.creationDate.monthValue}-${p.creationDate.year} ${p.creationDate.hour}:${p.creationDate.minute}</td>
                            <td>${p.endDate.dayOfMonth}-${p.endDate.monthValue}-${p.endDate.year}</td>
                            <td>
                                <a class="btn btn-outline-primary" href="/panel/" role="button">skopiuj link</a>
                            </td>
                            <td>
                                <a class="btn btn-outline-warning" href="/panel/edycja-ankiet/" role="button">edytuj</a>
                            </td>
                            <td>
                                <a class="btn btn-outline-danger" href="/panel/edycja-ankiet/usun/${p.id}" role="button">usuń</a>
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

<script>

</script>
</body>
</html>
