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
            <a class="nav-item nav-link active"><strong>panel użytkownika</strong></a>
        </div>
    </nav>
    <hr>
    <div class="row text-center">
        <div class="col-lg-12 text-center">
            <h2 class="mtb7-27">Witaj ${user}</h2>
            <hr>
            <div class="row">
                <div class="col-lg-3 text-center margin-auto">
                    <h4 class="mtb7-17">wyloguj</h4>
                    <a id="logOut" role="button" class="btn btn-outline-dark" href="/wyloguj">przejdź</a>
                </div>

                <div class="col-lg-3 text-center margin-auto">
                    <h4 class="mtb7-17">ustawienia</h4>
                    <a role="button" class="btn btn-outline-dark" href="/panel/ustawienia">przejdź</a>
                </div>

                <div class="col-lg-3 text-center margin-auto">
                    <h4 class="mtb7-17">pomoc</h4>
                    <a role="button" class="btn btn-outline-dark" href="/panel/pomoc">przejdź</a>
                </div>
                <div class="col-lg-3 text-center margin-auto">
                    <h4 class="mtb7-17">nowa ankieta</h4>
                    <a id="newForm" role="button" class="btn btn-outline-dark" href="/panel/tworzenie-nowej-ankiety">przejdź</a>
                </div>
            </div>
        </div>
    </div>

    <div class="row m-top">
        <c:if test="${polls == null}">
            <div class="col-lg-12 text-center">
                <h2>Nie masz żadnych utworzonych ankiet</h2>
            </div>
        </c:if>
        <c:if test="${polls != null}">
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
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${polls}" var="p" varStatus="i">
                        <tr>
                            <th scope="row">${i.count}</th>
                            <td>${p.name}</td>
                            <c:if test="${p.creationDate.monthValue < 10}">
                                <td>
                                        ${p.creationDate.dayOfMonth}-0${p.creationDate.monthValue}-${p.creationDate.year}
                                </td>
                            </c:if>
                            <c:if test="${p.creationDate.monthValue >= 10}">
                                <td>
                                        ${p.creationDate.dayOfMonth}-${p.creationDate.monthValue}-${p.creationDate.year}
                                </td>
                            </c:if>

                            <c:if test="${p.endDate.monthValue < 10}">
                                <td>
                                        ${p.endDate.dayOfMonth}-0${p.endDate.monthValue}-${p.endDate.year}
                                </td>
                            </c:if>
                            <c:if test="${p.endDate.monthValue >= 10}">
                                <td>
                                        ${p.endDate.dayOfMonth}-${p.endDate.monthValue}-${p.endDate.year}
                                </td>
                            </c:if>
                            <td>
                                ${p.actualNoOfVotes}/${p.noOfVoters}
                            </td>
                            <td>
                                <button class="btn btn-outline-primary copy" id="link${p.id}"
                                        data-clipboard-text="http://localhost:8080/ankieta=${p.link}">link
                                </button>
                            </td>
                            <td>
                                <a class="btn btn-outline-success" href="/panel/wynik/${p.id}" role="button">wynik</a>
                            </td>
                            <td>
                                <a class="btn btn-outline-warning" href="/panel/edycja-ankiet/" role="button">edytuj</a>
                            </td>
                            <td>
                                <a class="btn btn-outline-danger" href="/panel/edycja-ankiet/usun/${p.id}"
                                   role="button">usuń</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>

<c:if test="${polls == null}">
    <footer class="footer-sm-page">
        <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
    </footer>
</c:if>

<c:if test="${polls != null}">
    <footer>
        <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
    </footer>
</c:if>

<script src="https://cdn.jsdelivr.net/npm/clipboard@2/dist/clipboard.min.js"></script>
<script src="<c:url value="/static/js/index.js"/>"></script>
</body>
</html>
