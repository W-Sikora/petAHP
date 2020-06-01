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
                        <div class="text-center"><p><strong>Ogólne</strong> - krok <strong>1</strong> z 3</p>
                            <hr>
                        </div>
                        <div class="form-group">
                            <label>Nazwa ankiety
                                <input name="surveyName" class="form-control" placeholder="np. wybór zwierzaka"
                                       type="text" minlength="1" required>
                            </label>

                            <label>Liczba dopuszczonych do ankiety użytkowników
                                <input id="evaluatorNumber" name="evaluatorNumber" class="form-control" placeholder="od 2 do 10"
                                       type="number" min="2" max="10" required>
                                <span id="errorEvaluatorNumber" class="err">nieprawidłowa wartość</span>
                            </label>

                            <label>Data do której można oddać odpowiedź
                                <input id="endDate" name="endDate" class="form-control" type="date" min="${minDate}"
                                       required>
                                <span id="errorEndDate" class="err">nieprawidłowa wartość</span>
                            </label>

                        </div>
                        <div id="basicMsg" class="text-center">
                            <p>
                                <mark>aby przejsć dalej wypełnij powyższe pola</mark>
                            </p>
                        </div>
                    </div>

                    <div class="form-part part">
                        <div class="text-center"><p><strong>Zwierzęta</strong> - krok <strong>2</strong> z 3</p>
                            <hr>
                        </div>
                        <div class="form-group">
                            <div id="animals"></div>
                            <div class="text-center">
                                <button type="button" id="addAnimal" class="btn btn-outline-success">
                                    dodaj
                                </button>
                            </div>
                        </div>
                        <div id="animalsMsg" class="text-center mtb17-7">
                            <p>
                                <mark>aby przejsć dalej musisz stworzyć przynajmniej <b>2</b> zwierzęta
                                    (maksymalnie <b>9</b>)
                                </mark>
                            </p>
                        </div>
                    </div>

                    <div class="form-part part">
                        <div class="text-center"><p><strong>Kryteria</strong> - krok <strong>3</strong> z 3</p>
                            <hr>
                        </div>
                        <div class="form-group">
                            <div id="criteria"></div>
                        </div>
                        <div id="criterionMsg" class="text-center mtb17-7">
                            <p>
                                <mark>aby przejsć dalej musisz stworzyć przynajmniej <b>2</b> kryteria (maksymalnie
                                    <b>25</b>)
                                </mark>
                            </p>
                            <p>
                                <mark>
                                    dodatkowo do każdego kryterium głównego możesz utworzyć trzy poziomy podkryteriów
                                </mark>
                            </p>
                            <p>
                                <mark>
                                    twórz przynajmniej <b>2</b> podkryteria dla danego kryterium lub nie twórz wcale
                                </mark>
                            </p>
                        </div>
                    </div>

                    <div class="text-center mtb37-7">
                        <hr>
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
