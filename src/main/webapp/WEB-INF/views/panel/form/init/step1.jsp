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
            <a class="nav-item nav-link active" href="<c:url value="/panel"/>">panel użytkownika</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active"><strong>nowa ankieta</strong></a>
        </div>
    </nav>
    <hr>

    <div class="row">
        <div class="col-lg-12 text-center">
            <h3>Tworzenie nowej ankiety - krok 1 z 3</h3>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3 text-center"></div>

        <div class="col-md-6">
            <div class="form-group">
                <form action="<c:url value="/panel/tworzenie-nowej-ankiety/krok-1"/>" method="post">
                    <div class="form-part">
                        <strong>Ustawienia ankiety</strong>
                        <hr>
                    </div>
                    <label>Nazwa ankiety</label>
                    <input name="name" class="form-control" placeholder="np. wybór zwierzaka"
                           type="text" minlength="1" required>
                    <label>Liczba dopuszczonych do ankiety użytkowników</label>
                    <input name="noOfVoters" class="form-control" placeholder="od 2 do 10"
                           type="number" min="2" max="10" required>
                    <label>Data do której można oddać odpowiedź</label>
                    <input name="endDate" class="form-control" type="date" min="${minDate}" required>
                    <div class="form-part">
                        <strong>Ustawienia zwierząt</strong>
                        <hr>
                    </div>
                    <label>Liczba rozpatrywanych zwierząt</label>
                    <input name="noOfAnimals" type="number" class="form-control" placeholder="od 2 do 10"
                           min="2" max="10" required>
                    <div class="form-part"><strong>Ustawienia kryteriów</strong>
                        <hr>
                    </div>
                    <label>Liczba rozpatrywanych kryteriów głównych</label>
                    <input name="noOfCriteria" type="number" class="form-control" placeholder="od 2 do 7"
                           min="2" max="7" required>
                    <div class="form-part">
                        <button type="submit" class="btn btn-dark">dalej</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="col-md-3 text-center"></div>
    </div>
</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
