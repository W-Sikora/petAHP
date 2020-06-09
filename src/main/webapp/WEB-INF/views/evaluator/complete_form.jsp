<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="pl">
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
    <style>
        .table {
            margin: auto;
            width: 80% !important;
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
            <a class="nav-item nav-link active" href="<c:url value="/"/>">strona główna</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active"><strong>ankieta: ${survey.name}</strong></a>
        </div>
    </nav>
    <hr>

    <form id="completeForm" action="<c:url value="/ankieta/zapisano-odpowiedz"/>" method="post">
        <div class="col-lg-12 margin-auto">
            <h2 class="text-center">${survey.name}</h2>
            <div class="row">
                <div class="col-lg-7 margin-auto">
                    <div class="form-group part">
                        <input type="hidden" name="surveyId" value="${survey.id}"/>
                        <label>imię wypełniającego
                            <input type="text" class="form-control" id="evaluatorName" name="evaluatorName"
                                   placeholder="np. Jan" minlength="3" maxlength="30" required>
                        </label>
                    </div>
                </div>
            </div>
            <div class="row ">
                <div class="col-lg-10 margin-auto">
                    <input type="hidden" name="criteriaSize" value="${criteriaSize}"/>
                    <c:forEach items="${criteria}" var="criterion" varStatus="i">
                        <table class="table table-hover mtb7-27 text-center part">
                            <thead class="thead-light">
                            <tr>
                                <th scope="col">kryterium 1</th>
                                <th scope="col">przewaga</th>
                                <th scope="col">kryterium 2</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${criterion}" var="c1" begin="0" end="${criterion.size()}"
                                       varStatus="j">
                                <c:forEach items="${criterion}" var="c2" begin="${j.index}"
                                           end="${criterion.size()}">
                                    <c:if test="${c1.name ne c2.name and c1.id ne c2.id}">
                                        <tr>
                                            <td class="n1">${c1.name}</td>
                                            <td class="td">
                                                <span id="criteriaComparisonText_${c1.id}_${c2.id}"></span>
                                                <input type="range" min="1" max="17" class="form-control-range"
                                                       name="criteriaComparison_${i.index}_${c1.id}_${c2.id}"/>
                                            </td>
                                            <td class="n2">${c2.name}</td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:forEach>

                    <c:forEach items="${noChildren}" var="no" varStatus="i">
                        <table class="table table-hover mtb7-27 margin-auto text-center part">
                            <thead class="thead-light">
                            <tr>
                                <th scope="col">zwierzę 1</th>
                                <th scope="col">przewaga w zględem ${no.name}</th>
                                <th scope="col">zwierzę 2</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${animals}" var="a1" begin="0" end="${animals.size()}" varStatus="j">
                                <c:forEach items="${animals}" var="a2" begin="${j.index}" end="${animals.size()}">
                                    <c:if test="${a1.name ne a2.name and a1.id ne a2.id}">
                                        <tr>
                                            <td class="n1"><strong>${a1.name}</strong></td>
                                            <td class="td">
                                                <span id="animalCriteriaComparisonText_${a1.id}_${a2.id}"></span>
                                                <input type="range" min="1" max="17" class="form-control-range"
                                                       name="animalCriteriaComparison_${i.index}_${a1.id}_${a2.id}_${no.id}"/>
                                            </td>
                                            <td class="n2"><strong>${a2.name}</strong></td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="text-center mtb37-7">
            <hr>
            <button type="button" id="prevBtn" class="btn btn-outline-dark">Wróć</button>
            <button type="button" id="nextBtn" class="btn btn-dark lm">Dalej</button>
        </div>

    </form>
</div>

<footer class="footer-sm-page">
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

<script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"/>"></script>
<script src="<c:url value="https://cdn.jsdelivr.net/npm/clipboard@2/dist/clipboard.min.js"/>"></script>
<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/index.js"/>"></script>

</body>
</html>
