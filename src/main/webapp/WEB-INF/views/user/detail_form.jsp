<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
    <nav class="navbar navbar-expand-sm navbar-light">
        <div class="navbar-nav">
            <a class="nav-item nav-link active" href="<c:url value="/panel/"/>">panel użytkownika</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active">nowa ankieta</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active"><strong>szczegóły</strong></a>
        </div>
    </nav>
    <hr>

    <div class="row">
        <div class="col-lg-12">

            <h2 class="text-center">Szczegóły ankiety: ${survey.name}</h2>

            <div class="col-lg-10 margin-auto">
                <h5>Linki:</h5>
                <hr>
                <div class="input-group mb-3">
                    <div class="input-group-prepend" style="margin-top:15px">
                        <span class="input-group-text">link do&nbsp;<b>wypełnienia</b>&nbsp;ankiety</span>
                    </div>
                    <input readonly class="form-control" style="background-color: white"
                           value="http://localhost:8080/ankieta/=${survey.votingLink}"/>
                    <div class="input-group-append" style="margin-top:15px">
                        <button class="btn btn-outline-primary copy" id="votingLink${survey.id}"
                                data-clipboard-text="http://localhost:8080/ankieta/=${survey.votingLink}">skopiuj
                        </button>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend" style="margin-top:15px">
                        <span class="input-group-text">link do&nbsp;<b>wyniku</b>&nbsp;ankiety</span>
                    </div>
                    <input readonly class="form-control" style="background-color: white"
                           value="http://localhost:8080/ankieta/wynik=${survey.resultLink}"/>
                    <div class="input-group-append" style="margin-top:15px">
                        <button class="btn btn-outline-primary copy" id="resultLink${survey.id}"
                                data-clipboard-text="http://localhost:8080/ankieta/wynik=${survey.resultLink}">skopiuj
                        </button>
                    </div>
                </div>

                <h5>Ogólne:</h5>
                <hr>
                <p>Nazwa ankiety: <strong>${survey.name}</strong></p>
                <p>Liczba oceniających: <strong>${survey.evaluatorNumber}</strong></p>
                <p>Data zakończenia:
                    <fmt:parseDate value="${survey.endDate}" pattern="yyyy-MM-dd" var="endDate" type="date"/>
                    <fmt:formatDate pattern="dd-MM-yyyy" value="${endDate}" var="endDate"/>
                    <strong>${endDate}</strong>
                </p>

                <h5>Zwierzęta:</h5>
                <hr>
                <ul>
                    <c:forEach items="${animals}" var="a">
                        <li class="hl-1">${a.name}</li>
                    </c:forEach>
                </ul>

                <h5>Przyjęte kryteria:</h5>
                <hr>
                <ul>
                    <c:forEach items="${criteria}" var="c" varStatus="i">
                        <li class="hl-${c.hierarchyLevel}">${c.name}</li>
                    </c:forEach>
                </ul>
            </div>

        </div>
    </div>
</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

<script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"/>"></script>
<script src="<c:url value="https://cdn.jsdelivr.net/npm/clipboard@2/dist/clipboard.min.js"/>"></script>
<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/index.js"/>"></script>

</body>
</html>
