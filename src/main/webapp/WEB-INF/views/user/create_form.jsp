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
            <a class="nav-item nav-link active" href="<c:url value="/panel"/>">panel użytkownika</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active"><strong>nowa ankieta</strong></a>
        </div>
    </nav>
    <hr>

    <div class="row">
        <div class="col-lg-12 text-center">
            <h3>Tworzenie nowej ankiety</h3>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-8 margin-auto">
            <div class="form-group">
                <form id="form" action="<c:url value="/panel/tworzenie-ankiety"/>" method="post">

                    <div class="form-part part">
                        <div class="text-center"><strong>Ustawienia ogólne - krok 1 z 3</strong>
                            <hr>
                        </div>
                        <div class="form-group">
                            <label>Nazwa ankiety
                                <input name="surveyName" class="form-control" placeholder="np. wybór zwierzaka"
                                       type="text" minlength="1" required>
                            </label>
                            <label>Liczba dopuszczonych do ankiety użytkowników
                                <input name="evaluatorNumber" class="form-control" placeholder="od 2 do 10"
                                       type="number" min="2" max="10" required>
                            </label>
                            <label>Data do której można oddać odpowiedź
                                <input name="endDate" class="form-control" type="date" min="${minDate}"
                                       required>
                            </label>
                        </div>
                    </div>

                    <div class="form-part part">
                        <div class="text-center"><strong>Ustawienia zwierząt - krok 2 z 3</strong>
                            <hr>
                        </div>
                        <div class="form-group">
                            <ol id="animals"></ol>
                            <div class="text-center">
                                <button type="button" id="addAnimal" class="btn btn-outline-success">
                                    dodaj zwierzę
                                </button>
                                <button type="button" id="removeAnimal" class="btn btn-outline-danger m-left">
                                    usuń zwierzę
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="form-part part">
                        <div class="text-center"><strong>Ustawienia kryteriów - krok 3 z 3</strong>
                            <hr>
                        </div>
                        <div class="form-group">
                            <ol id="criteria"></ol>
                            <div class="text-center">
                                <button type="button" id="addCriterion" class="btn btn-outline-success">
                                    dodaj kryterium
                                </button>
                                <button type="button" id="removeCriterion" class="btn btn-outline-danger m-left">
                                    usuń kryterium
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="text-center mtb17-7">
                        <button type="button" id="prevBtn" class="btn btn-outline-dark">Wróć</button>
                        <button type="button" id="nextBtn" class="btn btn-dark lm">Dalej</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<footer class="m-top">
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="<c:url value="/static/js/index.js"/>"></script>
</body>
</html>
