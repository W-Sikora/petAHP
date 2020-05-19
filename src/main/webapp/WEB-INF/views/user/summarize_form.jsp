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
            <a class="nav-item nav-link active">nowa ankieta</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active"><strong>podsumowanie</strong></a>
        </div>
    </nav>
    <hr>

    <div class="row">
        <div class="col-lg-12 text-center">
            <h3>Ankieta została pomyślnie utworzona</h3>
            <a class="btn btn-outline-dark" href="<c:url value="/panel"/>" role="button">ok</a>
            <div class="col-lg-6 margin-auto">
                <label>link do wypełnienia ankiety</label>
                <button class="btn btn-outline-primary copy" id="link${survey.id}"
                        data-clipboard-text="http://localhost:8080/ankieta=${survey.votingLink}">skopiuj link
                </button>
            </div>
        </div>
    </div>

    <div class="row">

        <div class="col-lg-8 margin-auto">
            <h5>Ogólne:</h5>
            <hr>
            <p>Nazwa ankiety: <strong>${survey.name}</strong></p>
            <p>Liczba oceniających: <strong>${survey.evaluatorNumber}</strong></p>
            <p>Data zakończenia:
                <c:if test="${survey.endDate.monthValue < 10}">
                    <strong>${survey.endDate.dayOfMonth}-0${survey.endDate.monthValue}-${survey.endDate.year}</strong>
                </c:if>
                <c:if test="${survey.endDate.monthValue >= 10}">
                    <strong>${survey.endDate.dayOfMonth}-${survey.endDate.monthValue}-${survey.endDate.year}</strong>
                </c:if>
            </p>


            <h5>Zwierzęta:</h5>
            <hr>
            <c:forEach items="${animals}" var="a">
                <p>- <strong>${a.name}</strong></p>
            </c:forEach>

            <h5>Przyjęte kryteria:</h5>
            <hr>
            <c:forEach items="${criteria}" var="c">
                <c:if test="${c.survey == null}">
                    <p><strong>${c.name}</strong></p>
                </c:if>
                <c:if test="${c.survey != null}">
                    <p>- ${c.name}</p>
                </c:if>
            </c:forEach>
        </div>

    </div>
</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>
<script src="https://cdn.jsdelivr.net/npm/clipboard@2/dist/clipboard.min.js"></script>
<script src="<c:url value="/static/js/index.js"/>"></script>
</body>
</html>
