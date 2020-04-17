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
            <a class="nav-item nav-link active">nowa ankieta</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active">ustawienia zwierząt</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active"><strong>ustawienia kryteriów</strong></a>
        </div>
    </nav>
    <hr>

    <div class="row">
        <div class="col-lg-12 text-center">
            <h3>Tworzenie nowej ankiety - krok 4</h3>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-3"></div>

        <div class="col-lg-6">
            <c:import url="/WEB-INF/views/panel/form/init/form/form4.jsp"/>
        </div>

        <div class="col-lg-3"></div>
    </div>
</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
