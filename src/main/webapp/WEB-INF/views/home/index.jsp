<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <meta charset="UTF-8">
    <title>PetAHP</title>
    <link rel="stylesheet"
          href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/style/style.css"/>"/>
</head>
<body>

<header>
    <c:import url="/WEB-INF/views/header&footer/header.jsp"/>
</header>

<div class="container min-height">
    <div class="row">
        <div class="col-12 text-center">
            <img src="<c:url value="/static/img/pngfuel.comfree-pngdnmurdownload.png"/>" width="85%" height="auto"
                 alt="">
            <ul class="list-inline text-centered my-2">
                <li class="list-inline-item">
                    <p class="lead">pies?</p>
                </li>
                <li class="list-inline-item mx-5">
                    <p class="lead">kot?</p>
                </li>
                <li class="list-inline-item">
                    <p class="lead">a może rybki?</p>
                </li>

            </ul>
        </div>
    </div>
    <hr>

    <div class="row">
        <div class="col-4 text-center">
            <h4 class="mtb7-17">ankiety i wyniki</h4>
            <a class="btn btn-outline-dark mtb7-17" href="<c:url value="/przejdz-do-ankiety"/>"
               role="button">przejdź</a>
        </div>

        <div class="col-4 text-center">
            <h4 class="mtb7-17">zarejestruj się</h4>
            <a class="btn btn-outline-primary mtb7-17" href="<c:url value="/rejestracja"/>" role="button">przejdź</a>
        </div>

        <div class="col-4 text-center">
            <h4 class="mtb7-17">zaloguj się</h4>
            <a class="btn btn-outline-success mtb7-17" href="<c:url value="/logowanie"/>" role="button">przejdź</a>
        </div>
    </div>
    <hr>

    <div class="col-12">
        <h2 class="my-4">O co chodzi?</h2>
        <div class="text-justify">
            <p>Aplikacja wspomaga wybór pupila wykorzystując metodę
                <a href="https://en.wikipedia.org/wiki/Analytic_hierarchy_process">Analytic hierarchy process</a>.</p>
            <p>Decydent (użytkownik posiadający konto w serwisie) wybiera kilka różnych zwierzątek, które następnie
                poddawane są oceniane w sposób wielokryterialny przez osoby, które uzyskają link do oddania głosu.
                W rezultacie przeprowadzonej analizy tworzony jest ranking wizualizujący, który z rozpatrywanych
                pupili najbardziej wpasowuje się w oczekiwania wszystkich głosujących.</p>
        </div>
    </div>

</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
