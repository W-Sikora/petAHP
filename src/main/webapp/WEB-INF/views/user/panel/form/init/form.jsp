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
        <div class="col-md-3 text-center"></div>

        <div class="col-md-6">
            <div class="form-group">
                <form id="newForm" action="<c:url value="/panel/tworzenie-nowej-ankiety/podsumowanie"/>" method="post">

                    <div class="form-part part">
                        <div class="text-center"><strong>Ustawienia ogólne - krok 1 z 3</strong>
                            <hr>
                        </div>
                        <div class="form-group">
                            <label>Nazwa ankiety
                                <input name="pollName" class="form-control" placeholder="np. wybór zwierzaka"
                                       type="text" minlength="1" required></label>
                            <label>Liczba dopuszczonych do ankiety użytkowników
                                <input name="noOfVoters" class="form-control" placeholder="od 2 do 10"
                                       type="number" min="2" max="10" required></label>
                            <label>Data do której można oddać odpowiedź
                                <input name="endDate" class="form-control" type="date" min="${minDate}"
                                       required></label>
                        </div>
                    </div>

                    <div class="form-part part">
                        <div class="text-center"><strong>Ustawienia zwierząt - krok 2 z 3</strong>
                            <hr>
                        </div>
                        <div class="form-group">
                            <label>Liczba rozpatrywanych zwierząt
                                <input name="noOfAnimals" id="noOfAnimals" class="form-control"
                                       placeholder="np. od 2 do 6" type="number" min="2" max="6" required></label>
                            <div id="animalsDiv"></div>
                        </div>
                    </div>

                    <div class="form-part part">
                        <div class="text-center"><strong>Ustawienia kryteriów - krok 3 z 3</strong>
                            <hr>
                        </div>
                        <div class="form-group">
                            <label>Liczba rozpatrywanych kryteriów głównych
                                <input name="noOfCriteria" id="noOfCriteria" type="number" class="form-control"
                                       placeholder="od 2 do 6"
                                       min="2" max="6" required></label>
                            <c:forEach begin="0" end="5" varStatus="i">
                                <label class="hide">${i.count}. kryterium
                                    <input id="criterion${i.index}" name="criterion${i.index}" type="text"
                                           class="form-control" placeholder="nazwa kryterium">
                                    <input id="noOfSubCriteria${i.index}" name="noOfSubCriteria${i.index}" type="number"
                                           class="form-control" min="0" max="6"
                                           placeholder="liczba podkryteriów (od 0 do 6)">
                                    <div id="subCriteriaDiv${i.index}"></div>
                                </label>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="centerize mtb17-7">
                        <button type="button" id="prevBtn" class="btn btn-outline-dark">Wróć</button>
                        <button type="button" id="nextBtn" class="btn btn-dark lm">Dalej</button>
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
<script src="<c:url value="/static/js/index.js"/>"></script>
</body>
</html>
