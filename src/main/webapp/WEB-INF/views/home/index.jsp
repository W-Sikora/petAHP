<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <meta charset="UTF-8">
    <title>PetAHP</title>
    <link rel="stylesheet"
          href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"/>"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous"/>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/static/style/style.css"/>"/>
</head>
<body>

<header>
    <c:import url="/WEB-INF/views/header&footer/header.jsp"/>
</header>

<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center margin-auto">
            <img src="<c:url value="/static/img/pngfuel.comfree-pngdnmurdownload.png"/>" width="83%" height="auto"
                 alt="">
        </div>
    </div>
    <div class="row fst">
        <div class="col-lg-4 text-center">
            <p class="lead">pies?</p>
        </div>
        <div class="col-lg-4 text-center">
            <p class="lead">kot?</p>
        </div>
        <div class="col-lg-4 text-center">
            <p class="lead">a może rybki?</p>
        </div>
    </div>
    <hr>

    <div class="row justify-content-center">
        <div class="col-lg-4 text-center">
            <h4 class="mtb7-17">ankiety i wyniki</h4>
            <a class="btn btn-outline-dark mtb7-17" href="<c:url value="/przejdz-do-ankiety"/>"
               role="button">przejdź</a>
        </div>

        <div class="col-lg-4 text-center">
            <h4 class="mtb7-17">zarejestruj się</h4>
            <a class="btn btn-outline-primary mtb7-17" href="<c:url value="/rejestracja"/>" role="button">przejdź</a>
        </div>

        <div class="col-lg-4 text-center">
            <h4 class="mtb7-17">zaloguj się</h4>
            <a class="btn btn-outline-success mtb7-17" href="<c:url value="/logowanie"/>" role="button">przejdź</a>
        </div>
    </div>
    <hr>

    <div class="col-lg-12">
        <h2>O co chodzi?</h2>
        <p class="text-justify">
            Aplikacja wspomaga wybór pupila wykorzystując metodę
            <a href="https://en.wikipedia.org/wiki/Analytic_hierarchy_process">AHP</a>. Decydent (użytkownik posiadający
            konto w serwisie) wybiera kilka różnych zwierzątek, które następnie poddawane są oceniane w sposób
            wielokryterialny przez osoby, które uzyskają link do oddania głosu. W rezultacie przeprowadzonej analizy
            tworzony jest ranking wizualizujący, który z rozpatrywanych pupili najbardziej wpasowuje się w oczekiwania
            wszystkich głosujących.</p>

        <h2>Jak zacząć</h2>
        <h5>1. Ut vel vehicula enim</h5>
        <p> Nulla scelerisque fringilla blandit. Donec facilisis mattis efficitur. Nulla vitae convallis neque. Nulla
            enim urna, auctor ut risus in.</p>
        <h5>2. Ut vel vehicula enim</h5>
        <p> Nulla scelerisque fringilla blandit. Donec facilisis mattis efficitur. Nulla vitae convallis neque. Nulla
            enim urna, auctor ut risus in.</p>
    </div>

</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
