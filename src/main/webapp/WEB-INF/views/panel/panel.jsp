<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PetAHP</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        hr {
            margin-top: 15px;
            margin-bottom: 15px;
        }

        p {
            text-align: justify;
            margin-top: 20px;
            margin-bottom: 20px;
        }

        h2 {
            margin-top: 35px;
            margin-bottom: 25px;
        }

        h3 {
            margin-top: 20px;
            margin-bottom: 20px;

        }

        h5 {
            margin-top: 20px;
            margin-bottom: 20px;
        }
    </style>
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
            <h2>Witaj</h2>
            <a class="btn btn-dark" href="/" role="button">wyloguj</a>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-lg-4 text-center">
            <h5>Utwórz nową ankietę</h5>
            <a class="btn btn-success" href="/panel/ankieta-tworzenie" role="button">przejdź</a>
            <p></p>
        </div>
        <div class="col-lg-4 text-center">
            <h5>Edytuj istniejącą ankietę</h5>
            <a class="btn btn-warning" href="/panel/edycja-ankiet" role="button">przejdź</a>
        </div>
        <div class="col-lg-4 text-center">
            <h5>Pomoc</h5>
            <a class="btn btn-info" href="/panel/pomoc" role="button">przejdź</a>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-lg-12 text-center">
            <h3>Wyniki przeprowadzonych ankiet</h3>
        </div>
    </div>
    <div class="col-lg-12 text-center">
        <h3>Nie masz żadnych utworzonych ankiet</h3>
    </div>
</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
