<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <a class="nav-item nav-link active">
                <strong>panel użytkownika</strong>
            </a>
        </div>
    </nav>
    <hr>
    <div class="row text-center">
        <div class="col-lg-12 text-center">
            <h2 class="mtb7-27">Witaj ${user.name}</h2>
            <hr>
            <div class="row">
                <div class="col-lg-3 text-center margin-auto">
                    <h4 class="mtb7-17">wyloguj</h4>
                    <a id="logOut" role="button" class="btn btn-outline-dark"
                       href="<c:url value="/panel/wyloguj"/>">przejdź</a>
                </div>

                <div class="col-lg-3 text-center margin-auto">
                    <h4 class="mtb7-17">ustawienia</h4>
                    <a role="button" class="btn btn-outline-dark" href="<c:url value="/panel/ustawienia"/>">przejdź</a>
                </div>

                <div class="col-lg-3 text-center margin-auto">
                    <h4 class="mtb7-17">pomoc</h4>
                    <a role="button" class="btn btn-outline-dark" href="<c:url value="/panel/pomoc"/>">przejdź</a>
                </div>
                <div class="col-lg-3 text-center margin-auto">
                    <h4 class="mtb7-17">nowa ankieta</h4>
                    <a id="newForm" role="button" class="btn btn-outline-dark"
                       href="<c:url value="/panel/tworzenie-nowej-ankiety"/>">przejdź</a>
                </div>
            </div>
        </div>
    </div>

    <div class="row m-top">
        <c:if test="${surveys == null}">
            <div class="col-lg-12 text-center">
                <h2>Nie masz żadnych utworzonych ankiet</h2>
            </div>
        </c:if>
        <c:if test="${surveys != null}">
            <div class="col-lg-12 text-center">
                <h2>Twoje ankiety</h2>
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
                    <c:forEach items="${surveys}" var="s" varStatus="i">
                        <tr>
                            <th scope="row">${i.count}</th>
                            <td>${s.name}</td>
                            <td>
                                <fmt:parseDate value="${s.creationDate}" pattern="yyyy-MM-dd" var="creationDate" type="date"/>
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
            </div>
        </c:if>
    </div>
</div>

<c:if test="${surveys == null}">
    <footer class="footer-sm-page">
        <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
    </footer>
</c:if>

<c:if test="${surveys != null}">
    <footer>
        <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
    </footer>
</c:if>

<script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"/>"></script>
<script src="<c:url value="https://cdn.jsdelivr.net/npm/clipboard@2/dist/clipboard.min.js"/>"></script>
<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/index.js"/>"></script>

</body>
</html>
