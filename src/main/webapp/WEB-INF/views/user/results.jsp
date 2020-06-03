<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <meta charset="UTF-8">
    <title>PetAHP</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/style/style.css"/>"/>
</head>
<body>

<header>
    <c:import url="/WEB-INF/views/header&footer/header.jsp"/>
</header>

<div class="container">
    <nav class="navbar navbar-expand-sm navbar-light">
        <div class="navbar-nav">
            <a class="nav-item nav-link active" href="/panel">panel użytkownika</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active"><strong>wynik</strong></a>
        </div>
    </nav>
    <hr>

    <div class="col-lg-12 text-center">

        <h2>Strona w budowie</h2>
        <c:if test="${survey.status == 'FOUNDED'}">
            <p>aby uzyskać wyniki musisz dokończyć procedurę</p>
            <table class="table">
                <thead>
                <tr>
                    <th>kryterium 1</th>
                    <th>przewaga</th>
                    <th>kryterium 2</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${evaluators}" var="e1" begin="0" end="${evaluators.size()}" varStatus="i">
                    <c:forEach items="${evaluators}" var="e2" begin="${i.count}" end="${evaluators.size()}">
                        <c:if test="${e1.name ne e2.name and e1.id ne e2.id}">
                            <tr>
                                <td class="n1">${e1.name}</td>
                                <td>
                                    <span id="range_text_${e1.id}_${e2.id}"></span>
                                    <input type="range" min="1" max="17" class="form-control-range"
                                           name="range_${e1.id}_${e2.id}"/>
                                </td>
                                <td class="n2">${e2.name}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${survey.status == 'COMPLETED'}">
            <p>a</p>
        </c:if>
        <c:if test="${survey.status == 'DELETED'}">
            <p>przedmiotowy wynik nie istnieje</p>
        </c:if>

    </div>

</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="<c:url value="/static/js/index.js"/>"></script>

</body>
</html>
