<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

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
            <a class="nav-item nav-link active"><strong>wynik</strong></a>
        </div>
    </nav>
    <hr>

    <div class="row">
        <div class="col-lg-10 text-center margin-auto">
            <h2>Uzyskiwanie wyników</h2>
            <p>aby uzyskać ostateczny wyniki musisz dokończyć procedurę</p>
            <form action="<c:url value="/panel/zapisz/wynik"/>" method="post">
                <input type="hidden" name="surveyId" value="${survey.id}">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>głosujący 1</th>
                        <th>przewaga</th>
                        <th>głosujący 2</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${evaluators}" var="e1" begin="0" end="${evaluators.size()}" varStatus="i">
                        <c:forEach items="${evaluators}" var="e2" begin="${i.count}" end="${evaluators.size()}">
                            <c:if test="${e1.id ne e2.id}">
                                <tr>
                                    <td class="n1">${e1.name}</td>
                                    <td class="td">
                                        <span id="evaluator_text_${e1.id}_${e2.id}"></span>
                                        <input type="range" min="1" max="17" class="form-control-range"
                                               name="evaluator_${e1.id}_${e2.id}"/>
                                    </td>
                                    <td class="n2">${e2.name}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                    </tbody>
                </table>
                <hr class="mtb37-27">
                <button type="submit" class="btn btn-outline-dark">przejdź do wyniku</button>
            </form>
        </div>
    </div>
</div>

<footer class="mtb37-7">
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

<script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"/>"></script>
<script src="<c:url value="https://cdn.jsdelivr.net/npm/clipboard@2/dist/clipboard.min.js"/>"></script>
<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/index.js"/>"></script>

</body>
</html>
