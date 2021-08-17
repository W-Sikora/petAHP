<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PetAHP</title>
    <link rel="stylesheet"
          href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/style/style.css"/>"/>
</head>
<body>

<header>
    <c:import url="/WEB-INF/views/header&footer/header.jsp"/>
</header>

<div class="container min-height">

    <section>
        <nav class="breadcrumb transparent-background">
            <span class="breadcrumb-item"><b>panel użytkownika</b></span>
        </nav>
        <hr>
    </section>

    <section class="row h-75 text-center">
        <div class="col-12">
            <h3 class="mb-4">Witaj ${user.name}</h3>
            <hr>
            <div class="row">
                <div class="col-3">
                    <p class="lead mt-4">wyloguj</p>
                    <a role="button" class="btn btn-outline-dark" href="<c:url value="/panel/wyloguj"/>">przejdź</a>
                </div>

                <div class="col-3 ">
                    <p class="lead mt-4">ustawienia</p>
                    <a role="button" class="btn btn-outline-dark" href="<c:url value="/panel/ustawienia"/>">przejdź</a>
                </div>

                <div class="col-3">
                    <p class="lead mt-4">pomoc</p>
                    <a role="button" class="btn btn-outline-dark" href="<c:url value="/panel/pomoc"/>">przejdź</a>
                </div>

                <div class="col-3">
                    <p class="lead mt-4">nowa ankieta</p>
                    <a role="button" class="btn btn-outline-dark" href="<c:url value="/panel/tworzenie-ankiety"/>">przejdź</a>
                </div>
            </div>
        </div>

        <div class="col-12 mt-4">
            <c:choose>
                <c:when test="${surveys.numberOfElements == 0}">
                    <h3 class="my-4">Nie masz żadnych utworzonych ankiet</h3>
                </c:when>
                <c:otherwise>
                    <h3 class="my-4">Twoje ankiety</h3>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col">lp.</th>
                            <th scope="col">nazwa</th>
                            <th scope="col">data utworzenia</th>
                            <th scope="col">data zakończenia</th>
                            <th scope="col">głosy oddane</th>
                            <c:forEach begin="0" end="2">
                                <th scope="col"></th>
                            </c:forEach>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${surveys.toList()}" var="s" varStatus="i">
                            <tr>
                                <th scope="row">${i.count}</th>
                                <td>${s.name}</td>
                                <td>
                                    <fmt:parseDate value="${s.creationDate}" pattern="yyyy-MM-dd" var="creationDate"
                                                   type="date"/>
                                    <fmt:formatDate pattern="dd-MM-yyyy" value="${creationDate}" var="creationDate"/>
                                        ${creationDate}
                                </td>
                                <td>
                                    <fmt:parseDate value="${s.endDate}" pattern="yyyy-MM-dd" var="endDate" type="date"/>
                                    <fmt:formatDate pattern="dd-MM-yyyy" value="${endDate}" var="endDate"/>
                                        ${endDate}
                                </td>
                                <td>
                                        ${s.actualVotesNumber}/${s.evaluatorNumber}
                                </td>
                                <td>
                                    <a class="btn btn-outline-primary" role="button"
                                       href="<c:url value="/panel/szczegoly/${s.id}"/>">szczegóły</a>
                                </td>
                                <td>
                                    <a class="btn btn-outline-success" role="button"
                                       href="<c:url value="/panel/wynik/${s.id}"/>">wynik</a>
                                </td>
                                <td>
                                    <a class="btn btn-outline-danger" role="button"
                                       href="<c:url value="/panel/usun/${s.id}"/>">usuń</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </section>
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
