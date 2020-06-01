<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                <c:forEach items="${animals}" var="animal1" begin="0" end="${animals.size()}" varStatus="a1">
                    <c:forEach items="${animals}" var="animal2" begin="${a1.count}" end="${animals.size()}">
                        <c:if test="${animal1.name ne animal2.name}">
                            <tr>
                                <td>${animal1.name}</td>
                                <td>
                                    <select class="form-control" id="sel1">
                                        <option>nieznaczna przewaga - A</option>
                                        <option>umiarkowana przewaga - A</option>
                                        <option>istotna przewaga - A</option>
                                        <option>silna przewaga - A</option>
                                        <option>istotnie silna przewaga - A</option>
                                        <option>bardzo silna przewaga - A</option>
                                        <option>itotnie bardzo silna przewaga - A</option>
                                        <option>ekstremalna przewaga - A</option>
                                        <option>brak przewagi</option>
                                        <option>nieznaczna przewaga - B</option>
                                        <option>umiarkowana przewaga - B</option>
                                        <option>istotna przewaga - B</option>
                                        <option>silna przewaga - B</option>
                                        <option>istotnie silna przewaga - B</option>
                                        <option>bardzo silna przewaga - B</option>
                                        <option>itotnie bardzo silna przewaga - B</option>
                                        <option>ekstremalna przewaga - B</option>
                                    </select>
                                </td>
                                <td>${animal2.name}</td>
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

</body>
</html>
