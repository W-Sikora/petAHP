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
            <a class="nav-item nav-link active"><strong>ankieta: ${survey.name}</strong></a>
        </div>
    </nav>
    <hr>

    <div class="row">
        <div class="col-lg-12 margin-auto">
            <h2 class="text-center">${survey.name}</h2>
            <form id="completeForm" action="/ankieta/zapisano-odpowiedz" method="post">

                <div class="form-group part">
                    <label>
                        <input name="pollId" value="${survey.id}" hidden>
                    </label>
                    <label>imię wypełniającego
                        <input type="text" class="form-control" name="evaluatorName"
                               placeholder="np. Jan" minlength="3" maxlength="30" required>
                    </label>
                </div>

                <%--                <c:forEach items="${animals}" var="animal" begin="0" end="${animals.size()}" varStatus="a">--%>
                <%--                    <c:forEach items="${animals}" var="_animal" begin="${a.index + 1}" end="${animals.size()}">--%>
                <%--                        <c:if test="${animal.name ne _animal.name}">--%>
                <%--                            <p>${animal.name}${_animal.name}</p>--%>
                <%--                        </c:if>--%>
                <%--                    </c:forEach>--%>
                <%--                </c:forEach>--%>

                <c:forEach items="${criteria}" var="c">
                    <c:if test="${c.criterion == null}">
                        ${c.name}
                    </c:if>
                    <c:if test="${c.criterion != null}">
                        <c:if test="${c.criterion.}">

                        </c:if>
                        <p><strong>${c.criterion.name}</strong> ${c.name}</p>
                    </c:if>
                </c:forEach>


                <div class="text-center mtb17-7">
                    <button type="button" id="prevBtn" class="btn btn-outline-dark">Wróć</button>
                    <button type="button" id="nextBtn" class="btn btn-dark lm">Dalej</button>
                </div>

            </form>
        </div>
    </div>
</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>
<script src="<c:url value="/static/js/index.js"/>"></script>
</body>
</html>
