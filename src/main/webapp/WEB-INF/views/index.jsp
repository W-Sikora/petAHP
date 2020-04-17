<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

        a {
            margin-bottom: 15px ;
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
            margin-top: 10px;
            margin-bottom: 17px;

        }

        h5 {
            margin-top: 20px;
            margin-bottom: 20px;
        }

        footer {
            margin-top: 40px;
        }
    </style>
</head>
<body>

<header>
    <c:import url="/WEB-INF/views/header&footer/header.jsp"/>
</header>

<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <img src="<c:url value="/static/img/background.png"/>" width="100%" height="auto">
        </div>
    </div>
    <div class="row">
        <div class="col-lg-4 text-center">
            <h3>pies?</h3>
        </div>
        <div class="col-lg-4 text-center">
            <h3>kot?</h3>
        </div>
        <div class="col-lg-4 text-center">
            <h3>a może rybki?</h3>
        </div>
    </div>
    <hr>

    <div class="row">
        <div class="col-lg-6 text-center">
            <h3>Zarejestruj się</h3>
            <a class="btn btn-primary" href="/rejestracja" role="button">przejdź</a>
        </div>
        <div class="col-lg-6 text-center">
            <h3>Zaloguj się</h3>
            <a class="btn btn-success" href="/logowanie" role="button">przejdź</a>
        </div>
    </div>
    <hr>

    <div class="col-lg-12">
        <h2>O co chodzi?</h2>
        <p style="">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ac blandit ipsum. Vestibulum ante ipsum
            primis in faucibus orci luctus et ultrices posuere cubilia Curae; Maecenas hendrerit a leo sed gravida.
            Vivamus vestibulum pretium enim, nec lobortis orci elementum nec. Vestibulum dapibus, ipsum sit amet
            dignissim lacinia, orci nunc pulvinar nisi, id lacinia eros magna quis nulla. Curabitur laoreet arcu nulla,
            a tincidunt magna semper ac. Duis tristique venenatis aliquam. Maecenas vulputate consequat dolor, id
            ultricies ipsum hendrerit vel.</p>

        <p> Cras eros est, lacinia at eros non, molestie ultricies risus. Donec in egestas enim, quis posuere neque.
            Quisque vestibulum pharetra fermentum. Phasellus lobortis justo ex, id dictum elit vulputate consequat.
            Nulla non ante magna. Nullam tempus elit at eleifend blandit. Praesent rutrum, nisi sit amet interdum
            dignissim, tellus arcu pharetra arcu, vel sagittis diam eros eget nunc. Ut dignissim non mauris nec
            fringilla. Donec ac mollis ante. Cras molestie, ante a tincidunt convallis, urna erat luctus felis, id
            volutpat est risus eu ligula. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec malesuada
            sit amet nisi eu molestie. Quisque eu nunc vel metus ultrices posuere ut at sapien. Praesent pellentesque
            enim lectus, eu finibus eros porttitor at. Integer dui felis, suscipit non pharetra ornare, egestas a urna.
            Nulla nec quam condimentum, euismod augue quis, mattis lectus.
        </p>

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
