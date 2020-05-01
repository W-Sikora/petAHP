<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            <a class="nav-item nav-link active"><strong>panel użytkownika</strong></a>
        </div>
    </nav>
    <hr>
    <div class="row">
        <div class="col-lg-12 text-center">
            <h2 class="mtb7-17">Witaj ${user}</h2>
            <a class="btn btn-dark mtb7-17" href="<c:url value="/wyloguj"/>" role="button">wyloguj</a>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-lg-4 text-center">
            <h4 class="mtb7-17">Utwórz nową ankietę</h4>
            <a class="btn btn-outline-success" href="<c:url value="/panel/tworzenie-nowej-ankiety"/>" role="button">przejdź</a>
            <p></p>
        </div>
        <div class="col-lg-4 text-center">
            <h4 class="mtb7-17">Edytuj istniejącą ankietę</h4>
            <a class="btn btn-outline-warning" href="<c:url value="/panel/edycja-ankiet"/>" role="button">przejdź</a>
        </div>
        <div class="col-lg-4 text-center">
            <h4 class="mtb7-17">Pomoc</h4>
            <a class="btn btn-outline-info" href="<c:url value="/panel/pomoc"/>" role="button">przejdź</a>
        </div>
    </div>
    <hr>
    <div class="row">
<%--        <div class="col-lg-12 text-center">--%>
<%--            <h3>Wyniki przeprowadzonych ankiet</h3>--%>
<%--        </div>--%>

<%--        <div class="col-lg-12 text-center">--%>
<%--            <h3>Nie masz żadnych zakończonych ankiet</h3>--%>
<%--        </div>--%>
    </div>
</div>
    <footer>
        <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
    </footer>

</body>
</html>
