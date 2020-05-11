<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>PetAHP</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../static/style/style.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
            <a class="nav-item nav-link active"><strong>ankieta: ${poll.name}</strong></a>
        </div>
    </nav>
    <hr>
    <div class="row">
        <div class="col-lg-12 margin-auto">
            <h2 class="text-center">${poll.name}</h2>
            <form id="completeForm" action="/ankieta/zapisano-odpowiedz" method="post">
                <div class="col-lg-7 margin-auto">
                    <div class="form-group part">
                        <input name="pollId" value="${poll.id}" hidden>
                        <label>imię wypełniającego
                            <input type="text" class="form-control" name="evaluatorName" placeholder="np. Jan"
                                   minlength="3"
                                   maxlength="30" required></label>
                    </div>
                </div>
                <div class="col-lg-10 margin-auto">
                    <c:forEach items="${criteria}" var="c">
                        <table class="table table-hover part">
                            <thead class="thead-light">
                            <tr>
                                <th>Kryterium / Zwierze</th>
                                <c:forEach items="${animals}" var="a">
                                    <th class="text-center">${a.name}</th>
                                </c:forEach>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><strong>${c.name}</strong></td>

                                <c:forEach items="${animals}" var="a">
                                    <td>
                                        <div class="ratings" id="${a.id}-${c.id}">
                                            <span class="fa fa-star-o"></span>
                                            <span class="fa fa-star-o"></span>
                                            <span class="fa fa-star-o"></span>
                                            <span class="fa fa-star-o"></span>
                                            <span class="fa fa-star-o"></span>
                                        </div>
                                        <input type="text" class="rating-value" name="criterionRate${a.id}-${c.id}" id="rating-value${a.id}-${c.id}" hidden>
                                    </td>
                                </c:forEach>
                            </tr>
                            <c:forEach items="${subCriteria}" var="s">
                                <c:if test="${s.criterion.id == c.id}">
                                    <tr>
                                        <td>${s.name}</td>
                                        <c:forEach items="${animals}" var="a">
                                            <td>
                                                <div class="ratings" id="${a.id}-${s.id}">
                                                    <span class="fa fa-star-o"></span>
                                                    <span class="fa fa-star-o"></span>
                                                    <span class="fa fa-star-o"></span>
                                                    <span class="fa fa-star-o"></span>
                                                    <span class="fa fa-star-o"></span>
                                                </div>
                                                <input type="text" class="rating-value" name="subCriterionRate${a.id}-${s.id}" id="rating-value${a.id}-${s.id}" hidden>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:forEach>
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

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>
<script src="<c:url value="/static/js/index.js"/>"></script>
</body>
</html>
